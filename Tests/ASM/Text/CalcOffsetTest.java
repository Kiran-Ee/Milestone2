package ASM.Text;

import ASM.TextSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcOffsetTest {
    TextSection text_sec = new TextSection();
    int bad_cur_neg = -1;
    int bad_lbl_neg = -1;

    int cur0 = 0;
    int cur1 = 1;
    int cur2 = 100;
    int cur3 = 999999;
    int lbl1 = 1;
    int lbl2 = 50;
    int lbl3 = 1000000;
    // Testing Exceptions
    @Test
    public void setBad_cur_neg(){
        assertThrows(IllegalArgumentException.class, () -> {
            text_sec.calc_offset(bad_cur_neg, bad_lbl_neg);});
    }

    // Testing valid returns
    @Test
    public void set_0_return(){
        assertEquals(0, text_sec.calc_offset(cur0,lbl1));
    }
    @Test
    public void set_same_curLbl(){
       assertEquals(-1, text_sec.calc_offset(cur1,lbl1));
    }
    @Test
    public void set_cur_after_lbl(){
        assertEquals(-51, text_sec.calc_offset(cur2,lbl2));
    }
    @Test
    public void set_cur_before_lbl(){
        assertEquals(0, text_sec.calc_offset(cur3,lbl3));
    }

}