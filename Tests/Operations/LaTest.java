package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Josiah
class LaTest {
    String[] la1 = new String[]{"la", "$a0", "10010000"};
    String[] la1_hex_arr = new String[]{"3c011001", "34240000"};//"lui $1, 00001001", "ori $4, $1, 00000000"
    String[] la2 = new String[]{"la", "$8", "10010040"};
    String[] la2_hex_arr = new String[]{"3c011001", "34280040"};//"lui $1, 00001001", "ori $8, $1, 00000040"
    String[] la3 = new String[]{"la", "$s0", "10011900"};
    String[] la3_hex_arr = new String[]{"3c011001", "34301900"};//"lui $1, 00001001", "ori $16, $1, 00001900"

    String[] la4 = new String[]{"la", "$a0", "10010015"};
    String[] la4_hex_arr = new String[]{"3c011001", "34240015"};
    String[] la5 = new String[]{"la", "$a0", "1001002b"};
    String[] la5_hex_arr = new String[]{"3c011001", "3424002b"};

    @Test
    public void setla1(){
        La la = new La(la1);
        assertArrayEquals(la1_hex_arr, la.get_hex());
    }

    @Test
    public void setla2(){
        La la = new La(la2);
        assertArrayEquals(la2_hex_arr, la.get_hex());
    }

    @Test
    public void setla3(){
        La la = new La(la3);
        assertArrayEquals(la3_hex_arr, la.get_hex());
    }

    @Test
    public void setla4(){
        La la = new La(la4);
        assertArrayEquals(la4_hex_arr, la.get_hex());
    }
    @Test
    public void setla5(){
        La la = new La(la5);
        assertArrayEquals(la5_hex_arr, la.get_hex());
    }
}