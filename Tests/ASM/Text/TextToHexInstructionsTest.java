package ASM.Text;

import ASM.TextSection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class TextToHexInstructionsTest { // I COPIED ALL THE LINES FROM THE GIVEN CODE IN CANVAS
    TextSection text_obj = new TextSection();
    private LinkedHashMap<String, String[]> data_sec_1; // {“label” : [“data”, “address in memory”] }
    private LinkedHashMap<String, String[][]> text_sec_1; // {“index or label” : [[“instruction”], [“address in memory”]] }

    String[][] txt_ln1 = new String[][]{new String[]{"li", "$v0", "4"}, new String[]{"00400000"}};
    String[][] txt_ln2 = new String[][]{new String[]{"la", "$a0", "input_request"}, new String[]{"00400004"}};
    String[][] txt_ln3 = new String[][]{new String[]{"syscall"}, new String[]{"0040000c"}};
    String[][] txt_ln4 = new String[][]{new String[]{"li", "$v0", "5"}, new String[]{"00400010"}};
    String[][] txt_ln5 = new String[][]{new String[]{"syscall"}, new String[]{"00400014"}};
    String[][] txt_ln6 = new String[][]{new String[]{"move", "$t4", "$v0"}, new String[]{"00400018"}};
    String[][] txt_ln7 = new String[][]{new String[]{"andi", "$t0", "$t4", "0x1"}, new String[]{"0040001c"}};
    String[][] txt_ln8 = new String[][]{new String[]{"beq", "$t0", "$zero", "ITS_EVEN"}, new String[]{"0040002c"}};
    String[][] txt_ln9 = new String[][]{new String[]{"j", "ITS_ODD"}, new String[]{"00400024"}};
    String[][] txt_ln10 = new String[][]{new String[]{"9"}, new String[]{"00400028"}}; // "ITS_EVEN" -> "li $v0 4"
    String[][] txt_ln11 = new String[][]{new String[]{"li", "$v0", "4"}, new String[]{"00400028"}};
    String[][] txt_ln12 = new String[][]{new String[]{"la", "$a0", "even_output"}, new String[]{"0040002c"}};
    String[][] txt_ln13 = new String[][]{new String[]{"syscall"}, new String[]{"00400034"}};
    String[][] txt_ln14 = new String[][]{new String[]{"j", "EXIT"}, new String[]{"00400038"}};
    String[][] txt_ln15 = new String[][]{new String[]{"14"}, new String[]{"0040003c"}}; // "ITS_ODD" -> "li $v0 4"
    String[][] txt_ln16 = new String[][]{new String[]{"li", "$v0", "4"}, new String[]{"0040003c"}};
    String[][] txt_ln17 = new String[][]{new String[]{"la", "$a0", "odd_output"}, new String[]{"00400040"}};
    String[][] txt_ln18 = new String[][]{new String[]{"syscall"}, new String[]{"00400048"}};
    String[][] txt_ln19 = new String[][]{new String[]{"EXIT"}, new String[]{"0040004c"}}; // "EXIT" -> li $v0 10"
    String[][] txt_ln20 = new String[][]{new String[]{"li", "$v0", "10"}, new String[]{"0040004c"}};
    String[][] txt_ln21 = new String[][]{new String[]{"syscall"}, new String[]{"00400050"}};
    String data_exp_ret1 = "65746e45" +
            "6f792072" +
            "69207275" +
            "6765746e" +
            "203a7265" +
            "756f5900" +
            "6e692072" +
            "65676574" +
            "73692072" +
            "45564520" +
            "5900214e" +
            "2072756f" +
            "65746e69" +
            "20726567" +
            "4f207369" +
            "00214444";

    String text_exp_ret1 = "24020004" +
            "3c011001" +
            "34240000" +
            "0000000c" +
            "24020005" +
            "0000000c" +
            "00026021" +
            "31880001" +
            "11000001" +
            "0810000f" +
            "24020004" +
            "3c011001" +
            "34240015" +
            "0000000c" +
            "08100013" +
            "24020004" +
            "3c011001" +
            "3424002b" +
            "0000000c" +
            "2402000a" +
            "0000000c";
    String[] exp_ret1_strArr = new String[]{data_exp_ret1, text_exp_ret1};

    @BeforeEach
    void setUp() { // allows each test method to use this data ... wasn't working w/o this
        data_sec_1 = new LinkedHashMap<>();
        data_sec_1.put("input_request", new String[]{"Enter your integer: ", "10010000"});
        data_sec_1.put("even_output", new String[]{"Your integer is EVEN!", "10010015"});
        data_sec_1.put("odd_output", new String[]{"Your integer is ODD!", "1001002b"});

        text_sec_1 = new LinkedHashMap<>();
        text_sec_1.put("0", txt_ln1);
        text_sec_1.put("1", txt_ln2);
        text_sec_1.put("2", txt_ln3);
        text_sec_1.put("3", txt_ln4);
        text_sec_1.put("4", txt_ln5);
        text_sec_1.put("5", txt_ln6);
        text_sec_1.put("6", txt_ln7);
        text_sec_1.put("7", txt_ln8);
        text_sec_1.put("8", txt_ln9);
        text_sec_1.put("ITS_EVEN:", txt_ln10);
        text_sec_1.put("10", txt_ln11);
        text_sec_1.put("11", txt_ln12);
        text_sec_1.put("12", txt_ln13);
        text_sec_1.put("13", txt_ln14);
        text_sec_1.put("ITS_ODD:", txt_ln15);
        text_sec_1.put("15", txt_ln16);
        text_sec_1.put("16", txt_ln17);
        text_sec_1.put("17", txt_ln18);
        text_sec_1.put("EXIT:", txt_ln19);
        text_sec_1.put("19", txt_ln20);
        text_sec_1.put("20", txt_ln21);
    }

    @Test
    void setText_sec_1() {
        assertArrayEquals(exp_ret1_strArr, text_obj.text_to_hex_instructions(data_sec_1, text_sec_1));
    }
}