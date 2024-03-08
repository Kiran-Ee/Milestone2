package Operations;

import Util.General;
import org.ietf.jgss.GSSName;

/*
 I was thinking about having a package that holds all of our operations we need (add, addi, sub, etc.)
 for organization - this might cause problems with "Main" if it can't see these folders but we will
 deal with that later if it's a problem
 */
public class Add implements Operation {
    private final String SPECIAL = "000000";
    private String rs = "";
    private String rt = "";
    private String rd = "";
    private final String ZERO = "00000";
    private final String FUNC = "100000";


    public Add(String[] cleaned_instructions)
    {
        rs = cleaned_instructions[2]; //assuming the instance variables are Strings: registers
        rt = cleaned_instructions[3];
        rd = cleaned_instructions[1];
    }

    public String get_hex()
    {
        String rs_binary = General.register_to_binary(rs); // "$r1" -> "1111"
        String rt_binary = General.register_to_binary(rt);
        String rd_binary = General.register_to_binary(rd);

        String com = SPECIAL + rs_binary + rt_binary + rd_binary + ZERO + FUNC;

        int com_dec = Integer.parseInt(com,2); // String(Binary) -> Int(dec)

        String com_hex = Integer.toHexString(com_dec); // Int(dec) -> String(hex)

        com_hex = General.pad_hex(com_hex, 8); // padding

        return com_hex;
    }

    public String[] getInstruction()
    {
        String[] s = {SPECIAL, rs, rt, rd, ZERO, FUNC};
        return s;
    }
}
