package Operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class BltTest {
    LinkedHashMap<String, String[][]> text_hm = new LinkedHashMap<>();

    String[] text_ln1 = new String[]{"add", "$1", "$1","$1"};
    String[] text_ln2 = new String[]{"sub", "$1", "$1", "$1"};
    String[] blt_ITSODD = new String[]{"blt", "$a0", "$a1", "ITS_ODD:"};
    String[] text_ln3 = new String[]{"ITS_ODD:"};
    String[] text_ln4 = new String[]{"j", "ITS_ODD"};
    String[] text_ln5 = new String[]{"beq", "$a0", "$zero", "ITS_ODD"};
    String[] text_ln6 = new String[]{"add", "$t2", "$v1", "$a0"};
    String[][] clean_text_sec_size6 = new String[][]{text_ln1, text_ln2, text_ln3,text_ln4 ,text_ln5 ,text_ln6};

    @BeforeEach
    void setUp() {
        text_hm.put("0", new String[][]{text_ln1, new String[]{"00400000"}});
        text_hm.put("1", new String[][]{text_ln2, new String[]{"00400004"}});
        text_hm.put("2", new String[][]{blt_ITSODD, new String[]{"00400008"}});
        text_hm.put("ITS_ODD:", new String[][]{new String[]{"2"}, new String[]{"00400010"}});
        text_hm.put("4", new String[][]{text_ln4, new String[]{"00400010"}});
        text_hm.put("5", new String[][]{text_ln5, new String[]{"00400014"}});
        text_hm.put("6", new String[][]{text_ln6, new String[]{"00400018"}});
    }

    String[] blt_ITSODD_hex_arr = new String[]{"0085082a", "14200000"}; // from  MARS

    String[] blt1 = new String[]{"blt", "$a0", "$a1", "15"};
    String[] blt1_hex_arr = new String[]{"0085082a", "1420000f"};
    String[] blt2 = new String[]{"blt", "$4", "$a1", "0x0457"};
    String[] blt2_hex_arr = new String[]{"0085082a", "14200457"}; //"slt $1 $4 $5", "bne $1 $0 18"
    String[] blt3 = new String[]{"blt", "$a0", "$5", "12"};
    String[] blt3_hex_arr = new String[]{"0085082a", "1420000c"}; //"slt $1 $4 $5", "bne $1 $0 12"
    String[] blt4 = new String[]{"blt", "$4", "$5", "-16"};
    String[] blt4_hex_arr = new String[]{"0085082a", "1420fff0"}; //"slt $1 $4 $5", "bne $1 $0 -16"

    @Test
    public void label_ITS_ODD(){
        Blt blt = new Blt(blt_ITSODD);
        assertArrayEquals(blt_ITSODD_hex_arr, blt.get_hex());
    }
    
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
}