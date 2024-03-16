package Operations;

import static org.junit.jupiter.api.Assertions.*;

// Josiah
class LiTest {
    String[] li1 = new String[]{"li", "$a0", "1"};
    String[] li1_hex_arr = new String[]{"lui $1, 00000000", "ori $4, $1, 00000001"};
    String[] li2 = new String[]{"li", "$8", "10000001"};
    String[] li2_hex_arr = new String[]{"lui $1, 00001000", "ori $8, $1, 00000001"};
    String[] li3 = new String[]{"li", "$s0", "FFFFFFFF"};
    String[] li3_hex_arr = new String[]{"lui $1, 0000FFFF", "ori $16, $1, 0000FFFF"};


    @Test
    public void setli1(){
        li li = new li(li1);
        assertArrayEquals(li1_hex_arr, li.get_hex());
    }

    @Test
    public void setli1(){
        li li = new li(li2);
        assertArrayEquals(li2_hex_arr, li.get_hex());
    }

    @Test
    public void setli1(){
        li li = new li(li3);
        assertArrayEquals(li3_hex_arr, li.get_hex());
    }
}