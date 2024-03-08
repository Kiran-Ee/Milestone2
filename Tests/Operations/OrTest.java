package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Format or, rd, rs, rt
//Machine: SPECIAL rs rt rd ZERO OR
public class OrTest {
    String[] inst = {"or", "$t0", "$t1", "$t2"};
    String[] instr = {"or", "$t8", "$s0", "$t9"};
    String[] instr1 = {"or", "$a0", "$t5", "$t3"};
    String[] instr2 = {"or", "$0", "$zero", "$0"};

    @Test
    public void good_variable_setting() {
        Or or = new Or(inst);
        assertEquals(or.getInstruction()[3], inst[1]); //rd
        assertEquals(or.getInstruction()[1], inst[2]); //rs
        assertEquals(or.getInstruction()[2], inst[3]); //rt
    }

    @Test
    public void good_variable_setting1() {
        Or or = new Or(instr);
        assertEquals(or.getInstruction()[3], instr[1]); //rd
        assertEquals(or.getInstruction()[1], instr[2]); //rs
        assertEquals(or.getInstruction()[2], instr[3]); //rt
    }

    @Test
    public void good_variable_setting2() {
        Or or = new Or(instr1);
        assertEquals(or.getInstruction()[3], instr1[1]); //rd
        assertEquals(or.getInstruction()[1], instr1[2]); //rs
        assertEquals(or.getInstruction()[2], instr1[3]); //rt
    }

    @Test
    public void good_variable_setting3() {
        Or or = new Or(instr2);
        assertEquals(or.getInstruction()[3], instr2[1]); //rd
        assertEquals(or.getInstruction()[1], instr2[2]); //rs
        assertEquals(or.getInstruction()[2], instr2[3]); //rt
    }

    @Test
    public void return_correct_hex_inst() {
        Or or = new Or(inst);
        assertEquals("012a4025", or.get_hex());
    }

    @Test
    public void return_correct_hex_instr() {
        Or or = new Or(inst);
        assertEquals("012a4025", or.get_hex()); // instr1
    }

    @Test
    public void return_correct_hex_instr1() {
        Or or = new Or(instr1);
        assertEquals("01ab2025", or.get_hex());
    }

    @Test
    public void return_correct_hex_instr2() {
        Or or = new Or(instr2);
        assertEquals("00000025", or.get_hex());
    }
}
