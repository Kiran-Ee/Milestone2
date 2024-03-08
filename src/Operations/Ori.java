package Operations;

import Util.General;

public class Ori implements Operation{
    private final String ORI = "001101";
    private String rs = "";
    private String rt = "";
    private String immediate = "";

    public Ori(String[] cleaned_instr)
    {
        rs = cleaned_instr[2];
        rt = cleaned_instr[1];
        immediate = cleaned_instr[3];
    }

    public String get_hex() {
        String rs_binary = General.register_to_binary(rs);
        String rt_binary = General.register_to_binary(rt);
        String immediate_hex = General.immediate_to_hex(immediate, true);

        String com = ORI + rs_binary + rt_binary;

        int com_dec = Integer.parseInt(com,2);

        String com_hex = Integer.toHexString(com_dec)+immediate_hex;

        return com_hex;
    }
    public String[] getInstruction()
    {
        return new String[]{ORI, rs, rt, immediate};
    }
}
