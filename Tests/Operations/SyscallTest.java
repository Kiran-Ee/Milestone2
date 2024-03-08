package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SyscallTest {
    String[] instr = {"SYSCALL"}; //Form: SYSCALL

    @Test
    public void goodConstructor() {
        assertDoesNotThrow(() -> new Syscall(instr));
    }

//    @Test // don't think we need to do this since it doesn't take any inputs.
//    public void goodVariableSetting() {
//        Syscall sys = new Syscall(instr);
//        assertEquals(sys.getInstruction()[1], "001100");
//    }

    @Test
    public void return_correct_hex_instr() {
        Syscall sys = new Syscall(instr);
        assertEquals("0000000c", sys.get_hex());
    }
}
