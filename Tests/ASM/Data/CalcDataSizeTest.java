package ASM.Data;

import ASM.DataSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcDataSizeTest {
    DataSection data_sec = new DataSection();
    String[] text1 = new String[]{".asciiz", "Enter your integer: "};
    String[] text2 = new String[]{".asciiz", "Your integer is EVEN!"};
    String[] text3 = new String[]{".asciiz", "Yourinteger is ODD!"};
    String[] text4 = new String[]{".asciiz", "1234"};
    String[] text5 = new String[]{".asciiz", "56"};
    String[] text6 = new String[]{".asciiz", "7"};
    String[] text7 = new String[]{".asciiz", ""};
    String[] dataW1 = new String[]{".word", "43"}; // shouldn't need to worry about this for this assignment
    String[] dataW2 = new String[]{".word", "0"};


    @Test
    void settext1() {
        assertEquals(21 ,data_sec.calc_data_size(text1[0],text1[1]));
    }
    @Test
    void settext2() {
        assertEquals(22 ,data_sec.calc_data_size(text2[0],text2[1]));
    }
    @Test
    void settext3() {
        assertEquals(20 ,data_sec.calc_data_size(text3[0],text3[1]));
    }
    @Test
    void settext4() {
        assertEquals(5 ,data_sec.calc_data_size(text4[0],text4[1]));
    }
    @Test
    void settext5() {
        assertEquals(3 ,data_sec.calc_data_size(text5[0],text5[1]));
    }
    @Test
    void settext6() {
        assertEquals(2 ,data_sec.calc_data_size(text6[0],text6[1]));
    }
    @Test
    void setDataW1() {
        assertEquals(4 ,data_sec.calc_data_size(dataW1[0],dataW1[1]));
    }

    @Test
    void setDataW2() {
        assertEquals(4 ,data_sec.calc_data_size(dataW2[0],dataW2[1]));
    }
}