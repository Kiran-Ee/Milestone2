package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

// op-code: addiu, rt, rs, immediate
// machine-code: sp, rs, rt, immediate
// project says only takes positive values

//Fixed
class AddiuTest {
    String[] instr1 = {"addiu", "$s0", "$s1", "10"};
    String[] instr1_hex = {"addiu", "$t0", "$t0", "0xA"};

    String[] instr2 = {"addiu", "$t0", "$t0", "0"};
    String[] instr2_hex = {"addiu", "$t0", "$t0", "0x0"};

    String[] instr3 = {"addiu", "$t3", "$t2", "65535"};
    String[] instr3_hex = {"addiu", "$t3", "$t2", "0xFFFF"};

    // $#
    String[] instr4_numReg = {"addiu", "$3", "$0", "0x0000"};


    // Testing the variables set up correctly
    @Test
    void good_variable_setting1() {
        Addiu a = new Addiu(instr1);
        assertEquals(a.getInstruction()[1], instr1[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr1[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr1[3]); // immediate = immediate
    }
    @Test
    void good_variable_setting2() {
        Addiu a = new Addiu(instr1_hex);
        assertEquals(a.getInstruction()[1], instr1_hex[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr1_hex[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr1_hex[3]); // immediate = immediate
    }
    @Test
    void good_variable_setting3() {
        Addiu a = new Addiu(instr2);
        assertEquals(a.getInstruction()[1], instr2[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr2[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr2[3]); // immediate = immediate
    }
    @Test
    void good_variable_setting4() {
        Addiu a = new Addiu(instr2_hex);
        assertEquals(a.getInstruction()[1], instr2_hex[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr2_hex[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr2_hex[3]); // immediate = immediate
    }
    @Test
    void good_variable_setting5() {
        Addiu a = new Addiu(instr3);
        assertEquals(a.getInstruction()[1], instr3[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr3[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr3[3]); // immediate = immediate
    }
    @Test
    void good_variable_setting6() {
        Addiu a = new Addiu(instr3_hex);
        assertEquals(a.getInstruction()[1], instr3_hex[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr3_hex[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr3_hex[3]); // immediate = immediate
    }

    @Test
    void good_variable_setting7() {
        Addiu a = new Addiu(instr4_numReg);
        assertEquals(a.getInstruction()[1], instr4_numReg[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr4_numReg[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr4_numReg[3]); // immediate = immediate
    }

    @Test
    void return_correct_hex_instr1() {
        Addiu a = new Addiu(instr1);
        assertEquals("2630000a", a.get_hex());
    }

    @Test
    void return_correct_hex_instr1Hex() {
        Addiu a = new Addiu(instr1_hex);
        assertEquals("2508000a", a.get_hex());
    }

    @Test
    void return_correct_hex_instr2() {
        Addiu a = new Addiu(instr2);
        assertEquals("25080000", a.get_hex());
    }

    @Test
    void return_correct_hex_instr2Hex() {
        Addiu a = new Addiu(instr2_hex);
        assertEquals("25080000", a.get_hex());
    }

    @Test
    void return_correct_hex_instr3() {
        Addiu a = new Addiu(instr3);
        assertEquals("254bffff", a.get_hex());
    }

    @Test
    void return_correct_hex_instr3Hex() {
        Addiu a = new Addiu(instr3_hex);
        assertEquals("254bffff", a.get_hex());
    }

    @Test
    void return_correct_hex_instr4NumReg() {
        Addiu a = new Addiu(instr4_numReg);
        assertEquals("24030000", a.get_hex());
    }
}