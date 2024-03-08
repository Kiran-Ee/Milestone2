package Operations;
import Util.General;

public class Slt implements Operation{
    private final String SPECIAL = "000000";
    private String rs = "";
    private String rt = "";
    private String rd = "";
    private String ZERO = "00000";
    private String SLT = "101010";

    public Slt(String[] cleaned_instr)
    {
        rs = cleaned_instr[2];
        rt = cleaned_instr[3];
        rd = cleaned_instr[1];
    }

    public String get_hex() {
        String rs_binary = General.register_to_binary(rs);
        String rt_binary = General.register_to_binary(rt);
        String rd_binary = General.register_to_binary(rd);

        String com = SPECIAL + rs_binary + rt_binary + rd_binary + ZERO + SLT;

        int com_dec = Integer.parseInt(com,2); // String(Binary) -> Int(dec)

        String com_hex = Integer.toHexString(com_dec); // Int(dec) -> String(hex)

        com_hex = General.pad_hex(com_hex, 8); // padding

        return com_hex;
    }
    public String[] getInstruction()
    {
        return new String[]{SPECIAL, rs, rt, rd, ZERO, SLT};
    }
}
