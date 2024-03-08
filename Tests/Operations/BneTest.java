package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//op-code: bne, rs, rt, offset
//machine: bne, rs, rt, offset
public class BneTest {
    String[] valid_instr = {"bne", "$s0", "$s1", "15"}; // format: bne, rs, rt, offset cleaned instruction
    String[] valid_inst = {"000101", "$s0", "$s1", "0x0457"}; //not sure if valid inst
    String[] valid_instr1 = {"bne", "$zero", "$t4", "18"};
    String[] valid_instr2 = {"bne", "$6", "$11", "12"}; //"bne", "$a2", "$t3", "12"
    String[] neg_off = {"bne", "$t0", "$t1", "-16"};
    String[] zero_inst = {"bne", "$0", "$zero", "0"};
    @Test
    public void good_variable_setting1() {
        Bne bne = new Bne(valid_instr);
        assertEquals(bne.getInstruction()[1], valid_instr[1]); //rs
        assertEquals(bne.getInstruction()[2], valid_instr[2]); //rt
        assertEquals(bne.getInstruction()[3], valid_instr[3]); //offset
    }
    @Test
    public void good_variable_setting2() {
        Bne bne = new Bne(valid_inst);
        assertEquals(bne.getInstruction()[1], valid_inst[1]); //rs
        assertEquals(bne.getInstruction()[2], valid_inst[2]); //rt
        assertEquals(bne.getInstruction()[3], valid_inst[3]); //offset
    }
    @Test
    public void good_variable_setting3() {
        Bne bne = new Bne(valid_instr1);
        assertEquals(bne.getInstruction()[1], valid_instr1[1]); //rs
        assertEquals(bne.getInstruction()[2], valid_instr1[2]); //rt
        assertEquals(bne.getInstruction()[3], valid_instr1[3]); //offset
    }
    @Test
    public void good_variable_setting4() {
        Bne bne = new Bne(valid_instr2);
        assertEquals(bne.getInstruction()[1], valid_instr2[1]); //rs
        assertEquals(bne.getInstruction()[2], valid_instr2[2]); //rt
        assertEquals(bne.getInstruction()[3], valid_instr2[3]); //offset
    }
    @Test
    public void good_variable_setting5() {
        Bne bne = new Bne(neg_off);
        assertEquals(bne.getInstruction()[1], neg_off[1]); //rs
        assertEquals(bne.getInstruction()[2], neg_off[2]); //rt
        assertEquals(bne.getInstruction()[3], neg_off[3]); //offset
    }
    @Test
    public void good_variable_setting6() {
        Bne bne = new Bne(zero_inst);
        assertEquals(bne.getInstruction()[1], zero_inst[1]); //rs
        assertEquals(bne.getInstruction()[2], zero_inst[2]); //rt
        assertEquals(bne.getInstruction()[3], zero_inst[3]); //offset
    }

    @Test
    public void return_correct_hex_valid_instr() {
        Bne bne = new Bne(valid_instr);
        assertEquals("1611000f", bne.get_hex());
    }

    @Test
    public void return_correct_hex_valid_inst() {
        Bne bne = new Bne(valid_inst); //000101", "$s0", "$s1", "0x0457"
        assertEquals("16110457", bne.get_hex()); //16110457
    }
    @Test
    public void return_correct_hex_valid_instr1() {
        Bne bne = new Bne(valid_instr1);
        assertEquals("140c0012", bne.get_hex());
    }
    @Test
    public void return_correct_hex_valid_instr2() {
        Bne bne = new Bne(valid_instr2);
        assertEquals("14cb000c", bne.get_hex()); //14CB000C
    }
    @Test
    public void return_correct_hex_neg_off() {
        Bne bne = new Bne(neg_off);
        assertEquals("1509fff0", bne.get_hex()); //1509FFF0
    }
    @Test
    public void return_correct_hex_zero_inst() {
        Bne bne = new Bne(zero_inst);
        assertEquals("14000000", bne.get_hex()); //14000000
    }
}
