package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Format: Sub, rd, rs, rt
//Machine: SPECIAL rs rt rd ZERO SUB
public class SubTest {
    String[] valid_instr = {"sub", "$t0", "$t1", "$t2"};
    String[] valid_inst1 = {"sub", "$s0", "$s1", "$s2"};
    String[] valid_inst2 = {"sub", "$a0", "$zero", "$a2"};

    String[] valid_inst3 = {"sub", "$1", "$1", "$1"};

    @Test
    public void good_variable_setting() {
        Sub sub = new Sub(valid_instr);
        assertEquals(sub.getInstruction()[3], valid_instr[1]); //rd
        assertEquals(sub.getInstruction()[1], valid_instr[2]); //rs
        assertEquals(sub.getInstruction()[2], valid_instr[3]); //rt
    }

    @Test
    public void good_variable_setting1() {
        Sub sub = new Sub(valid_inst1);
        assertEquals(sub.getInstruction()[3], valid_inst1[1]); //rd
        assertEquals(sub.getInstruction()[1], valid_inst1[2]); //rs
        assertEquals(sub.getInstruction()[2], valid_inst1[3]); //rt
    }

    @Test
    public void good_variable_setting2() {
        Sub sub = new Sub(valid_inst2);
        assertEquals(sub.getInstruction()[3], valid_inst2[1]); //rd
        assertEquals(sub.getInstruction()[1], valid_inst2[2]); //rs
        assertEquals(sub.getInstruction()[2], valid_inst2[3]); //rt
    }

    @Test
    public void good_variable_setting3() {
        Sub sub = new Sub(valid_inst3);
        assertEquals(sub.getInstruction()[3], valid_inst3[1]); //rd
        assertEquals(sub.getInstruction()[1], valid_inst3[2]); //rs
        assertEquals(sub.getInstruction()[2], valid_inst3[3]); //rt
    }

    @Test
    public void return_correct_hex_valid_instr() {
        Sub sub = new Sub(valid_instr);
        assertEquals("012a4022", sub.get_hex());
    }

    @Test
    public void return_correct_hex_valid_inst1() {
        Sub sub = new Sub(valid_inst1);
        assertEquals("02328022", sub.get_hex());
    }

    @Test
    public void return_correct_hex_valid_inst2() {
        Sub sub = new Sub(valid_inst2);
        assertEquals("00062022", sub.get_hex());
    }

    @Test
    public void return_correct_hex_valid_inst3() {
        Sub sub = new Sub(valid_inst3);
        assertEquals("00210822", sub.get_hex());
    }
}
