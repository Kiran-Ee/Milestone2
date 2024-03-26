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
}