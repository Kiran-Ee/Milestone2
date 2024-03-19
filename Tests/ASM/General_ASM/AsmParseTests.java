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
    String[] asm1_arr = new String[]{data_file1 , text_file1};

    @Test
    void parsed_files() {
        assertArrayEquals(asm1_arr, genASM.asm_parser(asm_file1));
    }
}


/* Tanja's old work
    String asm = ".data\n";
    String asm1 = ".data\n#comment\nlabel: .word 42\n\n";
    String hexDeclaration = ".data label: .word 0x1A";
    String dataDec = ".data label: .asciiz Request";
    String spaces = ".data  label   :   .word   10";

    // Expected Returns
    String[] empty = new String[]{};
    String[] label = new String[]{"label", "42"};
    String[] hexDec = new String[]{"label", "0x1A"};
    String[] data_dec = new String[]{"label", ".asciiz", "Request"};
    String[] spc = new String[]{"label", "10"};

    @Test
    void empty(){
        assertArrayEquals(empty, data_sec.asm_parser(asm));
    }
    @Test
    void label(){
        assertArrayEquals(label, data_sec.asm_parser(asm1));
    }
    @Test
    void hexD(){
        assertArrayEquals(hexDec, data_sec.asm_parser(hexDeclaration));
    }
    @Test
    void data_declaration(){
        assertArrayEquals(data_dec, data_sec.asm_parser(dataDec));
    }
    @Test
    void leading_spaces(){
        assertArrayEquals(spc, data_sec.asm_parser(spaces));
    }
 */