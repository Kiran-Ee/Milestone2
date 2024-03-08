package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

//completed
//op-code ANDI rt, rs, immediate
//Machine ANDI rs rt immediate
class AndITest {
    String[] instr1 = {"andi", "$t0", "$s0", "255"}; //Format ANDI, rt, rs, immediate
    String[] instr2 = {"andi", "$s4", "$s2", "17119"};
    String[] instr3 = {"andi", "$t9", "$s0", "5995"};
    String[] instr_hex = {"andi", "$t0", "$s1", "0x1F4"};
    String[] instr_hex1 = {"andi", "$t0", "$s2", "0x0"};
    String[] instr4 = {"andi", "$2", "$5", "4"};
    String[] neg_imm = {"andi", "$v0", "$s2", "-25"}; //neg.sig only


    @Test
    void good_variable_setting1() {
        AndI a = new AndI(instr1);
        assertEquals(a.getInstruction()[1], instr1[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr1[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr1[3]); // immediate = immediate
    }
    @Test
    void good_variable_setting2() {
        AndI a = new AndI(instr2);
        assertEquals(a.getInstruction()[1], instr2[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr2[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr2[3]); // immediate = immediate
    }

    @Test
    void good_variable_setting3() {
        AndI a = new AndI(instr3);
        assertEquals(a.getInstruction()[1], instr3[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr3[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr3[3]); // immediate = immediate
    }

    @Test
    void good_variable_setting4() {
        AndI a = new AndI(instr_hex);
        assertEquals(a.getInstruction()[1], instr_hex[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr_hex[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr_hex[3]); // immediate = immediate
    }
    @Test
    void good_variable_setting5() {
        AndI a = new AndI(instr_hex1);
        assertEquals(a.getInstruction()[1], instr_hex1[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr_hex1[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr_hex1[3]); // immediate = immediate
    }
    @Test
    void good_variable_setting6() {
        AndI a = new AndI(instr4);
        assertEquals(a.getInstruction()[1], instr4[2]); // rs = rs
        assertEquals(a.getInstruction()[2], instr4[1]); // rt = rt
        assertEquals(a.getInstruction()[3], instr4[3]); // immediate = immediate
    }
    @Test
    void good_variable_setting7() {
        AndI a = new AndI(neg_imm);
        assertEquals(a.getInstruction()[1], neg_imm[2]); // rs = rs
        assertEquals(a.getInstruction()[2], neg_imm[1]); // rt = rt
        assertEquals(a.getInstruction()[3], neg_imm[3]); // immediate = immediate
    }

    @Test
    void return_correct_hex_instr1() {
        AndI a = new AndI(instr1);
        assertEquals("320800ff", a.get_hex());
    }
    @Test
    void return_correct_hex_instr2() {
        AndI a = new AndI(instr2);
        assertEquals("325442df", a.get_hex());
    }

    @Test
    void return_correct_hex_instr3() {
        AndI a = new AndI(instr3); //"andi", "$t9", "$s0", "59956"
        assertEquals("3219176b", a.get_hex()); //3219176B
    }

    @Test
    void return_correct_hex_instr_hex() {
        AndI a = new AndI(instr_hex); //"andi", "t0", "$s1", "0x1F4"
        assertEquals("322801f4", a.get_hex()); //322801F4
    }

    @Test
    void return_correct_hex_instr_hex1() {
        AndI a = new AndI(instr_hex1); //andi", "t0", "$s2", "0x0
        assertEquals("32480000", a.get_hex()); //32480000
    }

    @Test
    void return_correct_hex_instr4() {
        AndI a = new AndI(instr4); //"andi", "$2", "$5", "4" ($v0, $a1, 4
        assertEquals("30a20004", a.get_hex()); //30A20004
    }
    @Test
    void return_correct_hex_neg_imm() {
        AndI a = new AndI(neg_imm); //"andi", "$v0", "$s2", "-25"
        assertEquals("3242ffe7", a.get_hex());
    }
}