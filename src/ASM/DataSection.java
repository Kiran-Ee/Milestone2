package ASM;

import java.util.ArrayList;
import java.util.LinkedHashMap; // like hash but maintains ordering + allows us to immediately find labels
import java.util.List;

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
        int hexValue = Integer.parseInt(prev_address, 16);
        int sum = hexValue + current_size;
        return Integer.toHexString(sum);
    }

    /*
    need revision-during the meeting you mentioned that we may need to import the data value within the data cleaner
    that being said wouldn't we need one extra parameter dataValue in the method signature and
    instead of line to have label ex String[] declaration_line_cleaner(String label, string dataValue)
    also if we add additional par.dataValue may need to construct the line by concatenating label and dataValue
    ex.String line = label.trim() + ""+ dataValue.trim();
    p.s below is first draft of the method implementation base on pre-post conditions and test cases
     */

    public String[] declaration_line_cleaner(String line) {
        //  for empty line and return empty sting
        if(line == null || line.trim().isEmpty()){
            return new String[]{};
        }

        //split the line by tab, whitespaces, comments
        String[] p = line.split("[\\s\\t:#]+");

        //remove empty elements from the array
        List<String> cleanedPart = new ArrayList<>();
        for(String part : p){
            if(!part.isEmpty()){
                cleanedPart.add(part);
            }
        }
        //convert the list to a string array
        return cleanedPart.toArray(new String[0]);
    }

    public String[] data_to_little_endian(LinkedHashMap<String, String[]> dataMemory) {
        return null;
    }
}
