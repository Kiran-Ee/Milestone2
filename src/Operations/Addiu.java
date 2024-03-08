package Operations;

import Util.General;

public class Addiu implements Operation{
    private final String ADDIU = "001001";
    private String rs = "";
    private String rt = "";
    private String immediate = "";

    public Addiu (String[] cleaned_instructions)
    {
        rs = cleaned_instructions[2]; //assuming the instance variables are Strings: registers
        rt = cleaned_instructions[1];
        immediate = cleaned_instructions[3];
    }
    public String get_hex()
    {
        String rs_binary = General.register_to_binary(rs);
        String rt_binary = General.register_to_binary(rt);
        String immediate_hex = General.immediate_to_hex(immediate, false);

        String com = ADDIU + rs_binary + rt_binary;

        int com_dec = Integer.parseInt(com,2);

        String com_hex = Integer.toHexString(com_dec) + immediate_hex;

        return com_hex;
    }
    public String[] getInstruction()
    {
        return new String[]{ADDIU, rs, rt, immediate};
    }
}
