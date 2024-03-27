package Operations;
import static Util.General.immediate_to_hex;

// Josiah
public class Li implements PseudoOperation {
    private String[] originalInstruction;
    private Lui luihalf;
    private Ori orihalf;
    private Addiu add;
    public Li(String[] cleaned_instructions){
        if(!(cleaned_instructions[2].startsWith("0x"))){
            if(cleaned_instructions[2].startsWith("-")){
                if(Integer.parseInt(cleaned_instructions[2]) > -32769) {
                    cleaned_instructions[2] = immediate_to_hex(cleaned_instructions[2], true);
                    add = new Addiu(new String[]{"addiu", cleaned_instructions[1], "$0", "0x" + cleaned_instructions[2]});
                } else {
                    int immediate = Integer.parseInt(cleaned_instructions[2]); // Example signed immediate value from li instruction

                    // Extract the upper and lower 16 bits
                    int upper = (immediate & 0xFFFF0000) >>> 16;
                    int lower = immediate & 0xFFFF;

                    luihalf = new Lui(new String[]{"lui", "$at", "0x" + Integer.toHexString(upper)});
                    orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$at", "0x" + Integer.toHexString(lower)});
                }
            } else {
                cleaned_instructions[2] = immediate_to_hex(cleaned_instructions[2], false);
                if(Integer.parseInt(cleaned_instructions[2], 16) <= 0x7FFF){
                    add = new Addiu(new String[]{"addiu", cleaned_instructions[1], "$0", "0x" + cleaned_instructions[2]});
                } else {
                    orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$0", "0x" + cleaned_instructions[2]});
                }

            }
        } else {
            cleaned_instructions[2] = cleaned_instructions[2].substring(2); //Remove 0x
            if(cleaned_instructions[2].length() < 4){
                add = new Addiu(new String[]{"addiu", cleaned_instructions[1], "$0", cleaned_instructions[2]});
            } else {
                var first = "0x" + hexFix(cleaned_instructions[2].substring(0, cleaned_instructions[2].length() - 4)); //Gets first part and makes it into 4 length for lui
                var last = "0x" + cleaned_instructions[2].substring(cleaned_instructions[2].length() - 4); //Gets last 4 for ori
                if (!first.equals("0x0000")) {
                    luihalf = new Lui(new String[]{"lui", "$at", first});
                    orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$at", last});
                } else {
                    orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$0", last});
                }
            }
        }

        originalInstruction = cleaned_instructions;
    }

    private String hexFix(String hex){
        if(hex.length() == 4){
            return hex;
        } else if(hex.length() == 3){
            return "0" + hex;
        } else if(hex.length() == 2){
            return "00" + hex;
        } else if(hex.length() == 1){
            return "000" + hex;
        } else {
            return "0000";
        }

    }

    public String[] get_hex(){
        //Put two hexes in array arr[0] = lui.hex arr[1] = ori.hex
        if(add != null) {
            return new String[]{add.get_hex()};
        }
        if(luihalf == null){
            return new String[]{orihalf.get_hex()};
        }
        return new String[]{luihalf.get_hex(), orihalf.get_hex()};
    }
    public String[] getInstruction(){
        return originalInstruction;
    }
}
