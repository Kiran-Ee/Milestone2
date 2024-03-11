package ASM;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcNextAddressTest {
    DataSection data_sec = new DataSection();
    String[] dataLn1 = new String[]{"10010000", "21", "Enter your integer: "}; //format: start addr, numChar, data.
    String[] dataLn2 = new String[]{"10010015", "22", "Your integer is EVEN!"};
    String[] dataLn3 = new String[]{"1001002b", "21", "Your integer is ODD!"};

    String[] dataLn4 = new String[]{"10010000", "5", "1234"};
    String[] dataLn5 = new String[]{"10010005", "3", "56"};
    String[] dataLn6 = new String[]{"10010008", "2", "7"};
    String[] dataLn7 = new String[]{"1001000a", "1", ""};


    @Test
    void calcNextAddress1() {
        assertEquals("10010015" ,data_sec.calc_next_address(dataLn1[0], Integer.parseInt(dataLn1[1])));
    }
    @Test
    void calcNextAddress2() {
        assertEquals("1001002b" ,data_sec.calc_next_address(dataLn2[0], Integer.parseInt(dataLn2[1])));
    }
    @Test
    void calcNextAddress3() {
        assertEquals("10010040" ,data_sec.calc_next_address(dataLn3[0], Integer.parseInt(dataLn3[1])));
    }

    @Test
    void calcNextAddress4() {
        assertEquals("10010005" ,data_sec.calc_next_address(dataLn4[0], Integer.parseInt(dataLn4[1])));
    }
    @Test
    void calcNextAddress5() {
        assertEquals("10010008" ,data_sec.calc_next_address(dataLn5[0], Integer.parseInt(dataLn5[1])));
    }
    @Test
    void calcNextAddress6() {
        assertEquals("1001000a" ,data_sec.calc_next_address(dataLn6[0], Integer.parseInt(dataLn6[1])));
    }
    @Test
    void calcNextAddress7() {
        assertEquals("1001000b" ,data_sec.calc_next_address(dataLn7[0], Integer.parseInt(dataLn7[1])));
    }

}