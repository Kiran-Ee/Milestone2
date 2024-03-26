package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// THIS NEEDS TO BE FIXED TO ONLY ALLOW ADDRESSES!

//Format: J target
//Machine: J, instr_index(26)
public class JTest {
    String[] instr = {"j", "2500"}; //format: j, target
    String[] instr_hex = {"j", "0x1F4"};
    @Test
    public void good_variable_setting1()
    {
        j jump = new j(instr);
        assertEquals(jump.getInstruction()[1], instr[1]); //instr_index
    }
    @Test
    public void good_variable_setting2()
    {
        j jump = new j(instr_hex);
        //assertEquals(jump.getInstruction()[0], instr_hex[0]); //memonic = "j"
        assertEquals(jump.getInstruction()[1], instr_hex[1]); //instr_index
    }

    @Test
    public void return_correct_hex_instr()
    {
        j jump = new j(instr);
        String hex = jump.get_hex();
        assertEquals("080009c4", hex); //080009C4
    }
    @Test
    public void return_correct_hex_instr_hex()
    {
        j jump = new j(instr_hex);
        String hex = jump.get_hex();
        assertEquals("080001f4", hex);
    }
}
