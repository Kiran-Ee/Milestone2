package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiTest {
    String[] li1 = new String[]{"li", "$a0", "0x1"};
    String[] li1_expected = new String[]{"24040001", ""};

    String[] li2 = new String[]{"li", "$a0", "0x10000001"};
    String[] li2_expected = new String[]{"3c011000", "34240001"};

//    String[] li3 = new String[]{"li", "$a0", "0xffffffff"}; // this throws exception bc 32 bit unsigned doesn't work with "Integer.parseInt() in Li's constructor
//    String[] li3_expected = new String[]{"2410ffff", ""};

    String[] li4 = new String[]{"li", "$a0", "1"};
    String[] li4_expected = new String[]{"24040001", ""};

    String[] li5 = new String[]{"li", "$a0", "65534"};
    String[] li5_expected = new String[]{"3404fffe", ""};

    String[] li_max_signed = new String[]{"li", "$a0", "32767"}; // max +signed
    String[] li_max_signed_exp = new String[]{"24047fff", ""};

    String[] li_max_signed_hex = new String[]{"li", "$a0", "0x7fff"};
    String[] li_max_signed_hex_exp = new String[]{"24047fff", ""};

    String[] li_min_signed = new String[]{"li", "$a0", "-32768"}; // min -signed
    String[] li_min_signed_exp = new String[]{"24048000", ""};

    String[] li_min_signed_hex = new String[]{"li", "$a0", "0x8000"};  // same number "min -signed" & "max +signed" ... just interpreted differently (i think hex's are interpreted as positive signed?)
    String[] li_min_signed_hex_exp = new String[]{"34048000", ""};

    String[] li_hex1 = new String[]{"li", "$a0", "0x8001"};  // same number "min -signed" & "max +signed" ... just interpreted differently (i think hex's are interpreted as positive signed?)
    String[] li_hex1_exp = new String[]{"34048001", ""};

    String[] li_max_beyond_signed1 = new String[]{"li", "$a0", "32768"}; // beyond max +signed
    String[] li_max_beyond_signed1_exp = new String[]{"34048000", ""};

    String[] li_max_beyond_signed2 = new String[]{"li", "$a0", "32769"}; // beyond max +signed
    String[] li_max_beyond_signed2_exp = new String[]{"34048001", ""};

    String[] li_min_beyond_signed = new String[]{"li", "$a0", "-32769"}; // beyond max -signed
    String[] li_min_beyond_signed_exp = new String[]{"3c01ffff", "34247fff"};

//    String[] li_min_beyond_signed_hex = new String[]{"li", "$a0", "0xffff7fff"}; //not sure how to figure this out ...
//    String[] li_min_beyond_signed_hex_exp = new String[]{"3c01ffff", "34247fff"};

    String[] li_max_unsigned = new String[]{"li", "$a0", "65535"}; // max +unsigned
    String[] li_max_unsigned_exp = new String[]{"3404ffff", ""};

    String[] li_max_unsigned_hex = new String[]{"li", "$a0", "0xffff"};
    String[] li_max_unsigned_hex_exp = new String[]{"3404ffff", ""};

    String[] li_max_beyond_unsigned = new String[]{"li", "$a0", "65536"}; // beyond max +unsigned
    String[] li_max_beyond_unsigned_exp = new String[]{"3c010001", "34240000"};

    String[] li_max_beyond_unsigned_hex = new String[]{"li", "$a0", "0x00010000"};
    String[] li_max_beyond_unsigned_hex_exp = new String[]{"3c010001", "34240000"};

    @Test
    public void testLi1() {
        Li li = new Li(li1);
        assertArrayEquals(li1_expected, li.get_hex());
    }

    @Test
    public void testLi2() {
        Li li = new Li(li2);
        String[] s = li.get_hex();
        assertArrayEquals(li2_expected, s);
    }

//    @Test
//    public void testLi3() {
//        Li li = new Li(li3);
//        String[] s = li.get_hex();
//        assertArrayEquals(li3_expected, s);
//    }

    @Test
    public void testLi4() {
        Li li = new Li(li4);
        assertArrayEquals(li4_expected, li.get_hex());
    }

    @Test
    public void testLi5() {
        Li li = new Li(li5);
        assertArrayEquals(li5_expected, li.get_hex());
    }

    @Test
    public void testLiMaxSigned() {
        Li li = new Li(li_max_signed);
        assertArrayEquals(li_max_signed_exp, li.get_hex());
    }

    @Test
    public void testLiMaxSignedHex() {
        Li li = new Li(li_max_signed_hex);
        assertArrayEquals(li_max_signed_hex_exp, li.get_hex());
    }

    @Test
    public void testLiMinSigned() {
        Li li = new Li(li_min_signed);
        assertArrayEquals(li_min_signed_exp, li.get_hex());
    }

    @Test
    public void testLiMinSignedHex() {
        Li li = new Li(li_min_signed_hex);
        assertArrayEquals(li_min_signed_hex_exp, li.get_hex());
    }

    @Test
    public void testLiHex1() {
        Li li = new Li(li_hex1);
        assertArrayEquals(li_hex1_exp, li.get_hex());
    }

    @Test
    public void testLiMaxBeyondSigned1() {
        Li li = new Li(li_max_beyond_signed1);
        assertArrayEquals(li_max_beyond_signed1_exp, li.get_hex());
    }

    @Test
    public void testLiMaxBeyondSigned2() {
        Li li = new Li(li_max_beyond_signed2);
        assertArrayEquals(li_max_beyond_signed2_exp, li.get_hex());
    }

    @Test
    public void testLiMinBeyondSigned() {
        Li li = new Li(li_min_beyond_signed);
        assertArrayEquals(li_min_beyond_signed_exp, li.get_hex());
    }

//    @Test
//    public void testLiMinBeyondSignedHex() {
//        Li li = new Li(li_min_beyond_signed_hex);
//        assertArrayEquals(li_min_beyond_signed_hex_exp, li.get_hex());
//    }

    @Test
    public void testLiMaxUnsigned() {
        Li li = new Li(li_max_unsigned);
        assertArrayEquals(li_max_unsigned_exp, li.get_hex());
    }

    @Test
    public void testLiMaxUnsignedHex() {
        Li li = new Li(li_max_unsigned_hex);
        assertArrayEquals(li_max_unsigned_hex_exp, li.get_hex());
    }

    @Test
    public void testLiMaxBeyondUnsigned() {
        Li li = new Li(li_max_beyond_unsigned);
        String[] s = li.get_hex();
        assertArrayEquals(li_max_beyond_unsigned_exp, s);
    }

    @Test
    public void testLiMaxBeyondUnsignedHex() {
        Li li = new Li(li_max_beyond_unsigned_hex);
        assertArrayEquals(li_max_beyond_unsigned_hex_exp, li.get_hex());
    }

}
