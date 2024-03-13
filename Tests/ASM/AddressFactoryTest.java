package ASM;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

public class AddressFactoryTest {
    DataSection data_sec = new DataSection();
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
        Assertions.assertArrayEquals(smallEndian, data_sec.address_factory(true, sEndian));
    }
    @Test
    void data_comments(){
        Assertions.assertArrayEquals(dataComm1, data_sec.address_factory(true, dataComm));
    }
    @Test
    void valid_data(){
        Assertions.assertArrayEquals(validData1, data_sec.address_factory(true, validData));
    }
    @Test
    void branch_and_jump(){
        Assertions.assertArrayEquals(branch_j, data_sec.address_factory(false, branchJ));
    }
    @Test
    void label_address(){
        Assertions.assertArrayEquals(labelAddress1, data_sec.address_factory(false, labelAddress));
    }
}
