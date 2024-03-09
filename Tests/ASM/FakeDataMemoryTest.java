package ASM;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

// will probs add more tests later but this is a good start ...
// also, we might change the data structure entirely.
class FakeDataMemoryTest {
    DataSection data_sec = new DataSection();
    String[] dataLn1 = new String[]{"input_request", ".asciiz", "Enter your integer: "};
    String[] dataLn2 = new String[]{"even_output", ".asciiz", "Your integer is EVEN!"};
    String[] dataLn3 = new String[]{"odd_output", ".asciiz", "Your integer is ODD!"};

    String[] dataLn4 = new String[]{"kirans_label1Char", ".asciiz", "1234"};
    String[] dataLn5 = new String[]{"kirans_label2Char", ".asciiz", "56"};
    String[] dataLn6 = new String[]{"kirans_label3Char", ".asciiz", "7"};
    String[] dataLn7 = new String[]{"kirans_label4Char", ".asciiz", ""};
//    String[] dataLn5 = new String[]{"kiran's_label2Word",".word", "0x0"}; // shouldn't be needed for assignment ... keeping just in case we do need it
//    String[] dataLn6 = new String[]{"kiran's_label2Word",".word", "0x99999999"};
//    String[] dataLn7 = new String[]{"kiran's_label2Word",".word", "-1"};

    String[][] d3Sec1 = new String[][]{dataLn1, dataLn2, dataLn3};
    String[][] d4Sec2 = new String[][]{dataLn4, dataLn5, dataLn6, dataLn7};

    @Test
    void setDatad3Sec1() {
        HashMap<String, Object[]> hm = new HashMap<>();
        hm.put("input_request", new Object[]{0, "10010000"});
        hm.put("even_output", new Object[]{21, "10010015"});
        hm.put("odd_output", new Object[]{43, "1001002b"});
        assertEquals(hm, data_sec.fake_data_memory(d3Sec1));
    }

    @Test
    void setData4Sec2() {
        HashMap<String, Object[]> hm = new HashMap<>();
        hm.put("kirans_label1Char", new Object[]{0, "10010000"});
        hm.put("kirans_label2Char", new Object[]{5, "10010005"});
        hm.put("kirans_label3Char", new Object[]{8, "10010008"});
        hm.put("kirans_label4Char", new Object[]{11, "1001000b"});
        assertEquals(hm, data_sec.fake_data_memory(d4Sec2));
    }
}