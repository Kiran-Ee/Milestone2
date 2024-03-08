package Operations;

import Util.General;

public class Syscall implements Operation{
    private final String SPECIAL = "000000";
    // private String code;
    private final String SYSCALL = "001100";

    public Syscall(String[] cleaned_instr) {
        if(cleaned_instr.length < 1){
            throw new IllegalArgumentException("At least one argument expected for code");
        }
        // code = cleaned_instr[0]; // the argument would just be "syscall"
    }
    public String get_hex() {
        return "0000000c"; // this is what he was returning everytime for some reason?
    }
    public String[] getInstruction()
    {
        return new String[]{SPECIAL, SYSCALL};
    }
}
