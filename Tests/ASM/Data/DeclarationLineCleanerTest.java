package ASM.Data;

import ASM.DataSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationLineCleanerTest {
    DataSection data_sec = new DataSection();
    String lbl1 = " input_request: .asciiz \"Enter your integer:\"#comment testing";
    String lbl2 = " input_request:     .asciiz      \"Enter your integer:\"#comment testing  #comment";
    String lbl3 = "input_request:       .asciiz            \"Enter your integer:\"#comment testing#comment";
    String lbl4 = " fasdfa:       .asciiz            \"fasdfas\"#comment testing#comment";

    String[] dataLn1 = new String[]{"input_request", "Enter your integer:"};
    String[] dataLn2 = new String[]{"fasdfa", "fasdfas"};

    @Test
    void setLbl1() {
        assertArrayEquals(dataLn1, data_sec.declaration_line_cleaner(lbl1));}
    @Test
    void setLbl2(){
        assertArrayEquals(dataLn1, data_sec.declaration_line_cleaner(lbl2));
    }
    @Test
    void setLbl3(){
        assertArrayEquals(dataLn1, data_sec.declaration_line_cleaner(lbl3));
    }
    @Test
    void setLbl4(){
        assertArrayEquals(dataLn2, data_sec.declaration_line_cleaner(lbl4));
    }
}