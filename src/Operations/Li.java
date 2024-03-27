package Operations;
// Josiah
public class Li implements PseudoOperation {
    private String[] originalInstruction;
    private Lui luihalf;
    private Ori orihalf;

    public Li(String[] cleaned_instructions){
        cleaned_instructions[2] = cleaned_instructions[2].substring(2);
        var first = "0000";
        var last = "0000";
        if(cleaned_instructions[2].length() < 4){
            //16 bit
            first = "0000";
            last = hexFix(cleaned_instructions[2]);
        } else {
            //32 bit
            first = "0x" + hexFix(cleaned_instructions[2].substring(0, cleaned_instructions[2].length() - 4)); //Gets first part and makes it into 4 length for lui
            last = "0x" + cleaned_instructions[2].substring(cleaned_instructions[2].length() - 4); //Gets last 4 for ori
        }
        luihalf = new Lui(new String[]{"lui", "$at", first});
        orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$at", last});
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
        return new String[]{luihalf.get_hex(), orihalf.get_hex()};
    }
    public String[] getInstruction(){
        return originalInstruction;
    }
}
