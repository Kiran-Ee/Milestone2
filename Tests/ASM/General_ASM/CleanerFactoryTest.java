package ASM.General_ASM;

import ASM.GeneralASM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

public class CleanerFactoryTest {
    GeneralASM sec = new GeneralASM();
    String data_file1 =
            ".data\n" +
                    "Label1: .asciiz \"request\" #commentshouldn't be included       \n" +
                    "      spaces: .asciiz    \" sp a ces\" \n\n\n" +
                    " LotsOfLineBreaks: \"Line\"";
    String[] instr1 = new String[]{"Label1", "request"};
    String[] instr2 = new String[]{"spaces", " sp a ces"};
    String[] instr3 = new String[]{"LotsOfLineBreaks", "Line"};
    String[][] cleaned_data_file1 = new String[][]{instr1, instr2, instr3};

    @Test
    void setInstr1(){
        assertArrayEquals(cleaned_data_file1, sec.cleaner_factory(true, data_file1));
    }
/*
String sEndian = ".data var: .word 0x123456789";
String dataComm = ".data #comment var : .word 42";
String validData = ".data var : .word 42 var1: .asciiz  Request";
String branchJ = ".text loop: beq $t0, $t1, endloop j loop j endloop endloop: ";
String labelAddress = ".text main: add $t0, $t1, $t2, j loop, loop: sub $t3, $t4, $t5";
String[][] smallEndian = new String[][]{{"var", "0x78563412"}};
String[][] dataComm1 = new String[][]{{"var", "42"}};
String[][] validData1 = new String[][]{{"var", "42"}, {"var1", "Request"}};
String[][] branch_j = new String[][]{{"loop", "beq $t0, $t1, endloop"}, {"", "j 0x0040000C"}, {"endloop", ""}};
String[][] labelAddress1 = new String[][]{{"main", "add $t0, $t1, $t2"}, {"", "j 0x00400008"}, {"loop", "sub $t3, $t4, $t5"}};

@Test
void smlEndian(){
    Assertions.assertArrayEquals(smallEndian, sec.cleaner_factory(true, sEndian));
}
@Test
void data_comments(){
    Assertions.assertArrayEquals(dataComm1, sec.cleaner_factory(true, dataComm));
}
@Test
void valid_data(){
    Assertions.assertArrayEquals(validData1, sec.cleaner_factory(true, validData));
}
@Test
void branch_and_jump(){
    Assertions.assertArrayEquals(branch_j, sec.cleaner_factory(false, branchJ));
}
@Test
void label_address(){
    Assertions.assertArrayEquals(labelAddress1, sec.cleaner_factory(false, labelAddress));
}
*/}