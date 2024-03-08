package Operations;

import Util.General;

public class Lui implements Operation{
    private final String LUI = "001111";
    private final String ZERO = "00000";
    private String rt = "";
    private String immediate = "";

    public Lui(String[] cleaned_instr)
    {
        rt = cleaned_instr[1];
        immediate = cleaned_instr[2];
    }

    public String get_hex(){
        String rt_binary = General.register_to_binary(rt);
        String immediate_hex = General.immediate_to_hex(immediate, true);

        String com = LUI + ZERO + rt_binary;

        int com_dec = Integer.parseInt(com,2);

        String com_hex = Integer.toHexString(com_dec) + immediate_hex;

        return com_hex;
    }
    public String[] getInstruction()
    {
        return new String[]{LUI, ZERO, rt, immediate};
    }
}
