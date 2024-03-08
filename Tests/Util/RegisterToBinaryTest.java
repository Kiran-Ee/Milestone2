package Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Assuming all registers are 5 bits
class RegisterToBinaryTest {
    String zero = "$zero";

    String at = "$at";

    String v0 = "$v0";
    String v1 = "$v1";

    String a0 = "$a0";
    String a1 = "$a1";
    String a2 = "$a2";
    String a3 = "$a3";

    String t0 = "$t0";
    String t1 = "$t1";
    String t2 = "$t2";
    String t3 = "$t3";
    String t4 = "$t4";
    String t5 = "$t5";
    String t6 = "$t6";
    String t7 = "$t7";

    String s0 = "$s0";
    String s1 = "$s1";
    String s2 = "$s2";
    String s3 = "$s3";
    String s4 = "$s4";
    String s5 = "$s5";
    String s6 = "$s6";
    String s7 = "$s7";

    String t8 = "$t8";
    String t9 = "$t9";

    String k0 = "$k0";
    String k1 = "$k1";

    // Shouldn't need to worry about these?
    String gp = "$gp";

    String sp = "$sp";

    String fp = "$fp";

    String ra = "$ra";

    // THIS NEED TO BE UPDATED
    String zero_num = "$0";
    String zero_reg = "$r0";

    String at_num = "$1";

    String v0_num = "$2";
    String v1_num = "$3";

    String a0_num = "$4";
    String a1_num = "$5";
    String a2_num = "$6";
    String a3_num = "$7";

    String t0_num = "$8";
    String t1_num = "$9";
    String t2_num = "$10";
    String t3_num = "$11";
    String t4_num = "$12";
    String t5_num = "$13";
    String t6_num = "$14";
    String t7_num = "$15";

    String s0_num = "$16";
    String s1_num = "$17";
    String s2_num = "$18";
    String s3_num = "$19";
    String s4_num = "$20";
    String s5_num = "$21";
    String s6_num = "$22";
    String s7_num = "$23";

    String t8_num = "$24";
    String t9_num = "$25";

    String k0_num = "$26";
    String k1_num = "$27";

    // "k" is for operating system so no way he's testing that.
    String gp_num = "$28";

    String sp_num = "$29";

    String fp_num = "$30";

    String ra_num = "$31";

    // Testing Bad Input
    @Test
    void bad_input() {
        assertThrows(IllegalArgumentException.class, () -> {
            General.register_to_binary("bad input!");
        });
    }

    // Testing Register Format
    @Test
    void zero_binary() {
        assertEquals("00000", General.register_to_binary(zero));
    }

    @Test
    void at_binary() {
        assertEquals("00001", General.register_to_binary(at));
    }

    @Test
    void v0_binary() {
        assertEquals("00010", General.register_to_binary(v0));
    }

    @Test
    void v1_binary() {
        assertEquals("00011", General.register_to_binary(v1));
    }

    @Test
    void a0_binary() {
        assertEquals("00100", General.register_to_binary(a0));
    }

    @Test
    void a1_binary() {
        assertEquals("00101", General.register_to_binary(a1));
    }

    @Test
    void a2_binary() {
        assertEquals("00110", General.register_to_binary(a2));
    }

    @Test
    void a3_binary() {
        assertEquals("00111", General.register_to_binary(a3));
    }

    @Test
    void t0_binary() {
        assertEquals("01000", General.register_to_binary(t0));
    }

    @Test
    void t1_binary() {
        assertEquals("01001", General.register_to_binary(t1));
    }

    @Test
    void t2_binary() {
        assertEquals("01010", General.register_to_binary(t2));
    }

    @Test
    void t3_binary() {
        assertEquals("01011", General.register_to_binary(t3));
    }

    @Test
    void t4_binary() {
        assertEquals("01100", General.register_to_binary(t4));
    }

    @Test
    void t5_binary() {
        assertEquals("01101", General.register_to_binary(t5));
    }

    @Test
    void t6_binary() {
        assertEquals("01110", General.register_to_binary(t6));
    }

    @Test
    void t7_binary() {
        assertEquals("01111", General.register_to_binary(t7));
    }

    @Test
    void s0_binary() {
        assertEquals("10000", General.register_to_binary(s0));
    }

    @Test
    void s1_binary() {
        assertEquals("10001", General.register_to_binary(s1));
    }

    @Test
    void s2_binary() {
        assertEquals("10010", General.register_to_binary(s2));
    }

    @Test
    void s3_binary() {
        assertEquals("10011", General.register_to_binary(s3));
    }

    @Test
    void s4_binary() {
        assertEquals("10100", General.register_to_binary(s4));
    }

    @Test
    void s5_binary() {
        assertEquals("10101", General.register_to_binary(s5));
    }

    @Test
    void s6_binary() {
        assertEquals("10110", General.register_to_binary(s6));
    }

    @Test
    void s7_binary() {
        assertEquals("10111", General.register_to_binary(s7));
    }

    @Test
    void t8_binary() {
        assertEquals("11000", General.register_to_binary(t8));
    }

    @Test
    void t9_binary() {
        assertEquals("11001", General.register_to_binary(t9));
    }

    @Test
    void k0_binary() {
        assertEquals("11010", General.register_to_binary(k0));
    }

    @Test
    void k1_binary() {
        assertEquals("11011", General.register_to_binary(k1));
    }

    @Test
    void gp_binary() {
        assertEquals("11100", General.register_to_binary(gp));
    }

    @Test
    void sp_binary() {
        assertEquals("11101", General.register_to_binary(sp));
    }

    @Test
    void fp_binary() {
        assertEquals("11110", General.register_to_binary(fp));
    }


    @Test
    void ra_binary() {
        assertEquals("11111", General.register_to_binary(ra));
    }


    // Testing Number Register
    @Test
    void zero_num_binary() {
        assertEquals("00000", General.register_to_binary(zero_num));
    }

    @Test
    void zero_reg_binary() {
        assertEquals("00000", General.register_to_binary(zero_reg));
    }

    @Test
    void at_num_binary() {
        assertEquals("00001", General.register_to_binary(at_num));
    }

    @Test
    void v0_num_binary() {
        assertEquals("00010", General.register_to_binary(v0_num));
    }

    @Test
    void v1_num_binary() {
        assertEquals("00011", General.register_to_binary(v1_num));
    }

    @Test
    void a0_num_binary() {
        assertEquals("00100", General.register_to_binary(a0_num));
    }

    @Test
    void a1_num_binary() {
        assertEquals("00101", General.register_to_binary(a1_num));
    }

    @Test
    void a2_num_binary() {
        assertEquals("00110", General.register_to_binary(a2_num));
    }

    @Test
    void a3_num_binary() {
        assertEquals("00111", General.register_to_binary(a3_num));
    }

    @Test
    void t0_num_binary() {
        assertEquals("01000", General.register_to_binary(t0_num));
    }

    @Test
    void t1_num_binary() {
        assertEquals("01001", General.register_to_binary(t1_num));
    }

    @Test
    void t2_num_binary() {
        assertEquals("01010", General.register_to_binary(t2_num));
    }

    @Test
    void t3_num_binary() {
        assertEquals("01011", General.register_to_binary(t3_num));
    }

    @Test
    void t4_num_binary() {
        assertEquals("01100", General.register_to_binary(t4_num));
    }

    @Test
    void t5_num_binary() {
        assertEquals("01101", General.register_to_binary(t5_num));
    }

    @Test
    void t6_num_binary() {
        assertEquals("01110", General.register_to_binary(t6_num));
    }

    @Test
    void t7_num_binary() {
        assertEquals("01111", General.register_to_binary(t7_num));
    }

    @Test
    void s0_num_binary() {
        assertEquals("10000", General.register_to_binary(s0_num));
    }

    @Test
    void s1_num_binary() {
        assertEquals("10001", General.register_to_binary(s1_num));
    }

    @Test
    void s2_num_binary() {
        assertEquals("10010", General.register_to_binary(s2_num));
    }

    @Test
    void s3_num_binary() {
        assertEquals("10011", General.register_to_binary(s3_num));
    }

    @Test
    void s4_num_binary() {
        assertEquals("10100", General.register_to_binary(s4_num));
    }

    @Test
    void s5_num_binary() {
        assertEquals("10101", General.register_to_binary(s5_num));
    }

    @Test
    void s6_num_binary() {
        assertEquals("10110", General.register_to_binary(s6_num));
    }

    @Test
    void s7_num_binary() {
        assertEquals("10111", General.register_to_binary(s7_num));
    }

    @Test
    void t8_num_binary() {
        assertEquals("11000", General.register_to_binary(t8_num));
    }

    @Test
    void t9_num_binary() {
        assertEquals("11001", General.register_to_binary(t9_num));
    }

    @Test
    void gp_num_binary() {
        assertEquals("11100", General.register_to_binary(gp_num));
    }

    @Test
    void sp_num_binary() {
        assertEquals("11101", General.register_to_binary(sp_num));
    }

    @Test
    void fp_num_binary() {
        assertEquals("11110", General.register_to_binary(fp_num));
    }


    @Test
    void ra_num_binary() {
        assertEquals("11111", General.register_to_binary(ra_num));
    }

}