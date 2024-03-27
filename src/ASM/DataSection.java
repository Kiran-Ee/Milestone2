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
    public String[] data_to_little_endian(LinkedHashMap<String, String[]> dataMemory) {
        List<String> returnList = new ArrayList<>();
        String tempStr = "";

        for(Map.Entry<String, String[]> entry : dataMemory.entrySet()) {
            String tempHexStr = "";
            char tempChar = ' ';

            String[] value = entry.getValue();
            String val = value[0];
            int absPointer = 0; //*remove this* 2nd Data_value String: "756f5900"

            while (absPointer < val.length()) { // Reverse "Enter your integer: " -> ["etnE", "oy r", "i ru", "getn", " :re", "000/u"] -> ["65746e45", "6f792072", "69207275", "6765746e", "203a7265"]
                tempChar = val.charAt(absPointer);
                if (tempStr.length() < 8) {
                    int asciiValue = (int) tempChar;
                    tempHexStr = Integer.toHexString(asciiValue);
                    tempStr = tempHexStr + tempStr;
                } else { // Reset split
                    returnList.add(tempStr);
                    tempStr = "";
                    int asciiValue = (int) tempChar;
                    tempHexStr = Integer.toHexString(asciiValue);
                    tempStr = tempHexStr + tempStr;
                }
                absPointer++;
            }
            if (tempStr.length() == 8) {
                returnList.add(tempStr);
                tempStr = "00";
            }
            else{
                tempStr = "00" + tempStr; // null byte to carry over to next iterations.
            }
        }
        if (tempStr.length()!=0){ // padding
            returnList.add(General.pad_hex(tempStr, 8));
        }

        return returnList.toArray(new String[0]);
    }


//    public String[] data_to_little_endian(LinkedHashMap<String, String[]> dataMemory) { //Water bottle = etaW-ob r-eltt-___0
//        String[] returnString = new String[]{};
//        dataMemory.forEach((key, value) -> { // Loops for every Label
//            int curIndex = 1;
//            int multiplier = 1;
//            while(value[multiplier*4 - curIndex] != null){ // Loops the list from index 3->0, 7->4, 11->8, but stops at null
//                //Constructs left to right.
//                //Example: moon
//                //First loop: value[multiplier*4 - curIndex] = value[3]. Gets char hex value and places in string. "6E"
//                //Second loop: value[multiplier*4 - curIndex] = value[2]. Places new value at end of string "6E6F"
//                //Third loop: value[1]. String becomes "6E6F6F"
//                //Fourth loop: value[0] String is "6E6F6F6D" which is "noom"
//                int ascii = value[multiplier*4 - curIndex].charAt(0);
//                returnString[multiplier-1] = returnString[multiplier-1] + Integer.toHexString(ascii);
//                if(++curIndex > 4){
//                    curIndex = 1;
//                    ++multiplier;
//                }
//            }
//            //Need a thing to add null character, but needs case for if adding null to next word
//            // If index is divisible by 4, need a null at the end of next string.
//            //Example: Input: moon, OutputL: noom ___0
//
//            if((multiplier*4 - curIndex) % 4 == 0){
//                returnString[multiplier] = "20202000";
//            } else {
//                //If index is not divisible by 4, need null in current string
//                //Example: Input: Hello!, Output: lloH _0!o
//                //Fill the word with the Substitute ascii value like MIPS, Dec=26, Hex=1A
//                //multiplier*4 - curIndex = 3,2,1,0,7, When loop runs at index 7 = null, but needs to input indexes 6,5 still
//                String next = ""; //If this part runs there will be at least one empty space
//                boolean once = true;
//                for(int i = 1; i < 3; ++i){
//                    if(value[multiplier*4 - i - 1] != null && once){ //If next index is a char, place null
//                        next = next + "00";
//                        once = false; //Ensures only one is placed
//                    } else if (value[multiplier*4 - i] == null){ //If current doesn't exist, place substitute
//                        next = next + "20";
//                    } else {
//                        next = next + value[multiplier*4 - i]; //If does exist, place
//                    }
//                }
//                next = next + value[multiplier*4-4]; //Last char will always enter if this part is run
//            }
//
//
//
//
//        });
//        return returnString;
//    }
}
