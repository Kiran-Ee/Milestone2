package ASM;

import java.util.LinkedHashMap; // like hash but maintains ordering + allows us to immediately find labels

public class DataSection {
    public LinkedHashMap<String, String[]> fake_data_memory(String[][] cleaned_data_sec) {
        return null;
    }

    public int calc_data_size(String data_type, String data) {
        return -1;
    }

    public String calc_next_address(String prev_address, int current_size) {
        return null;
    }

    public String[] declaration_line_cleaner(String line) {
        return null;
    }

    public String[] data_to_little_endian(LinkedHashMap<String, String[]> dataMemory) {
        return null;
    }
}
