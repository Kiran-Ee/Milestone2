package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

//op-code: AND rd, rs, rt
//Machine: SPECIAL, rs ,rt , rd, ZERO, AND

class AndTest {
    String[] instr = {"and", "$t0", "$s1", "$s2"}; //format and, rd, rs, rt
    String[] instr1 = {"and", "$v0", "$a0", "$s1"};
    String[] instr2 = {"and", "$t5", "$t8", "$t4"};
    String[] instr3 = {"and", "$6", "$17", "$11"}; //"and", "$a2", "$s1", "$t3"

    @Test
    void good_variable_setting() {
        And a = new And(instr);
        assertEquals(a.getInstruction()[3], instr[1]); // rd = rd
        assertEquals(a.getInstruction()[1], instr[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr[3]); // rt = rt
    }
    @Test
    void good_variable_setting1() {
        And a = new And(instr1);
        assertEquals(a.getInstruction()[3], instr1[1]); // rd = rd
        assertEquals(a.getInstruction()[1], instr1[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr1[3]); // rt = rt
    }
    @Test
    void good_variable_setting2() {
        And a = new And(instr2);
        assertEquals(a.getInstruction()[3], instr2[1]); // rd = rd
        assertEquals(a.getInstruction()[1], instr2[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr2[3]); // rt = rt
    }
    @Test
    void good_variable_setting3() {
        And a = new And(instr3);
        assertEquals(a.getInstruction()[3], instr3[1]); // rd = rd
        assertEquals(a.getInstruction()[1], instr3[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr3[3]); // rt = rt
    }
    @Test
    void return_correct_hex_instr() {
        And a = new And(instr);
        assertEquals("02324024", a.get_hex()); //leading 0 missing
    }
    @Test
    void return_correct_hex_instr1(){
        And a = new And(instr1);
        assertEquals("00911024", a.get_hex()); //leading 0 m missing
    }
    @Test
    void return_correct_hex_instr2(){
        And a = new And(instr2);
        assertEquals("030c6824", a.get_hex()); // 0 missing
    }
    @Test
    void return_correct_hex_instr3(){
        And a = new And(instr3);
        assertEquals("022b3024", a.get_hex());
    }
}