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

    String data_file2 =
            ".data\n\n"  +
            "        input_request:        .asciiz        \"Enter your integer: \"\n" +
            "        even_output:        .asciiz        \"Your integer is EVEN!\"\n" +
            "        odd_output:        .asciiz        \"Your integer is ODD!\"\n" +
            "\n";

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
    String asm_file2 = data_file2 + text_file1;

    String corrected_data_file1 =
                    "Label1: .asciiz \"request\" #commentshouldn't be included       \n" +
                    "      spaces: .asciiz    \" sp a ces\" \n\n\n" +
                    " LotsOfLineBreaks: \"Line\"";
    String corrected_data_file2 =
            "\n"  +
            "        input_request:        .asciiz        \"Enter your integer: \"\n" +
            "        even_output:        .asciiz        \"Your integer is EVEN!\"\n" +
            "        odd_output:        .asciiz        \"Your integer is ODD!\"\n" +
            "\n";

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

    String[] asm2_arr = new String[]{corrected_data_file2 , corrected_text_file1};


    @Test
    void parsed_files1() {
        assertArrayEquals(asm1_arr, genASM.asm_parser(asm_file1));
    }

    @Test
    void parsed_files2() {
        assertArrayEquals(asm2_arr, genASM.asm_parser(asm_file2));
    }

}