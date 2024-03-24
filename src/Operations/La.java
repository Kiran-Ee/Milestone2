package Operations;
// Josiah
public class La implements PseudoOperation {

    private String[] originalInstruction;
    private Lui luihalf;
    private Ori orihalf;
    public La(String[] cleaned_instructions){ //EX: "la, "$t0", "10010000"
        String[] lui = new String[]{"LUI", "$at", "1001"}; //Will always be 1001
        int shifted_for_ori = Integer.parseInt(cleaned_instructions[2]) << 4 >>> 4;
        String[] ori = new String[]{"ORI", cleaned_instructions[1], "$at", String.valueOf(shifted_for_ori)};
        luihalf = new Lui(lui);
        orihalf = new Ori(ori);
        originalInstruction = cleaned_instructions;
    }

    public String[] get_hex(){
        //Put two hexes in array arr[0] = lui.hex arr[1] = ori.hex
        return new String[]{luihalf.get_hex(), orihalf.get_hex()};
    }
    public String[] getInstruction(){
        return originalInstruction;
    }
}
