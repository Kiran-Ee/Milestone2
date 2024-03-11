package ASM;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataSectionTest {
    DataSection data_sec = new DataSection();
    String[] data1 = new String[]{".asciiz", "Enter your integer: "};
    String[] data2 = new String[]{".asciiz", "Your integer is EVEN!"};
    String[] data3 = new String[]{".asciiz", "Yourinteger is ODD!"};
    String[] data4 = new String[]{".asciiz", "1234"};
    String[] data5 = new String[]{".asciiz", "56"};
    String[] data6 = new String[]{".asciiz", "7"};
    String[] data7 = new String[]{".asciiz", ""};
//    String[] dataW1 = new String[]{".word", "43"}; // shouldn't need to worry about this for this assignment
//    String[] dataW2 = new String[]{".word", "0"};


    @Test
    void setData1() {
        assertEquals(21 ,data_sec.calc_data_size(data1[0],data1[1]));
    }
    @Test
    void setData2() {
        assertEquals(22 ,data_sec.calc_data_size(data2[0],data2[1]));
    }
    @Test
    void setData3() {
        assertEquals(20 ,data_sec.calc_data_size(data3[0],data3[1]));
    }
    @Test
    void setData4() {
        assertEquals(5 ,data_sec.calc_data_size(data4[0],data4[1]));
    }
    @Test
    void setData5() {
        assertEquals(3 ,data_sec.calc_data_size(data5[0],data5[1]));
    }
    @Test
    void setData6() {
        assertEquals(2 ,data_sec.calc_data_size(data6[0],data6[1]));
    }
    @Test
    void setData7() {
        assertEquals(1 ,data_sec.calc_data_size(data7[0],data7[1]));
    }
}