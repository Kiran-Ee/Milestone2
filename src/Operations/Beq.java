package Operations;

import Util.General;

import static Util.General.register_to_binary;

public class Beq implements Operation{
    private final String BEQ = "000100";
    private String rs = "";
    private String rt = "";
    private String offset = "";

    public Beq(String[] cleaned_instr){
      rs = cleaned_instr[1];
      rt = cleaned_instr[2];
      offset = cleaned_instr[3];  //!!!ASSUMING WE SEND IN AN OFFSET OF THE LABEL ALREADY!!! ... handled in "text_to_hex_instructions"

    }
    public String get_hex(){
        String rs_binary = General.register_to_binary(rs);
        String rt_binary = General.register_to_binary(rt);
        String offset_hex = General.immediate_to_hex(offset, true); // not sure if this is signed*

        String com = BEQ + rs_binary + rt_binary;

        int com_dec = Integer.parseInt(com,2);

        String com_hex = Integer.toHexString(com_dec) +offset_hex;

        return com_hex;
    }

    public String[] getInstruction(){ // used to check constructor
        String[] ins = {BEQ, rs, rt, offset};
        return ins;
    }
}
