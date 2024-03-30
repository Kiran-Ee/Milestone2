package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Josiah
class LiTest { //Only takes Hex values
    String[] li1 = new String[]{"li", "$a0", "0x1"};
    String[] li1_hex_arr = new String[]{"24040001", ""};

    String[] li2 = new String[]{"li", "$8", "0x10000001"};
    String[] li2_hex_arr = new String[]{"3c011000", "34280001"};

    String[] li3 = new String[]{"li", "$s0", "0xFFFFFFFF"};
    String[] li3_hex_arr = new String[]{"3c01ffff", "3430ffff"};

    String[] li4 = new String[]{"li", "$a0", "1"};
    String[] li4_hex_arr = new String[]{"24040001", ""};

    String[] li5 = new String[]{"li", "$a0", "65534"};
    String[] li5_hex_arr = new String[]{"3404fffe", ""};

    String[] li6 = new String[]{"li", "$a0", "32767"};
    String[] li6_hex_arr = new String[]{"24047fff", ""};

    String[] li7 = new String[]{"li", "$a0", "-32768"};
    String[] li7_hex_arr = new String[]{"24048000", ""};

    String[] li8 = new String[]{"li", "$a0", "-32769"};
    String[] li8_hex_arr = new String[]{"3c01ffff","34247fff"};

    String[] li9 = new String[]{"li", "$a0", "42"};
    String[] li9_hex_arr = new String[]{"2411002a",""};

    // ADD CASE FOR "ORI" AND ASK JOSIAH

    @Test
    public void setli1(){
        Li li = new Li(li1);
        assertArrayEquals(li1_hex_arr, li.get_hex());
    }
    @Test
    public void setli2(){
        Li li = new Li(li2);
        assertArrayEquals(li2_hex_arr, li.get_hex()); //1st element is wrong
    }
    @Test
    public void setli3(){
        Li li = new Li(li3);
        assertArrayEquals(li3_hex_arr, li.get_hex());
    }
    @Test
    public void setli4(){
        Li li = new Li(li4);
        assertArrayEquals(li4_hex_arr, li.get_hex());
    }
    @Test
    public void setLi5(){
        Li li = new Li(li5);
        assertArrayEquals(li5_hex_arr, li.get_hex());
    }
    @Test
    public void setli6(){
        Li li = new Li(li6);
        assertArrayEquals(li6_hex_arr, li.get_hex());
    }
    @Test
    public void setLi7(){
        Li li = new Li(li7);
        assertArrayEquals(li7_hex_arr, li.get_hex());
    }
    @Test
    public void setli8(){
        Li li = new Li(li8);
        assertArrayEquals(li8_hex_arr, li.get_hex());
    }
    @Test
    public void setli9(){
        Li li = new Li(li9);
        assertArrayEquals(li9_hex_arr, li.get_hex());
    }
}