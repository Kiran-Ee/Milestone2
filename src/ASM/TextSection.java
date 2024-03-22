package ASM;

import java.util.*;

import Util.General;

public class TextSection {
    private final String LABEL_INSTR = "beq bne j blt la";
    private final String PSEUDO_INSTR = "li la blt";

    public LinkedHashMap<String, String[][]> fake_text_memory(String[][] cleaned_text_sec) { // {“index or label” : [[“instruction”], [“address in memory”]] }
        int counter = 0;
        String cur_addr = "00400000";
        LinkedHashMap<String, String[][]> lhm = new LinkedHashMap<>();

        for (String[] instr : cleaned_text_sec) {
            String counter_str = "" + counter;

            if (instr[0].contains(":")) { // label
                cur_addr = DataSection.calc_next_address(cur_addr, 4);
                cur_addr = General.pad_hex(cur_addr, 8); // padding 0's bc "00" gets removed
                lhm.put(instr[0], new String[][]{new String[]{counter_str}, new String[]{cur_addr}});
            } else {
                lhm.put(counter_str, new String[][]{instr, new String[]{cur_addr}});
                cur_addr = DataSection.calc_next_address(cur_addr, 4);
                cur_addr = General.pad_hex(cur_addr, 8);
            }
            counter++;
        }
        return lhm;
    }

    public String[] text_line_cleaner(String line) {
        if (line.trim().isEmpty()) { // empty line
            return new String[]{};
        }

        int index_start_char = General.find_non_space(0, line);
        String first_char = String.valueOf(line.charAt(index_start_char));
        if (first_char.equals("#")) { // comment line
            return new String[]{};
        }

        return General.mnemonic_cleaner(line);
    }

    public String[] text_to_hex_instructions(LinkedHashMap<String, String[]> data_mem, LinkedHashMap<String, String[][]> text_mem) {
        String hex = "";
        ArrayList<String> al = new ArrayList<>();

        for (Map.Entry<String, String[][]> entry : text_mem.entrySet()) {
            String key = entry.getKey();
            if (key.contains(":")) continue; // shouldn't return "Text-Labels"
            String[] instr_arr = entry.getValue()[0];
            String op = instr_arr[0];

            if (LABEL_INSTR.contains(op)) {
                label_factory(instr_arr, data_mem, text_mem); // replacing label w/ memory
            }
            if (PSEUDO_INSTR.contains(op)) {
                String[] hex_arr = General.pseudo_instruction_factory(instr_arr);
                al.add(hex_arr[0]);
                al.add(hex_arr[1]);
            } else {
                hex = General.instruction_factory(instr_arr);
                al.add(hex);
            }
        }
        String[] ar = new String[al.size()];
        return al.toArray(ar);
    }

    // Replaces the label with the memory address
    private void label_factory(String[] instr, LinkedHashMap<String, String[]> data_mem, LinkedHashMap<String, String[][]> text_mem) {
        String s = "";
        switch (instr[0]) {
            case "beq":
                s = instr[3];
                instr[3] = key_to_mem_addr(s, data_mem, text_mem);
                break;
            case "bne":
                s = instr[3];
                instr[3] = key_to_mem_addr(s, data_mem, text_mem);
                break;
            case "j":
                s = instr[1];
                instr[1] = key_to_mem_addr(s, data_mem, text_mem);
                break;
            case "blt":
                s = instr[3];
                instr[3] = key_to_mem_addr(s, data_mem, text_mem);
                break;
            case "la":
                s = instr[2];
                instr[2] = key_to_mem_addr(s, data_mem, text_mem);
                break;
        }
    }

    // Returns the memory address of a key either inside of the data_mem or text_mem ... MUST SEND A VALID KEY
    private String key_to_mem_addr(String key, LinkedHashMap<String, String[]> data_mem, LinkedHashMap<String, String[][]> text_mem) {
        if (data_mem.get(key) != null) {
            return data_mem.get(key)[1];
        }
        if (text_mem.get(key) != null) {
            return text_mem.get(key)[1][0];
        } else {
            throw new IllegalArgumentException("Sent a key, label, not present in data_mem or text_mem");
        }
    }

    public int calc_offset(String pc, String label_address) {
        int pc_plus_4_int = Integer.parseInt(pc) + 1; // "4 bytes" = "1 word"
        int lbl_int = Integer.parseInt(label_address);
        if (pc_plus_4_int < 0 || lbl_int < 0)
            throw new IllegalArgumentException("calc_offset: not possible to send negative index ...");
        return lbl_int - pc_plus_4_int;
    }
}
