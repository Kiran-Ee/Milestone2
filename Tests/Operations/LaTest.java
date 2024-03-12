package Operations;

import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;

import static org.junit.jupiter.api.Assertions.*;

//  lui $at, 4097 (0x1001 → upper 16 bits of $at).
//  ori $a0,$at,disp
public class LaTest {
    String[] instr = {"la", "$s0", "4000"};
    String[] instr_hex = {"la", "$s0", "0x12345678"};
    //LI - LUI - Copy upper 1/2 bits, ORI copy lower 1/2 bits
    @Test
    public void testRegularSuccess()
    {

    }

    @Test
    public void testHexSuccess()
    {

    }
}
