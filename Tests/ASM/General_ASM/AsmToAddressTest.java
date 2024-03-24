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