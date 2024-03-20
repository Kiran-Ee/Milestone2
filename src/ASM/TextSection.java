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
            if (instr[0].contains(":")) { // label
                cur_addr = DataSection.calc_next_address(cur_addr, 4);
                lhm.put(instr[0], new String[][]{new String[]{counter_str}, new String[]{cur_addr}});
            } else { // non-label
                lhm.put(counter_str, new String[][]{instr, new String[]{cur_addr}});
                cur_addr = DataSection.calc_next_address(cur_addr, 4);
            }
            counter++;
        }
        return null;
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

    public int calc_offset_address(String pc_plus_4, String label_address) {
        return -1;
    }

    public String[] text_to_hex_instructions(LinkedHashMap<String, String[]> dataMemory, LinkedHashMap<String, String[][]> textMemory) {
        return null;
    }
}
