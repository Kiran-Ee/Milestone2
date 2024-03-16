package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Josiah
// Add 2 more tests for LA w/ memory addresses.
class LaTest {
    String[] la1 = new String[]{"la", "$a0", "10010000"};
    String[] la1_hex_arr = new String[]{"lui $1, 00001001", "ori $4, $1, 00000000"};

    @Test
    public void setla1(){
        La la = new La(la1);
        assertArrayEquals(la1_hex_arr, la.get_hex());
    }
}