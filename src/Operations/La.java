package Operations;
// Josiah
public class La implements PseudoOperation {

    private String[] originalInstruction;
    private Lui luihalf;
    private Ori orihalf;
    public La(String[] cleaned_instructions) { //EX: "la, "$t0", "10010000"
        int shifted_for_lui = Integer.valueOf(cleaned_instructions[2], 16) >>> 4; // removing bottom 4 bits
        String[] lui = new String[]{"Lui", "$at", String.valueOf(shifted_for_lui)};
        int shifted_for_ori = Integer.valueOf(cleaned_instructions[2], 16) << 4 >>> 4; // removing top 4 bits
        String[] ori = new String[]{"Ori", cleaned_instructions[1], "$at", String.valueOf(shifted_for_ori)};
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
