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
                    " LotsOfLineBreaks: .asciiz \"Line\"" +
             ".text \n" +
                    "add $s1, $s2, $s2\n" +
                    "addiu $s1, $s2, 10 #blablabla\n" +
                    "       addiu   $s1,$2, 0x10\n" +
                    "        \n" +
                    "   \n" +
                    "\n\n\n\n" +
                    "#comment line \n" +
                    "j RandomLabel#withacomment\n" +
                    "\n\n" +
                    "li $s1, 42\n" +
                    "la $s1, Label1\n" +
                    "blt $s1, $s2, RandomLabel\n" +
                    "bne $s1, $s2, RandomLabel\n" +
                    "beq $s1, $s2, RandomLabel\n" +
                    "\n" +
                    "RandomLabel:\n" +
                    "add $s1, $s2, $3";
    String[] exp_data_addr_arr = new String[]{
            "75716572",
            "00747365",
            "20707320",
            "65632061",
            "694c0073",
            "0000656e"
    };
    String[] exp_text_addr_arr = new String[]{
            "02528820",
            "2651000a",
            "24510010",
            "0810000b",
            "2411002a",
            "3c011001",
            "34310000",
            "0232082a",
            "14200002",
            "16320001",
            "12320000",
            "02438820"
    };
    String[][] exp_asm_arr = new String[][]{exp_data_addr_arr, exp_text_addr_arr};

    @Test
    void asm_master_return() {
        assertArrayEquals(exp_asm_arr, genASM.asm_to_address(asm_file1));
        // having troubles with ":" with labels ...
    }
}