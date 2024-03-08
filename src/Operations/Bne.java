package Operations;

import Util.General;

public class Bne implements Operation{
    private final String BNE = "000101";
    private String rs = "";
    private String rt = "";
    private String offset = "";

    public Bne(String[] cleaned_instr)
    {
        rs = cleaned_instr[1];
        rt = cleaned_instr[2];
        offset = cleaned_instr[3];
    }
    public String[] getInstruction()
    {
        String[] b = {BNE, rs, rt, offset};
        return b;
    }
    public String get_hex(){
        String rs_binary = General.register_to_binary(rs);
        String rt_binary = General.register_to_binary(rt);
        String offset_hex = General.immediate_to_hex(offset, true);

        String com = BNE + rs_binary + rt_binary;
        //assert rs_binary != null; //should we handle this way the nulls or check with if

        int com_dec = Integer.parseInt(com,2);

        String com_hex = Integer.toHexString(com_dec) +offset_hex;

        return com_hex;
    }

}
