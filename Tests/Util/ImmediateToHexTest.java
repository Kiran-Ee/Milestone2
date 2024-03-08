package Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImmediateToHexTest {

    String dec_0 = "0";
    String dec_10 = "10";
    String dec_999 = "999";

    String dec_neg10 = "-10";
    String dec_neg999 = "-999";
    String dec_min = "-32768";

    String dec_max = "32767"; // assuming 16 bit max
    String dec_1plus_max_signed = "32768";
    String dec_max_unsigned = "65535";

    String hex_0 = "0x0"; // used "https://www.rapidtables.com/convert/number/decimal-to-binary.html?x=-10"
    String hex_10 = "0xA";
    String hex_999 = "0x3E7";

    String hex_max = "0x7FFF";
    String hex_max_unsigned = "0xFFFF";

    String prof1 = "";  // didn't handle "" case previously

    // Return type
    @Test
    void returns_string() {
        assertEquals(String.class, General.immediate_to_hex("10", true).getClass());
    }

    // Decimal Immediate: Signed
    @Test
    void decimal_sign_0() {
        assertEquals("0000", General.immediate_to_hex(dec_0, true));
    }

    @Test
    void decimal_sign_10() {
        assertEquals("000a", General.immediate_to_hex(dec_10, true));
    }

    @Test
    void decimal_sign_999() {
        assertEquals("03e7", General.immediate_to_hex(dec_999, true));
    }

    @Test
    void decimal_sign_Max() {
        assertEquals("7fff", General.immediate_to_hex(dec_max, true));
    }

    // Decimal Immediate: Unsigned
    @Test
    void decimal_NoSign_0() {
        assertEquals("0000", General.immediate_to_hex(dec_0, false));
    }

    @Test
    void decimal_NoSign_10() {
        assertEquals("000a", General.immediate_to_hex(dec_10, false));
    }

    @Test
    void decimal_NoSign_999() {
        assertEquals("03e7", General.immediate_to_hex(dec_999, false));
    }

    @Test
    void decimal_NoSign_SignMax() {
        assertEquals("7fff", General.immediate_to_hex(dec_max, false));
    }

    @Test
    void decimal_NoSign_UnsignMax() {
        assertEquals("ffff", General.immediate_to_hex(dec_max_unsigned, false));
    }

    @Test
    void decimal_NoSign_1PlusMaxSign() {
        assertEquals("8000", General.immediate_to_hex(dec_1plus_max_signed, false));
    }


    // Decimal Negative Immediate: signed
    @Test
    void decimal_Negative_10() {
        assertEquals("fff6", General.immediate_to_hex(dec_neg10, true));
    }

    @Test
    void decimal_Negative_999() {
        assertEquals("fc19", General.immediate_to_hex(dec_neg999, true));
    }

    @Test
    void decimal_Negative_Min() {
        assertEquals("8000", General.immediate_to_hex(dec_min, true));
    }


    // Hex, Sign
    @Test
    void hex_0() {
        assertEquals("0000", General.immediate_to_hex(hex_0, true));
    }

    @Test
    void hex_10() {
        assertEquals("000a", General.immediate_to_hex(hex_10, true));
    }

    @Test
    void hex_999() {
        assertEquals("03e7", General.immediate_to_hex(hex_999, true));
    }

    @Test
    void hex_Max() {
        assertEquals("7fff", General.immediate_to_hex(hex_max, true));
    }

    @Test
    void hex_Max_Unsigned() {
        assertEquals("ffff", General.immediate_to_hex(hex_max_unsigned, false));
    }

    @Test
    void prof1() {
        assertEquals("0000", General.immediate_to_hex(prof1, true));
    }

}