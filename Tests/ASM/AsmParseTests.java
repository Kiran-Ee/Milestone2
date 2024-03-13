package ASM;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AsmParseTests {
    DataSection data_sec = new DataSection();

    String asm = ".data\n";
    String asm1 = ".data\n#comment\nlabel: .word 42\n\n";
    String hexDeclaration = ".data label: .word 0x1A";
    String dataDec = ".data label: .asciiz Request";
    String spaces = ".data  label   :   .word   10";
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
}
