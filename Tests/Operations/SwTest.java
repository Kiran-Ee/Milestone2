package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwTest {
    String[] valid_instr = {"sw", "$t0", "100($s1)"}; //Format: sw,rt, offset(base)
    String[] valid_instr1 = {"sw", "$a0", "5($t3)"};
    String[] valid_instr2 = {"sw", "$zero", "5($a0)"};

    String[] instr_hex = {"sw", "$v0", "0xA($s4)"};
    String[] instr_neg = {"sw", "$v1", "-99($t4)"};

    @Test
    public void good_variable_setting()
    {
        Sw sw = new Sw(valid_instr);
        String[] result = sw.getInstruction();

        assertEquals("$t0", result[2]); //destination
        assertEquals("100", result[3]); //offset
        assertEquals("$s1", result[1]); //base
    }
    @Test
    public void good_variable_setting1()
    {
        Sw sw = new Sw(valid_instr1);
        String[] result = sw.getInstruction();
        assertEquals("$a0", result[2]); //destination
        assertEquals("5", result[3]); //offset
        assertEquals("$t3", result[1]); //base
    }
    @Test
    public void good_variable_setting2()
    {
        Sw sw = new Sw(valid_instr2);
        String[] result = sw.getInstruction(); // String[] instr = {"lw", "$t0", "10($s1)"};
        assertEquals("$zero", result[2]); //destination
        assertEquals("5", result[3]); //offset
        assertEquals("$a0", result[1]); //base
    }
    @Test
    public void return_correct_hex_instr()
    {
        Sw sw = new Sw(valid_instr);
        assertEquals("ae280064", sw.get_hex());
    }
    @Test
    public void return_correct_hex_instr1()
    {
        Sw sw = new Sw(valid_instr1);
        assertEquals("ad640005", sw.get_hex());
    }
    @Test
    public void return_correct_hex_instr2()
    {
        Sw sw = new Sw(valid_instr2);
        assertEquals("ac800005", sw.get_hex());
    }

    @Test
    public void return_correct_hex_instr_hex()
    {
        Sw sw = new Sw(instr_hex);
        assertEquals("ae82000a", sw.get_hex());
    }

    @Test
    public void return_correct_hex_instr_neg()
    {
        Sw sw = new Sw(instr_neg);
        assertEquals("ad83ff9d", sw.get_hex());
    }
}
