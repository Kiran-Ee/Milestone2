package Operations;

import Util.General;

public class Lw implements Operation {
    private final String LW = "100011";
    private String base = "";
    private String rt = "";
    private String offset = "";

    /*
    Format:
LW rt, offset(base)
["lw","rt","offset(base)"]
 Machine Code Format:
    [LW(6: 100011), base(5), rt(5), offset(16)]
Desc: rt <--memory[base+offset]
     */
    public Lw(String[] cleaned_instr) {
        rt = cleaned_instr[1];
        base = cleaned_instr[3];
        offset = cleaned_instr[2];
    }

    public String get_hex() {
        String base_binary = General.register_to_binary(base);
        String rt_binary = General.register_to_binary(rt);
        String offset_hex = General.immediate_to_hex(offset, true); // assuming signed*

        String com = LW + base_binary + rt_binary;

        int com_dec = Integer.parseInt(com, 2);

        String com_hex = Integer.toHexString(com_dec) + offset_hex;

        return com_hex;
    }

    public String[] getInstruction() {
        String[] inst = {LW, base, rt, offset};
        return inst;
    }
}
