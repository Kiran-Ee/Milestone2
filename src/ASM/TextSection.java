package ASM;

import java.util.*;

import Operations.Li;
import Operations.PseudoOperation;
import Util.General;

public class TextSection {
    private final String REL_BRANCH_INSTR = "beq bne blt"; //beq, bne, blt = offset in text section... j = absolute address in text section ... la = data address
    private final String LA_INSTR = "la";
    private final String ABS_BRANCH_INSTR = "j";
    private final String PSEUDO_INSTR = "li la blt";

    public LinkedHashMap<String, String[][]> fake_text_memory(String[][] cleaned_text_sec) { // {“index or label” : [[“instruction or index”], [“address in memory”]] }
        int counter = 0;
        String cur_addr = "00400000";
        String op = "";
        LinkedHashMap<String, String[][]> lhm = new LinkedHashMap<>();

        for (String[] instr : cleaned_text_sec) {
            op = instr[0];
            String counter_str = "" + counter;

            if (op.contains(":")) { // label
                lhm.put(op, new String[][]{new String[]{counter_str}, new String[]{cur_addr}});
            } else {
                lhm.put(counter_str, new String[][]{instr, new String[]{cur_addr}});
                int num_instr = determine_num_instr(op, instr);
                cur_addr = DataSection.calc_next_address(cur_addr, 4*num_instr);
                cur_addr = General.pad_hex(cur_addr, 8);
            }
            counter++;
        }
        counter++;
        return lhm;
    }

    // Helper  for fake_text_memory to deal with pseudos
    private int determine_num_instr(String op, String[] instr) {
        if ("li".equals(op)) {
            Li flag = new Li(instr);
            if ("Addiu".equals(flag.flag) || "Ori".equals(flag.flag)) {
                return 1;
            } else {
                return 2; // Lui + Ori
            }
        } else if ("la".equals(op) || "blt".equals(op)) {
            return 2;
        } else {
            return 1;
        }
    }

public String[] text_line_cleaner(String line) {
    if (line.trim().isEmpty())  // empty line
        return new String[]{};

    int index_start_char = General.find_non_space(0, line);
    String first_char = String.valueOf(line.charAt(index_start_char));
    if (first_char.equals("#"))  // comment line
        return new String[]{};

    return General.mnemonic_cleaner(line);
}

public String[] text_to_hex_instructions(LinkedHashMap<String, String[]> data_mem, LinkedHashMap<String, String[][]> text_mem) {
    String hex = "";
    ArrayList<String> al = new ArrayList<>();
    int cur_ind = -1; // used for offset calculation

    for (Map.Entry<String, String[][]> entry : text_mem.entrySet()) {
        cur_ind++;
        String key = entry.getKey();
        if (key.contains(":")) continue; // shouldn't return "Text-Labels"
        String[] instr_arr = entry.getValue()[0]; // ["add", "$0", "$1", "$2"]
        String op = instr_arr[0]; // "add"

        if (REL_BRANCH_INSTR.contains(op)) { // blt, bne, beq
            instr_arr[3] = rel_branch_offset(instr_arr, cur_ind, text_mem);
        }
        if (LA_INSTR.contains(op)) { // la
            instr_arr[2] = dataLbl_to_addr(instr_arr[2], data_mem);
        }
        if (ABS_BRANCH_INSTR.contains(op)) { // j
            instr_arr[1] = textLbl_to_addr(instr_arr[1], text_mem);
        }
        if (PSEUDO_INSTR.contains(op)) { // li, la, blt
            String[] hex_arr = General.pseudo_instruction_factory(instr_arr);
            if (!hex_arr[0].isEmpty()) al.add(hex_arr[0]); // "if"'s are for "la" it can return 1 or 2 instructions.
            if (!hex_arr[1].isEmpty()) al.add(hex_arr[1]);
        } else {
            hex = General.instruction_factory(instr_arr);
            al.add(hex);
        }
    }
    String[] ar = new String[al.size()];
    return al.toArray(ar);
}

// Return relative offset from current index to label's index or just the offset if was already sent an offset (for bne, blt, beq)
private String rel_branch_offset(String[] instr, int cur_ind, LinkedHashMap<String, String[][]> text_mem) {
    String possible_label = instr[3];
    String lbl_ind = "";
    lbl_ind = textLbl_to_index(possible_label, text_mem);

    if (!lbl_ind.equals("Invalid Key")) {
        return "" + calc_offset(cur_ind, Integer.parseInt(lbl_ind));
    } else {
        return possible_label; // when formatted like MIPS1: not sent a label, it's already an offset so just return
    }
}

// Helper for "relative_branch_offset_calc": Return the index of a key inside text_mem
private static String textLbl_to_index(String key, LinkedHashMap<String, String[][]> text_mem) {
    if (text_mem.get(key + ":") != null) {
        return (text_mem.get(key + ":")[0][0]);
    } else {
        return "Invalid Key";
    }
}

// Helper for "relative_branch_offset_calc:" Return difference between "next index" and "label index"
public int calc_offset(int cur_addr_ind, int lbl_addr_ind) {
    int pc_plus_4_int = cur_addr_ind + 1;
    int lbl_int = lbl_addr_ind;

    if (pc_plus_4_int < 0 || lbl_int < 0)
        throw new IllegalArgumentException("calc_offset: not possible to send negative index ...");
    return lbl_int - pc_plus_4_int;
}

// Return absolute text mem address of the label (for j) ... IN WORD ADDRESSING
private String textLbl_to_addr(String key, LinkedHashMap<String, String[][]> text_mem) {
    if (text_mem.get(key + ":") != null) {
        String memAddr_byte = (text_mem.get(key + ":")[1][0]);
        int wordAddress = Integer.parseInt(memAddr_byte, 16) / 4;
        String memAddr_word = Integer.toHexString(wordAddress);
        return "0x" + memAddr_word;
    } else {
        return "Invalid Key";
    }
}

// Return data mem address of the label (for la)
private String dataLbl_to_addr(String key, LinkedHashMap<String, String[]> data_mem) {
    if (data_mem.get(key) != null) {
        return (data_mem.get(key)[1]);
    } else {
        return "Invalid Key";
    }
}

}
