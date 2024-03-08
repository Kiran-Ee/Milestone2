package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LuiTest {
    String[] instr = {"lui", "$t0", "5"}; //format LUI, rt. immediate
    String[] instr1 = {"lui", "$s1", "4096"};
    String[] instr2 = {"lui", "$16", "10"}; //"lui", "$t6", "10"
    String[] instr_zero_imm = {"lui", "$t2", "0"};
    String[] instr_zero = {"lui", "$zero", "0"};
    String[] neg_imm = {"lui", "$a0", "-1"}; //load upper 16 bits of the register with a dec value -1

    @Test
    public void good_variable_setting1(){
        Lui lui = new Lui(instr);
        assertEquals(lui.getInstruction()[2], instr[1]); //rt
        assertEquals(lui.getInstruction()[3], instr[2]); //immediate
    }

    @Test
    public void good_variable_setting2(){
        Lui lui = new Lui(instr1);
        assertEquals(lui.getInstruction()[2], instr1[1]); //rt
        assertEquals(lui.getInstruction()[3], instr1[2]); //immediate
    }
    @Test
    public void good_variable_setting3(){
        Lui lui = new Lui(instr2);
        assertEquals(lui.getInstruction()[2], instr2[1]); //rt
        assertEquals(lui.getInstruction()[3], instr2[2]); //immediate
    }
    @Test
    public void good_variable_setting4(){
        Lui lui = new Lui(instr_zero_imm);
        assertEquals(lui.getInstruction()[2], instr_zero_imm[1]); //rt
        assertEquals(lui.getInstruction()[3], instr_zero_imm[2]); //immediate
    }
    @Test
    public void good_variable_setting5(){
        Lui lui = new Lui(instr_zero);
        assertEquals(lui.getInstruction()[2], instr_zero[1]); //rt
        assertEquals(lui.getInstruction()[3], instr_zero[2]); //immediate
    }

    @Test
    public void good_variable_setting(){
        Lui lui = new Lui(neg_imm);
        assertEquals(lui.getInstruction()[2], neg_imm[1]); //rt
        assertEquals(lui.getInstruction()[3], neg_imm[2]); //immediate
    }
    @Test
    public void return_correct_hex_instr()
    {
        Lui l = new Lui(instr);
        assertEquals("3c080005",l.get_hex());
    }
    @Test
    public void return_correct_hex_instr1()
    {
        Lui l = new Lui(instr1);
        assertEquals("3c111000",l.get_hex());
    }
    @Test
    public void return_correct_hex_instr2()
    {
        Lui l = new Lui(instr2);
        assertEquals("3c10000a",l.get_hex());//3C10000A-signed 0x00A; 3c0e000a ->0xA
    }
    @Test
    public void return_correct_hex_instr_zero_imm()
    {
        Lui l = new Lui(instr_zero_imm);
        assertEquals("3c0a0000",l.get_hex());
    }
    @Test
    public void return_correct_hex_instr_zero()
    {
        Lui l = new Lui(instr_zero);
        assertEquals("3c000000",l.get_hex());
    }
    @Test
    public void return_correct_hex_neg_imm()
    {
        Lui l = new Lui(neg_imm);
        assertEquals("3c04ffff",l.get_hex()); //3C04FFFF
    }
}
