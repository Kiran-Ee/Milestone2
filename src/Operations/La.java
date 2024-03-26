package Operations;
// Josiah
public class La implements PseudoOperation {

    private String[] originalInstruction;
    private Lui luihalf;
    private Ori orihalf;
    public La(String[] cleaned_instructions){ //EX: "la, "$t0", "10010000"
        String[] lui = new String[]{"lui", "$at", "4097"}; //Will always be 0x1001, because identifier for data address
        //Construct ori, parameter needs to be in hex, code gets last 4 bits
        String[] ori = new String[]{"ori", cleaned_instructions[1], "$at", "0x" + cleaned_instructions[2].substring(4)};
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
