package ASM.General_ASM;

import ASM.TextSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// add more if needed ...
class CalcOffsetAddressTest {
    TextSection text_sec = new TextSection();
    String i1 = "0";
    String i2 = "1";
    String i3 = "2";
    String i4 = "3";
    String i5 = "4";
    String i6 = "5";
    String i7 = "6";
    String i8 = "7";
    String i9 = "8";
    String i10 = "9";
    String i11 = "10";
    String i12 = "11";
    String i13 = "12";
    String i14 = "13";
    String i15 = "14";
    String i16 = "15";
    String i17 = "16";
    String i18 = "17";
    String i19 = "18";
    String i20 = "19";

    // same
    @Test
    void i5_i5() {
        assertEquals(-1, text_sec.calc_offset(i5, i5));
    }

    // random combos
    @Test
    void i1_i2() {
        assertEquals(0,text_sec.calc_offset(i1, i2));
    }

    @Test
    void i2_i1() {
        assertEquals(-2,text_sec.calc_offset(i2, i1));
    }

    @Test
    void i1_i20() {
        assertEquals(18,text_sec.calc_offset(i1, i20));
    }

    @Test
    void i20_ii() {
        assertEquals(-20,text_sec.calc_offset(i20, i1));
    }
}