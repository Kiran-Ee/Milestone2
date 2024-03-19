package ASM.General_ASM;

import ASM.TextSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// add more if needed ...
class CalcOffsetAddressTest {
    TextSection text_sec = new TextSection();
    String i1 = "000400004";
    String i2 = "000400008";
    String i3 = "00040000c";
    String i4 = "000400010";
    String i5 = "000400014";
    String i6 = "000400018";
    String i7 = "00040001c";
    String i8 = "000400020";
    String i9 = "000400024";
    String i10 = "000400028";
    String i11 = "00040002c";
    String i12 = "000400030";
    String i13 = "000400034";
    String i14 = "000400038";
    String i15 = "00040003c";
    String i16 = "000400040";
    String i17 = "000400044";
    String i18 = "000400048";
    String i19 = "00040004c";
    String i20 = "000400050";

    @Test
    void bad_address() {
        assertThrows(IllegalArgumentException.class,
                () -> text_sec.calc_offset_address(i1, "000400005"));
    }

    // same
    @Test
    void i5_i5() {
        assertEquals(0, text_sec.calc_offset_address(i5, i5));
    }

    // random combos
    @Test
    void i1_i2() {
        assertEquals(1,text_sec.calc_offset_address(i1, i2));
    }

    @Test
    void i2_i1() {
        assertEquals(-1,text_sec.calc_offset_address(i2, i1));
    }

    @Test
    void i1_i20() {
        assertEquals(19,text_sec.calc_offset_address(i1, i20));
    }

    @Test
    void i20_ii() {
        assertEquals(-19,text_sec.calc_offset_address(i20, i1));
    }
}