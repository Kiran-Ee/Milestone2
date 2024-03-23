package ASM;

import Util.General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap; // like hash but maintains ordering + allows us to immediately find labels
import java.util.List;
import java.util.Map;

public class DataSection {
    public LinkedHashMap<String, String[]> fake_data_memory(String[][] cleaned_data_sec) {
        LinkedHashMap<String, String[]> lhm = new LinkedHashMap<>();
        String cur_address = "10010000";
        for(String[] instr: cleaned_data_sec){
            String label = instr[0];
            String data_val = instr[1];
            int cur_size = calc_data_size(".asciiz", data_val);

            lhm.put(label, new String[]{data_val, cur_address});

            cur_address = calc_next_address(cur_address, cur_size);
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

    public static String calc_next_address(String prev_address, int current_size) {
        int hexValue = Integer.parseInt(prev_address, 16);
        int sum = hexValue + current_size;
        return Integer.toHexString(sum);
    }

    public String[] declaration_line_cleaner(String line) {
        if(line == null || line.trim().isEmpty()) //  for empty line and return empty sting
            return new String[]{};
        //label name
        List<String> cleanedPart = new ArrayList<>();
        int start_ind = General.find_non_space(0, line);
        int colon_ind = line.indexOf(":");
        cleanedPart.add(line.substring(start_ind, colon_ind));

        //data type
        start_ind = General.find_non_space(colon_ind + 1, line);
        int space_ind = line.indexOf(" ", start_ind + 1);
        //cleanedPart.add(line.substring(start_ind, space_ind)); // don't add data type

        // data
        start_ind = General.find_non_space(space_ind + 1, line);
        int last_qoute_ind = line.indexOf("\"", start_ind + 1);
        cleanedPart.add(line.substring(start_ind+1, last_qoute_ind));

        return cleanedPart.toArray(new String[0]);
    }

    public String[] data_to_little_endian(LinkedHashMap<String, String[]> dataMemory) { //Water bottle = etaW-ob r-eltt-___0
        String[] returnString = new String[]{};
        dataMemory.forEach((key, value) -> { // Loops for every Label
            int curIndex = 1;
            int multiplier = 1;
            while(value[multiplier*4 - curIndex] != null){ // Loops the list from index 3->0, 7->4, 11->8, but stops at null
                int ascii = value[multiplier*4 - curIndex].charAt(0);
                returnString[multiplier-1] = returnString[multiplier-1] + Integer.toHexString(ascii);
                if(++curIndex > 4){
                    curIndex = 1;
                    ++multiplier;
                }
            }
            //Need a thing to add null character, but needs case for if adding null to next word
            if(curIndex >= 4){
                ++multiplier;
                curIndex = 1;
            } else {
                returnString[multiplier - 1] = returnString[multiplier - 1] + "00";
                curIndex++;
            }

            //Fill the word with the Substitute ascii value like MIPS, Dec=26, Hex=1A
            while(curIndex < 4){
                returnString[multiplier-1] = returnString[multiplier-1] + Integer.toHexString(26); //MIPS puts this for blanks
                ++curIndex;
            }
        });
        return returnString;
    }
}
