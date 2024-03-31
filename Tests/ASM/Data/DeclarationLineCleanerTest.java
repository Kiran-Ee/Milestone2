package ASM.Data;

import ASM.DataSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationLineCleanerTest {
    DataSection data_sec = new DataSection();
    String lbl1 = " input_request: .asciiz \"Enter your integer:\"#comment testing";
    String lbl2 = " input_request:     .asciiz      \"Enter your integer:\"#comment testing  #comment";
    String lbl3 = " input_request:       .asciiz            \"Enter your integer:\"#comment testing#comment";
    String lbl4 = " fasdfa:       .asciiz            \"fasdfas\"#comment testing#comment";

    String[] dataLn1 = new String[]{"input_request", "Enter your integer:"};
    String[] dataLn2 = new String[]{"fasdfa", "fasdfas"};

    String ln1 = "\n";
    String ln2 = "        input_request:        .asciiz        \"Enter your integer: \"\n";
    String ln3 = "        even_output:        .asciiz        \"Your integer is EVEN!\"\n";
    String ln4 = "        odd_output:        .asciiz        \"Your integer is ODD!\"\n";
    String ln5 = "\n";

    String[] data2Ln1 = new String[]{"input_request", "Enter your integer: "};
    String[] data2Ln2 = new String[]{"even_output", "Your integer is EVEN!"};
    String[] data2Ln3 = new String[]{"odd_output", "Your integer is ODD!"};
    String[] data2Ln4 = new String[]{};

    @Test
    void setLbl1() {
        assertArrayEquals(dataLn1, data_sec.declaration_line_cleaner(lbl1));
    }

    @Test
    void setLbl2() {
        assertArrayEquals(dataLn1, data_sec.declaration_line_cleaner(lbl2));
    }

    @Test
    void setLbl3() {
        assertArrayEquals(dataLn1, data_sec.declaration_line_cleaner(lbl3));
    }

    @Test
    void setLbl4() {
        assertArrayEquals(dataLn2, data_sec.declaration_line_cleaner(lbl4));
    }


    @Test
    void setLn2() {
        assertArrayEquals(data2Ln1, data_sec.declaration_line_cleaner(ln2));
    }

    @Test
    void setLn3() {
        assertArrayEquals(data2Ln2, data_sec.declaration_line_cleaner(ln3));
    }

    @Test
    void setLn4() {
        assertArrayEquals(data2Ln3, data_sec.declaration_line_cleaner(ln4));
    }

    @Test
    void setLn1() {
        assertArrayEquals(data2Ln4, data_sec.declaration_line_cleaner(ln1));
    }

    @Test
    void setLn5() {
        assertArrayEquals(data2Ln4, data_sec.declaration_line_cleaner(ln5));
    }

}