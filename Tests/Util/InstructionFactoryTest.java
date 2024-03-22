package Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionFactoryTest {
    General gen = new General();
    String[] r1 = new String[]{"add", "$s1", "$s2", "$s3"};
    String[] r1_bad_op = new String[]{"addd", "$s1", "$s2", "$s3"};

    String[] i1 = new String[]{"addiu", "$s1", "$s2", "10"};
    String[] i1_hex = new String[]{"addiu", "$s1", "$s2", "0x10"};

    String[] r2 = new String[]{"and", "$s0", "$s1", "$s2"};

    String[] i2 = new String[]{"andi", "$t0", "$t1", "10"};
    String[] i2_hex = new String[]{"andi", "$s3", "$s4", "0xA"};
    String[] i2_neg = new String[]{"andi", "$t0", "$t1", "-10"}; //-10 ... 10 = 1010 -> 0101 -> 0110 -> 6

    String[] i3 = new String[]{"beq", "$s2", "$s1", "100"}; //100 -> 0x64
    String[] i3_hex = new String[]{"beq", "$s2", "$s1", "0x100"};
    String[] i3_neg = new String[]{"beq", "$s2", "$s1", "-100"}; //-100 ... 100 = 01100100 -> 10011011 -> 10011110 -> 9E

    String[] i4 = new String[]{"bne", "$s1", "$s2", "100"};
    String[] i4_hex = new String[]{"bne", "$s2", "$s1", "0x100"};
    String[] i4_neg = new String[]{"bne", "$s2", "$s1", "-100"}; //-100 ... 9C

    // Should jumps be able to take dec,neg dec, hex, or binary? (Book just shows labels)
    String[] j1 = new String[]{"j", "1000"}; // assuming just decimal? 1000 = 03E8
    String[] j1_hex = new String[]{"j", "0x0"};
    String[] j1_min = new String[]{"j", "-33554432"};
    String[] j1_max = new String[]{"j", "33554431"};


    String[] i5 = new String[]{"lui", "$t5", "2222"}; // 2222 = 08AE
    String[] i5_hex = new String[]{"lui", "$t5", "0x1234"};
    // don't think there are any negatives for LUI

    String[] i6 = new String[]{"lw", "$t7", "100", "$t0"};
    String[] i6_hex = new String[]{"lw", "$t7", "0x100", "$t0"};
    String[] i6_neg = new String[]{"lw", "$t7", "-100", "$t0"};

    String[] r3 = new String[]{"or", "$t5", "$t6", "$s0"};

    String[] i7 = new String[]{"ori", "$t5", "$t6", "9999"};
    String[] i7_hex = new String[]{"ori", "$t5", "$t6", "0x9999"};
    String[] i7_neg = new String[]{"ori", "$t5", "$t6", "-9999"}; //-9999 = D8F1

    String[] r4 = new String[]{"slt", "$t4", "$t4", "$t5"};

    String[] r5 = new String[]{"sub", "$s5", "$s4", "$s1"};

    String[] i8 = new String[]{"sw", "$t2", "100", "$s0"};
    String[] i8_hex = new String[]{"sw", "$t2", "0x100", "$s0"};
    String[] i8_neg = new String[]{"sw", "$t2", "-100", "$s0"}; //-9999 = D8F1

    String[] sys = new String[]{"syscall"};

    String[] prof1 = new String[]{"or", "$k0", "$gp", "$ra"}; // I think his is wrong again
    String[] prof2 = new String[]{"andi", "$zero", "$ra", "9"};

    // Testing Bad Input
    @Test
    void bad_operation() {
        assertThrows(IllegalArgumentException.class, () -> {
            gen.instruction_factory(r1_bad_op);
        });
    }

    // r1
    @Test
    void return_hex_r1() {
        assertEquals("02538820", gen.instruction_factory(r1));
    }

    // i1
    @Test
    void return_hex_i1() {
        assertEquals("2651000a", gen.instruction_factory(i1));
    }

    @Test
    void return_hex_i1Hex() {
        assertEquals("26510010", gen.instruction_factory(i1_hex));
    }

    // r2
    @Test
    void return_hex_r2() {
        assertEquals("02328024", gen.instruction_factory(r2));
    }

    // i2
    @Test
    void return_hex_i2() {
        assertEquals("3128000a", gen.instruction_factory(i2));
    }

    @Test
    void return_hex_i2Hex() {
        assertEquals("3293000a", gen.instruction_factory(i2_hex));
    }

    @Test
    void return_hex_i2Neg() {
        assertEquals("3128fff6", gen.instruction_factory(i2_neg));
    }

    // i3
    @Test
    void return_hex_i3() {
        assertEquals("12510064", gen.instruction_factory(i3));
    }

    @Test
    void return_hex_i3Hex() {
        assertEquals("12510100", gen.instruction_factory(i3_hex));
    }

    @Test
    void return_hex_i3Neg() {
        assertEquals("1251ff9c", gen.instruction_factory(i3_neg));
    }

    // i4
    @Test
    void return_hex_i4() {
        assertEquals("16320064", gen.instruction_factory(i4));
    }

    @Test
    void return_hex_i4Hex() {
        assertEquals("16510100", gen.instruction_factory(i4_hex));
    }

    @Test
    void return_hex_i4Neg() {
        assertEquals("1651ff9c", gen.instruction_factory(i4_neg));
    }

    // j - assuming Signed
    @Test
    void return_hex_j() {
        assertEquals("080003e8", gen.instruction_factory(j1));
    }

    @Test
    void return_hex_j_hex() {
        assertEquals("08000000", gen.instruction_factory(j1_hex));
    }

    @Test
    void return_hex_j_min() {
        assertEquals("0a000000", gen.instruction_factory(j1_min));
    }

    @Test
    void return_hex_j_max() {
        assertEquals("09ffffff", gen.instruction_factory(j1_max));
    }

    // i5
    @Test
    void return_hex_i5() {
        assertEquals("3c0d08ae", gen.instruction_factory(i5));
    }

    @Test
    void return_hex_i5Hex() {
        assertEquals("3c0d1234", gen.instruction_factory(i5_hex));
    }

    // i6
    @Test
    void return_hex_i6() {
        assertEquals("8d0f0064", gen.instruction_factory(i6));
    }

    @Test
    void return_hex_i6Hex() {
        assertEquals("8d0f0100", gen.instruction_factory(i6_hex));
    }

    @Test
    void return_hex_i6Neg() {
        assertEquals("8d0fff9c", gen.instruction_factory(i6_neg));
    }

    // r3
    @Test
    void return_hex_r3() {
        assertEquals("01d06825", gen.instruction_factory(r3));
    }

    // i7
    @Test
    void return_hex_i7() {
        assertEquals("35cd270f", gen.instruction_factory(i7));
    }

    @Test
    void return_hex_i7Hex() {
        assertEquals("35cd9999", gen.instruction_factory(i7_hex));
    }

    @Test
    void return_hex_i7Neg() {
        assertEquals("35cdd8f1", gen.instruction_factory(i7_neg));
    }

    // r4
    @Test
    void return_hex_r4() {
        assertEquals("018d602a", gen.instruction_factory(r4));
    }

    // r5
    @Test
    void return_hex_r5() {
        assertEquals("0291a822", gen.instruction_factory(r5));
    }

    // i8
    @Test
    void return_hex_i8() {
        assertEquals("ae0a0064", gen.instruction_factory(i8));
    }

    @Test
    void return_hex_i8Hex() {
        assertEquals("ae0a0100", gen.instruction_factory(i8_hex));
    }

    @Test
    void return_hex_i8Neg() {
        assertEquals("ae0aff9c", gen.instruction_factory(i8_neg));
    }

    // syscall
    @Test
    void return_hex_syscall() {
        assertEquals("0000000c", gen.instruction_factory(sys));
    }

//    @Test
//    void return_prof1() {
//        assertEquals("039df025", gen.instruction_factory(prof1)); //wrong?
//    }

    @Test
    void return_prof2() {
        assertEquals("33e00009", gen.instruction_factory(prof2)); //wrong?
    }
}