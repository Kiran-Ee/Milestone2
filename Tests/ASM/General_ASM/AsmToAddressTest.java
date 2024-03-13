package ASM.General_ASM;

import ASM.DataSection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class AsmToAddressTest {
    DataSection data_sec = new DataSection();

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
}
