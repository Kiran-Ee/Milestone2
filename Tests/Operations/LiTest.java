package Operations;

import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;

import static org.junit.jupiter.api.Assertions.*;

//Format: li register immediate
public class LiTest {
    String[] instr = {"li", "$s0", "1"};
    String[] instr_neg = {"li", "$s0", "-1"};
    String[] instr_hex = {"li", "$s0", "0x3BF20"};

    String[] instr_max = {"li", "$t0", "0xFFFFFFFF"};

    String[] instr_short = {"li", "$t0"};
    String[] instr_bad = {"li", "$t0", "Apples"};

    //LI - LUI - Copy upper 1/2 bits, ORI copy lower 1/2 bits
    @Test
    public void testRegularSuccess() {
        Li li = new Li(instr);
    }

    @Test
    public void testMaxSuccess() {
        Li li = new Li(instr_max);

    }

    @Test
    public void testHexSuccess() {
        Li li = new Li(instr_hex);

    }

    @Test
    public void testNegSuccess() {
        Li li = new Li(instr_neg);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testShortFails()
    {
        Li li = new Li(instr_short);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadFails()
    {
        Li li = new Li(instr_bad);
    }
}
