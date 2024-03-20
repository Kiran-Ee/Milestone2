package ASM;

import java.util.LinkedHashMap; // like hash but maintains ordering + allows us to immediately find labels

import Util.General;

public class TextSection {
    public LinkedHashMap<String, String[][]> fake_text_memory(String[][] cleaned_text_sec) { // {“index or label” : [[“instruction”], [“address in memory”]] }
        int counter = 0;
        String cur_addr = "00400000";
        LinkedHashMap<String, String[][]> lhm = new LinkedHashMap<>();

        for (String[] instr : cleaned_text_sec) {
            String counter_str = "" + counter;

            if (instr[0].contains(":"))  { // label
                cur_addr = DataSection.calc_next_address(cur_addr, 4);
                cur_addr = General.pad_hex(cur_addr, 8); // padding 0's bc "00" gets removed
                lhm.put(instr[0], new String[][]{new String[]{counter_str}, new String[]{cur_addr}});
            }
            else {
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

    public String[] text_to_hex_instructions(LinkedHashMap<String, String[]> dataMemory, LinkedHashMap<String, String[][]> textMemory) {

        return null;
    }

    public int calc_offset(String pc, String label_address) {
        int pc_plus_4_int = Integer.parseInt(pc) + 1; // "4 bytes" = "1 word"
        int lbl_int = Integer.parseInt(label_address);
        if (pc_plus_4_int <0 || lbl_int<0) throw new IllegalArgumentException("calc_offset: not possible to send negative index ...");
        return lbl_int - pc_plus_4_int;
    }
}
