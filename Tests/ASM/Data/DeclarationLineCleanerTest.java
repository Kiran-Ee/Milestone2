package ASM.Data;

import ASM.DataSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeclarationLineCleanerTest {
    DataSection data_sec = new DataSection();
    String lbl1 = " input_request: .asciiz Enter your integer:#comment testing";
    String labelOnly = "    label:";
    String labelData1 = " label:    .word 0x32";
    String multipleWords = "    my_label_name: .word 0x32";
    String label_spaces_tab = " label:  .word     0x32";
    String[] dataLn1 = new String[]{"input_request", ".asciiz", "Enter your integer: "};
    String[] expected = new String[]{"label"};
    String[] labelData = new String[]{"label", ".word", "0x32"};
    String[] label_with_multiple_words = new String[]{"my_label_name", ".word", "0x32"};
    @Test
    void declaration_line_cleaner() {
        assertArrayEquals(dataLn1, data_sec.declaration_line_cleaner(lbl1));}
    @Test
    void label_only(){
        assertArrayEquals(expected, data_sec.declaration_line_cleaner(labelOnly));
    }
    @Test
    void label_data(){
        assertArrayEquals(labelData, data_sec.declaration_line_cleaner(labelData1));
    }
    @Test
    void multiple_word_label(){
        assertArrayEquals(label_with_multiple_words, data_sec.declaration_line_cleaner(multipleWords));
    }
    @Test
    void label_spaces(){
        assertArrayEquals(labelData, data_sec.declaration_line_cleaner(label_spaces_tab));
    }
}