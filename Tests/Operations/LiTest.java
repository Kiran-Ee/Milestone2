package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Josiah
// IF ONLY RETURNING 1 INSTRUCTION (addiu) THEN MAKE THE SECOND ELEMENT "" ... this is for the text_to_hex_
class LiTest {
    String[] li1 = new String[]{"li", "$a0", "0x1"};
    String[] li1_hex_arr = new String[]{"24040001", ""};
    String[] li2 = new String[]{"li", "$8", "0x10000001"};
    String[] li2_hex_arr = new String[]{"3c011000", "34280001"};
    String[] li3 = new String[]{"li", "$s0", "0xFFFFFFFF"};
    String[] li3_hex_arr = new String[]{"2410ffff", ""};
    String[] li4 = new String[]{"li", "$a0", "1"};
    String[] li4_hex_arr = new String[]{"24040001", ""};
    String[] li5 = new String[]{"li", "$a0", "65535"};
    String[] li5_hex_arr = new String[]{"3404ffff", ""};
    String[] li6 = new String[]{"li", "$a0", "4294967295"};
    String[] li6_hex_arr = new String[]{"2404ffff", ""};
    @Test
    public void setli1(){
        Li li = new Li(li1);
        assertArrayEquals(li1_hex_arr, li.get_hex());
    }
    @Test
    public void setli2(){
        Li li = new Li(li2);
        assertArrayEquals(li2_hex_arr, li.get_hex());
    }
    @Test
    public void setli3(){
        Li li = new Li(li3);
        assertArrayEquals(li3_hex_arr, li.get_hex());
    }
    @Test
    public void setLi4(){
        Li li = new Li(li4);
        assertArrayEquals(li4_hex_arr, li.get_hex());
    }
    @Test
    public void setli5(){
        Li li = new Li(li5);
        assertArrayEquals(li5_hex_arr, li.get_hex());
    }
    @Test
    public void setli6(){
        Li li = new Li(li6);
        assertArrayEquals(li6_hex_arr, li.get_hex());
    }
}