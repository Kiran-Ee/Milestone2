package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

//Format: ORI rt, rs, immediate
//machine: ORI rs rt immediate
public class OriTest {
    String[] instr = {"ori", "$t0", "$s1", "10"}; // Format ori, rt, rs, immediate
    String[] instr_imm_hex = {"ori", "$t0", "$s5", "0xFFFF"};

    String[] instr_max = {"ori", "$t0", "$s5", "32767"};
    String[] instr_neg_min = {"ori", "$t0", "$s5", "-32768"};

    String[] instr_invalid = {"ori", "$s0", "$zero", "65535"};


    @Test
    public void good_variable_setting() {
        Ori ori = new Ori(instr);
        assertEquals(ori.getInstruction()[1], instr[2]); //rs
        assertEquals(ori.getInstruction()[2], instr[1]); //rt
        assertEquals(ori.getInstruction()[3], instr[3]); //immediate
    }

    @Test
    public void good_variable_setting1() {
        Ori ori = new Ori(instr_invalid);
        assertEquals(ori.getInstruction()[1], instr_invalid[2]);//rs
        assertEquals(ori.getInstruction()[2], instr_invalid[1]); //rt
        assertEquals(ori.getInstruction()[3], instr_invalid[3]); //immediate
    }

    @Test
    public void good_variable_setting2() {
        Ori ori = new Ori(instr_imm_hex);
        assertEquals(ori.getInstruction()[1], instr_imm_hex[2]); //rs
        assertEquals(ori.getInstruction()[2], instr_imm_hex[1]); //rt
        assertEquals(ori.getInstruction()[3], instr_imm_hex[3]); //immediate
    }

    @Test
    public void good_variable_setting3() {
        Ori ori = new Ori(instr_max);
        assertEquals(ori.getInstruction()[1], instr_max[2]);//rs
        assertEquals(ori.getInstruction()[2], instr_max[1]); //rt
        assertEquals(ori.getInstruction()[3], instr_max[3]); //immediate
    }

    @Test
    public void return_correct_hex_instr() {
        Ori ori = new Ori(instr);
        assertEquals("3628000a", ori.get_hex());

    }

    @Test
    public void return_correct_hex_instr_imm_hex() {
        Ori ori = new Ori(instr_max);
        assertEquals("36a87fff", ori.get_hex());
    }

    @Test
    public void return_correct_hex_instr_imm_neg() {
        Ori ori = new Ori(instr_neg_min);
        assertEquals("36a88000", ori.get_hex());
    }

    @Test
    public void return_correct_hex_instr_instrInvalid() {
        Ori ori = new Ori(instr_invalid);
        assertThrows(IllegalArgumentException.class, () -> {
            ori.get_hex();
        });
    }
}
