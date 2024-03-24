package Operations;

import java.util.LinkedHashMap;

public class Blt implements PseudoOperation {

    private String[] originalInstruction;
    private Slt slthalf;
    private Bne bnehalf;
    private String reg1;
    private String reg2;
    private String offset;

    public Blt(String[] cleaned_instructions) { //EX: "blt" "$t0" "$a3" "offset"
        reg1 = cleaned_instructions[1];
        reg2 = cleaned_instructions[2];
        offset = cleaned_instructions[3]; //!!!ASSUMING WE SEND IN AN OFFSET OF THE LABEL ALREADY!!! ... handled in "text_to_hex_instructions"
        originalInstruction = cleaned_instructions;
        String[] slt = new String[]{"Slt", "$at", reg1, reg2};
        slthalf = new Slt(slt);
        String[] bne = new String[]{"Bne", "$at", "$0", offset}; //MUST SEND IN OFFSET NOT STRING LABEL
        bnehalf = new Bne(bne);
    }

    public String[] get_hex(){
        //Put two hexes in array arr[0] = lui.hex arr[1] = ori.hex
        return new String[]{slthalf.get_hex(), bnehalf.get_hex()};
    }
    public String[] getInstruction(){
        return originalInstruction;
    }
}
