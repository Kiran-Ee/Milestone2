package ASM;

import java.util.LinkedHashMap; // like hash but maintains ordering + allows us to immediately find labels

public class DataSection {
    public LinkedHashMap<String, String[]> fake_data_memory(String[][] cleaned_data_sec) {
        LinkedHashMap<String, String[]> lhm = new LinkedHashMap<>();
        String prev_addr = "10010000";
        for(String[] instr: cleaned_data_sec){
            String label = instr[0];
            String data_val = instr[1];

            int cur_size = calc_data_size(".asciiz", data_val);
            String address = calc_next_address(prev_addr, cur_size);

            lhm.put(label, new String[]{data_val, address});
        }
        return lhm;
    }

    public int calc_data_size(String data_type, String data) {
        if (data_type.equals(".asciiz")){ // no .word for this assignment ...
            return data.length() + 1;
        } else{
            return 4;
        }
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
