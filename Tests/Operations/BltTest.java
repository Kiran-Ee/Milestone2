package Operations;

import static org.junit.jupiter.api.Assertions.*;

class BltTest {
    String[] blt1 = new String[]{"blt", "$a0", "$a1", "00400000"};
    String[] blt1_hex_arr = new String[]{"slt $1 $4 $5", "bne $1 $0 00400000"};
    String[] blt2 = new String[]{"blt", "$4", "$a1", "00400040"};
    String[] blt2_hex_arr = new String[]{"slt $1 $4 $5", "bne $1 $0 00400040"};
    String[] blt3 = new String[]{"blt", "$a0", "$5", "0040004c"};
    String[] blt3_hex_arr = new String[]{"slt $1 $4 $5", "bne $1 $0 0040004c"};
    String[] blt4 = new String[]{"blt", "$4", "$5", "00400028"};
    String[] blt4_hex_arr = new String[]{"slt $1 $4 $5", "bne $1 $0 00400028"};


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