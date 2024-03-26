package Operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class BltTest {
    LinkedHashMap<String, String[][]> text_hm = new LinkedHashMap<>();

    String[] blt1 = new String[]{"blt", "$a0", "$a1", "15"};
    String[] blt1_hex_arr = new String[]{"0085082a", "1420000f"};
    String[] blt2 = new String[]{"blt", "$4", "$a1", "0x0457"};
    String[] blt2_hex_arr = new String[]{"0085082a", "14200457"}; //"slt $1 $4 $5", "bne $1 $0 18"
    String[] blt3 = new String[]{"blt", "$a0", "$5", "12"};
    String[] blt3_hex_arr = new String[]{"0085082a", "1420000c"}; //"slt $1 $4 $5", "bne $1 $0 12"
    String[] blt4 = new String[]{"blt", "$4", "$5", "-16"};
    String[] blt4_hex_arr = new String[]{"0085082a", "1420fff0"}; //"slt $1 $4 $5", "bne $1 $0 -16"
    String[] blt5 = new String[]{"blt", "$0", "$a1", "1"};
    String[] blt5_hex_arr = new String[]{"0005082a", "14200001"}; //"slt $1 $4 $5", "bne $1 $0 -16"


    @Test
    public void setblt1(){
        Blt blt = new Blt(blt1);
        assertArrayEquals(blt1_hex_arr, blt.get_hex());
    }

    @Test
    public void setblt2(){
        Blt blt = new Blt(blt2);
        assertArrayEquals(blt2_hex_arr, blt.get_hex());
    }

    @Test
    public void setblt3(){
        Blt blt = new Blt(blt3);
        assertArrayEquals(blt3_hex_arr, blt.get_hex());
    }
    @Test
    public void setblt4(){
        Blt blt = new Blt(blt4);
        assertArrayEquals(blt4_hex_arr, blt.get_hex());
    }
    @Test
    public void setblt5(){
        Blt blt = new Blt(blt5);
        assertArrayEquals(blt5_hex_arr, blt.get_hex());
    }
}