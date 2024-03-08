package Operations;

import Util.General;

public class j implements Operation {
    private final String j = "000010"; //opcode for j instruction
    private String instr_index;

    //Constructor
    // -	If not passed a List, not all of them are Strings, or missing values, raises illegalArgumentExceptions
    public j(String[] cleaned_instr)
    {
        for (String i : cleaned_instr) {
            if (!(i instanceof String))
                throw new IllegalArgumentException("Can't send in non string to j operations constructor");
        }
        //input validation
        if(cleaned_instr == null || cleaned_instr.length !=2){
            throw new IllegalArgumentException("Invalid input");
        }
        //set instr_index from input
        this.instr_index = cleaned_instr[1];
    }
    public String get_hex()
    {
        String instr_index_binary = General.instr_index_to_binary(instr_index, true);  // ASSUMING SIGNED*

        String com_bin = j + instr_index_binary;

        int com_dec = Integer.parseInt(com_bin, 2);

        String com_hex = Integer.toHexString(com_dec);

        com_hex = General.pad_hex(com_hex, 8);

        return com_hex;
    }
    //get instruction format
    public String[] getInstruction()
    {
        return new String[]{j, instr_index};
    }
}

