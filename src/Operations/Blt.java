package Operations;

public class Blt {

    private String[] originalInstruction;
    private Slt slthalf;
    private Bne bnehalf;
    private String reg1;
    private String reg2;
    private String label;

    public Blt(String[] cleaned_instructions){ //EX: "la, "$t0", "10010000"
        reg1 = cleaned_instructions[1];
        reg2 = cleaned_instructions[2];
        label = cleaned_instructions[3];
        originalInstruction = cleaned_instructions;
        String[] slt = new String[]{"SLT", "$at", reg1, reg2};
        slthalf = new Slt(slt);
        String[] bne = new String[]{"BNE", "$at", "$0", label};
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
