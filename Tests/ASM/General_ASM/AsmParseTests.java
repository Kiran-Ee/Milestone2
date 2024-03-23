package ASM.General_ASM;

import ASM.GeneralASM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AsmParseTests {
    GeneralASM genASM = new GeneralASM();

    // Broken up for "clarity" & testing
    String data_file1 =
            ".data\n" +
            "Label1: .asciiz \"request\" #commentshouldn't be included       \n" +
            "      spaces: .asciiz    \" sp a ces\" \n\n\n" +
            " LotsOfLineBreaks: \"Line\"";
    String text_file1 =
            ".text \n" +
            "add $s1, $s2, $s\n" +
            "addiu $s1, $s2, 10 #blablabla\n" +
            "       addiu   $s1,$2, 0x10\n" +
            "addiu  $s1,$s2,                    -10\n" +
            "        \n" +
            "   \n"+
            "\n\n\n\n" +
            "#comment line"+
            "j RandomLabel#withacommen\n" +
            "add $s1, $s2, $3";
    String asm_file1 = data_file1 + text_file1;

    String corrected_data_file1 =
                    "Label1: .asciiz \"request\" #commentshouldn't be included       \n" +
                    "      spaces: .asciiz    \" sp a ces\" \n\n\n" +
                    " LotsOfLineBreaks: \"Line\"";
    String corrected_text_file1 =
                    " \nadd $s1, $s2, $s\n" +
                    "addiu $s1, $s2, 10 #blablabla\n" +
                    "       addiu   $s1,$2, 0x10\n" +
                    "addiu  $s1,$s2,                    -10\n" +
                    "        \n" +
                    "   \n"+
                    "\n\n\n\n" +
                    "#comment line"+
                    "j RandomLabel#withacommen\n" +
                    "add $s1, $s2, $3";
    String[] asm1_arr = new String[]{corrected_data_file1 , corrected_text_file1};

    @Test
    void parsed_files() {
        assertArrayEquals(asm1_arr, genASM.asm_parser(asm_file1));
    }
}