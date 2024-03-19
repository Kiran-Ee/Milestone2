package ASM.General_ASM;

import ASM.GeneralASM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AsmToAddressTest {
    GeneralASM genASM = new GeneralASM();
    String asm_file1 =
            ".data\n" +
                    "Label1: .asciiz \"request\" #commentshouldn't be included       \n" +
                    "      spaces: .asciiz    \" sp a ces\" \n\n\n" +
                    " LotsOfLineBreaks: \"Line\"" +
             ".text \n" +
                    "add $s1, $s2, $s2\n" +
                    "addiu $s1, $s2, 10 #blablabla\n" +
                    "       addiu   $s1,$2, 0x10\n" +
                    "addiu  $s1,$s2,                    -10\n" +
                    "        \n" +
                    "   \n" +
                    "\n\n\n\n" +
                    "#comment line" +
                    "j RandomLabel#withacommen\n" +
                    "add $s1, $s2, $3";
    String[] exp_data_addr_arr = new String[]{"75716572",
            "00747365",
            "20707320",
            "65632061",
            "694c0073",
            "0000656e"}; 
    String[] exp_text_addr_arr = new String[]{"02528820" ,
            "2651000a" ,
            "24510010" ,
            "2651fff6" ,
            "08100006" ,
            "02438820" ,
            "2402000a" ,
            "0000000c"}; // ... ENTER IN PROGRAM USE MARS DUMP FEATURE
    String[][] exp_asm_arr = new String[][]{exp_data_addr_arr, exp_text_addr_arr};

    @Test
    void emptyString() {
        assertArrayEquals(exp_asm_arr, genASM.asm_to_address(asm_file1));
    }
}

/*

    String empty = "";
    String comm = "#comment";
    String asm = ".data var: .word 42, var1: .asciiz Request /n.text main: add $t0, $t1, $t2";
    String text = ".text main: add $t0, $t1, $t2";
    String[] emptyStr = new String[]{"", ""};
    String[] comment1 = {"", ""};
    String[] asm1 = new String[]{"0x10010000 2A00 10010004 68656C6C6F", "00400000 21080008 014B4820"};//need add. check
    String[] text1 = new String[]{"", "00400000 21080008 014B4820"}; //add check,

    @Test
    void emptyString(){
        Assertions.assertEquals(emptyStr, data_sec.asm_to_address(empty));
    }
    @Test
    void comment(){
        Assertions.assertEquals(comment1, data_sec.asm_to_address(comm));
    }
    @Test
    void asm(){
        Assertions.assertEquals(asm1, data_sec.asm_to_address(asm));
    }
    @Test
    void text_section(){
        Assertions.assertEquals(text1, data_sec.asm_to_address(text));
    }
 */

