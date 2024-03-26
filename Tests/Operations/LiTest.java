package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Josiah
class LiTest { //Only takes Hex values
    String[] li1 = new String[]{"li", "$a0", "0x1"};
    String[] li1_hex_arr = new String[]{"3c010000", "34240001"};
    String[] li2 = new String[]{"li", "$8", "0x10000001"};
    String[] li2_hex_arr = new String[]{"3c011000", "34280001"};
    String[] li3 = new String[]{"li", "$s0", "0xFFFFFFFF"};
    String[] li3_hex_arr = new String[]{"3c01ffff", "3430ffff"};
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
}