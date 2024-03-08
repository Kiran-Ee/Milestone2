package Operations;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

//op-code-BEQ, rs, rt, offset
//Machine-BEQ, rs, rt, offset
//-Test->Pass
public class BeqTest {
    String[] valid_instr = {"beq", "$s0", "$s1", "32"}; //Format: beq, rs, rt, offset
    String[] valid_instr1 = {"beq", "$t0", "$zero", "4"};
    String[] valid_instr2 = {"beq", "$20", "$13", "10"}; //"beq", "$s4", "$t5", "10"
    String[] zero_offset = {"000100", "$s1", "$s2", "000000"};
    String[] negative_offset = {"beq", "$s1", "$s3", "-200"}; //-proj instruction said will test positive val only

    String[] prof1 = {"beq", "$s0", "$a0", "0x34"}; // MC: 000100 10000 00100 0000000000110100

    @Test
    public void good_variable_settings1() {
        Beq beq = new Beq(valid_instr);
        assertEquals(beq.getInstruction()[1], valid_instr[1]);//rs
        assertEquals(beq.getInstruction()[2], valid_instr[2]); //rt
        assertEquals(beq.getInstruction()[3], valid_instr[3]); //offset
    }

    @Test
    public void good_variable_settings2() {
        Beq b = new Beq(valid_instr1);
        assertEquals(b.getInstruction()[1], valid_instr1[1]);//rs
        assertEquals(b.getInstruction()[2], valid_instr1[2]); //rt
        assertEquals(b.getInstruction()[3], valid_instr1[3]); //offset
    }

    @Test
    public void good_variable_settings3() {
        Beq b = new Beq(valid_instr2);
        assertEquals(b.getInstruction()[1], valid_instr2[1]);//rs
        assertEquals(b.getInstruction()[2], valid_instr2[2]); //rt
        assertEquals(b.getInstruction()[3], valid_instr2[3]); //offset
    }

    @Test
    public void good_variable_settings4() {
        Beq b = new Beq(zero_offset);
        assertEquals(b.getInstruction()[1], zero_offset[1]);//rs
        assertEquals(b.getInstruction()[2], zero_offset[2]); //rt
        assertEquals(b.getInstruction()[3], zero_offset[3]); //offset
    }

    @Test
    public void good_variable_settings5() {
        Beq b = new Beq(negative_offset);
        assertEquals(b.getInstruction()[1], negative_offset[1]);//rs
        assertEquals(b.getInstruction()[2], negative_offset[2]); //rt
        assertEquals(b.getInstruction()[3], negative_offset[3]); //offset
    }

    @Test
    public void good_variable_settings7() {
        Beq b = new Beq(prof1);
        assertEquals(b.getInstruction()[1], prof1[1]);//rs
        assertEquals(b.getInstruction()[2], prof1[2]); //rt
        assertEquals(b.getInstruction()[3], prof1[3]); //offset
    }

    @Test
    public void return_correct_hex_valid_instr() {
        Beq b = new Beq(valid_instr);
        assertEquals("12110020", b.get_hex());
    }

    @Test
    public void return_correct_hex_valid_instr1() {
        Beq b = new Beq(valid_instr1);
        assertEquals("11000004", b.get_hex());
    }

    @Test
    public void return_correct_hex_valid_instr2() {
        Beq b = new Beq(valid_instr2);
        assertEquals("128d000a", b.get_hex());
    }

    @Test
    public void return_correct_hex_zero_offset() {
        Beq b = new Beq(zero_offset);
        assertEquals("12320000", b.get_hex());
    }

    @Test
    public void return_correct_hex_negative_offset() { //"beq", "$s1", "$s3", "-200"
        Beq b = new Beq(negative_offset);
        assertEquals("1233ff38", b.get_hex());
    }

//    @Test
//    public void return_prof1() { //"beq", "$s1", "$s3", "-200"
//        Beq b = new Beq(prof1);
//        assertEquals("10900034", b.get_hex()); // HIS INSTRUCTION SEEMS WRONG???
//    }
}