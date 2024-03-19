package ASM.Text;

import ASM.TextSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcOffsetAddressTest {
    TextSection text_sec = new TextSection();
    String bad_cur_empty = "";
    String bad_cur_neg = "-1";
    String bad_cur_double = "1.5";
    String bad_cur_hex = "0x1";
    String bad_lbl_empty = "";
    String bad_lbl_neg = "-1";
    String bad_lbl_double = "1.5";
    String bad_lbl_hex = "0x1";

    String cur1 = "1";
    String cur2 = "100";
    String cur3 = "999999";
    String lbl1 = "1";
    String lbl2 = "50";
    String lbl3 = "1000000";

    // Testing Exceptions
    @Test
    public void setBad_cur_empty(){
        assertThrows(IllegalArgumentException.class, () -> {
            text_sec.calc_offset_address(bad_cur_empty, bad_lbl_empty);});
    }
    @Test
    public void setBad_cur_neg(){
        assertThrows(IllegalArgumentException.class, () -> {
            text_sec.calc_offset_address(bad_cur_neg, bad_lbl_neg);});
    }
    @Test
    public void setBad_cur_double(){
        assertThrows(IllegalArgumentException.class, () -> {
            text_sec.calc_offset_address(bad_cur_double, bad_lbl_double);});
    }
    @Test
    public void setBad_cur_hex(){
        assertThrows(IllegalArgumentException.class, () -> {
            text_sec.calc_offset_address(bad_cur_hex, bad_lbl_hex);});
    }
    // Testing valid returns
    @Test
    public void setCur1(){
       assertEquals(0, text_sec.calc_offset_address(cur1,lbl1)); // same
    }
    @Test
    public void setCur2(){
        assertEquals(0, text_sec.calc_offset_address(cur2,lbl2)); // current after lbl
    }
    @Test
    public void setCur3(){
        assertEquals(0, text_sec.calc_offset_address(cur3,lbl3)); // current before lbl
    }

}