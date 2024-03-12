package Operations;

import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;

import static org.junit.jupiter.api.Assertions.*;
//blt reg reg label
public class BltTest {
    String[] instr = {"blt", "$t0", "$t1", "0x1F4"};
    String[] instr_i = {"blt", "$t0", "$t1", "2500"};
    String[] instr_short = {"blt", "$t0", "$t1"};
    String[] instr_bad = {"blt", "$t0", "$t1", "Apple"};

    @Test
    public void testRegularSuccess() {
        Blt Blt = new Blt(instr);
    }
    @Test
    public void testISuccess() {
        Blt Blt = new Blt(instr_i);
    }
    @Test
    public void testShortFails() {
        Blt Blt = new Blt(instr_short);
    }
    @Test
    public void testBadFails() {
        Blt Blt = new Blt(instr_bad);
    }
}