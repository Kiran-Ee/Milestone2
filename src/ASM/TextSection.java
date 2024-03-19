package ASM;

import java.util.LinkedHashMap; // like hash but maintains ordering + allows us to immediately find labels

import Util.General;

public class TextSection {
    public LinkedHashMap<String, String[][]> fake_text_memory(String[][] cleaned_data_sec) {
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

        String[] cleaned_line = General.mnemonic_cleaner(line);
        if (cleaned_line[0].contains(":")) { // for labels
            String s = cleaned_line[0];
            cleaned_line[0] = s.substring(0, s.indexOf(":"));
        }
        return cleaned_line;
    }

    public int calc_offset_address(String pc_plus_4, String label_address) {
        return -1;
    }

    public String[] text_to_hex_instructions(LinkedHashMap<String, String[]> dataMemory, LinkedHashMap<String, String[][]> textMemory) {
        return null;
    }
}
