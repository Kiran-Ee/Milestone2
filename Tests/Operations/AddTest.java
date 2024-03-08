package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

// op-code: add, rd, rs, rt
// machine-code: sp, rs, rt, rd, 0, add
public class AddTest {
    String[] instr1 = {"add", "$s0", "$s1", "$t7"};
    String[] instr2 = {"add", "$t0", "$t0", "$t0"};
    String[] instr3 = {"add", "$t3", "$t2", "$t1"};

    String[] instr4 = {"add", "$3", "$2", "$1"};

    // Testing the variables set up correctly
    @Test
    void good_variable_setting1() { // "add", "$s0", "$s1", "t7"
        Add a = new Add(instr1);
        assertEquals(a.getInstruction()[1], instr1[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr1[3]); // rt = rt
        assertEquals(a.getInstruction()[3], instr1[1]); // rd = rd
    }

    // insert good_variable_setting for instr2 & instr3

    @Test
    void good_variable_setting2() {
        Add a = new Add(instr2);
        assertEquals(a.getInstruction()[1], instr2[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr2[3]); // rt = rt
        assertEquals(a.getInstruction()[3], instr2[1]); // rd = rd
    }

    @Test
    void good_variable_setting3() {
        Add a = new Add(instr3);
        assertEquals(a.getInstruction()[1], instr3[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr3[3]); // rt = rt
        assertEquals(a.getInstruction()[3], instr3[1]); // rd = rd
    }


    @Test
    void good_variable_setting4() {
        Add a = new Add(instr4);
        assertEquals(a.getInstruction()[1], instr4[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr4[3]); // rt = rt
        assertEquals(a.getInstruction()[3], instr4[1]); // rd = rd
    }

    @Test
    void return_correct_hex_instr1() {
        Add a = new Add(instr1);
        assertEquals("022f8020", a.get_hex());
    }

    @Test
    void return_correct_hex_instr2() {
        Add a = new Add(instr2);
        assertEquals("01084020", a.get_hex());
    }

    @Test
    void return_correct_hex_instr3() {
        Add a = new Add(instr3);
        assertEquals("01495820", a.get_hex());
    }


    @Test
    void return_correct_hex_instr4() {
        Add a = new Add(instr4);
        assertEquals("00411820", a.get_hex());
    }
} //end: class addTest