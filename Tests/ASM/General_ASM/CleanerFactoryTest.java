package ASM.General_ASM;

import ASM.GeneralASM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

public class CleanerFactoryTest {
    GeneralASM sec = new GeneralASM();

    // ADD TEST_FILE1
    String data_file1 =
            "Label1: .asciiz \"request\" #commentshouldn't be included       \n" +
                    "      spaces: .asciiz    \" sp a ces\" \n\n\n" +
                    " LotsOfLineBreaks: .asciiz  \"Line\"#comment";
    String[] instr1 = new String[]{"Label1", "request"};
    String[] instr2 = new String[]{"spaces", " sp a ces"};
    String[] instr3 = new String[]{"LotsOfLineBreaks", "Line"};
    String[][] cleaned_data_file1 = new String[][]{instr1, instr2, instr3};

    String text_file1 =
            "add $s1, $s3, $s\n" +
                    "addiu $s1, $s2, 10 #test\n" +
                    "       addiu   $s1,$2, 0x10\n" +
                    "addiu  $s1,$s2,                    -10\n" +
                    "        \n" +
                    "   \n" +
                    "\n\n\n\n" +
                    "#comment line" +
                    "j RandomLabel#withacomment\n" +
                    "add $s5, $s2, $3";
    String[] instr4 = new String[]{"add", "$s1", "$s3", "$s"};
    String[] instr5 = new String[]{"addiu", "$s1", "$s2", "10"};
    String[] instr6 = new String[]{"addiu", "$s1", "$2", "0x10"};
    String[] instr7 = new String[]{"addiu", "$s1", "$s2", "-10"};
    String[] instr8 = new String[]{"add", "$s5", "$s2", "$3"};

    String[][] cleaned_data_file2 = new String[][]{instr4, instr5, instr6, instr7, instr8};

    @Test
    void setCleaned_data_file1() {
        assertArrayEquals(cleaned_data_file1, sec.cleaner_factory(true, data_file1));
    }

    @Test
    void setCleaned_text_file1() {
        assertArrayEquals(cleaned_data_file2, sec.cleaner_factory(false, text_file1));
    }
}