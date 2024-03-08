package Operations;

import Util.General;

public class And implements Operation{
    private final String SPECIAL = "000000";
    private String rs = "";
    private String rt = "";
    private String rd = "";
    private final String ZERO = "00000";
    private final String AND = "100100";

    public And(String[] cleaned_instr)
    {
        rs = cleaned_instr[2]; //assuming the instance variables are Strings: registers
        rt = cleaned_instr[3];
        rd = cleaned_instr[1];
    }
    public String get_hex()
    {
        String rs_binary = General.register_to_binary(rs);
        String rt_binary = General.register_to_binary(rt);
        String rd_binary = General.register_to_binary(rd);

        String com = SPECIAL + rs_binary + rt_binary + rd_binary + ZERO + AND;

        int com_dec = Integer.parseInt(com,2);

        String com_hex = Integer.toHexString(com_dec);

        com_hex = General.pad_hex(com_hex, 8);

        return com_hex;
    }

    public String[] getInstruction() {
        String[] s = {SPECIAL, rs, rt, rd, ZERO, AND};
        return s;
    }
}
