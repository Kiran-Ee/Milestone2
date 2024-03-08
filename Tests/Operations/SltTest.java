package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Format: slt, rd, rs, rt
//Machine: SPECIAL rs rt rd ZERO SLT
public class SltTest {
    String[] instr = {"slt", "$t0", "$s1", "$s2"};
    String[] valid_instr = {"slt", "$t4", "$s7", "$s7"};
    String[] valid_instr1 = {"slt", "$s2", "$t5", "$a0"};
    String[] valid_instr2 = {"slt", "$2", "$5", "$0"};

    @Test
    public void good_variable_setting() {
        Slt slt = new Slt(instr);
        assertEquals(slt.getInstruction()[3], instr[1]); //rd
        assertEquals(slt.getInstruction()[1], instr[2]); //rs
        assertEquals(slt.getInstruction()[2], instr[3]); //rt
    }

    @Test
    public void good_variable_setting1() {
        Slt slt = new Slt(valid_instr);
        assertEquals(slt.getInstruction()[3], valid_instr[1]); //rd
        assertEquals(slt.getInstruction()[1], valid_instr[2]); //rs
        assertEquals(slt.getInstruction()[2], valid_instr[3]); //rt
    }

    @Test
    public void good_variable_setting2() {
        Slt slt = new Slt(valid_instr1);
        assertEquals(slt.getInstruction()[3], valid_instr1[1]); //rd
        assertEquals(slt.getInstruction()[1], valid_instr1[2]); //rs
        assertEquals(slt.getInstruction()[2], valid_instr1[3]); //rt
    }

    @Test
    public void good_variable_setting3() {
        Slt slt = new Slt(valid_instr2);
        assertEquals(slt.getInstruction()[3], valid_instr2[1]); //rd
        assertEquals(slt.getInstruction()[1], valid_instr2[2]); //rs
        assertEquals(slt.getInstruction()[2], valid_instr2[3]); //rt
    }

    @Test
    public void return_correct_hex_instr() {
        Slt slt = new Slt(instr);
        assertEquals("0232402a", slt.get_hex());
    }

    @Test
    public void return_correct_hex_valid_instr() {
        Slt slt = new Slt(valid_instr);
        assertEquals("02f7602a", slt.get_hex());
    }

    @Test
    public void return_correct_hex_valid_instr1() {
        Slt slt = new Slt(valid_instr1);
        assertEquals("01a4902a", slt.get_hex());
    }

    @Test
    public void return_correct_hex_valid_instr2() {
        Slt slt = new Slt(valid_instr2);
        assertEquals("00a0102a", slt.get_hex());
    }
}
