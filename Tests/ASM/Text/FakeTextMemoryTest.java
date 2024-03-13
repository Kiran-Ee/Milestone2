package ASM.Text;

import ASM.TextSection;

class FakeTextMemoryTest {
    // Probably need to use MARS to get calculate the correct output
    TextSection text_sec = new TextSection();
    String[] r1 = new String[]{"add", "$s1", "$s2", "$s3"};
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
    String[] j1 = new String[]{"j", "1000"}; // assuming just decimal? 1000 = 03E8
    String[] j1_hex = new String[]{"j", "0x0"};
    String[] j1_min = new String[]{"j", "-33554432"};
    String[] j1_max = new String[]{"j", "33554431"};
    String[] i5 = new String[]{"lui", "$t5", "2222"}; // 2222 = 08AE
    String[] i5_hex = new String[]{"lui", "$t5", "0x1234"};
    String[] i6 = new String[]{"lw", "$t7", "100($t0)"};
    String[] i6_hex = new String[]{"lw", "$t7", "0x100($t0)"};
    String[] i6_neg = new String[]{"lw", "$t7", "-100($t0)"};
    String[] r3 = new String[]{"or", "$t5", "$t6", "$s0"};
    String[] i7 = new String[]{"ori", "$t5", "$t6", "9999"};
    String[] i7_hex = new String[]{"ori", "$t5", "$t6", "0x9999"};
    String[] i7_neg = new String[]{"ori", "$t5", "$t6", "-9999"}; //-9999 = D8F1
    String[] r4 = new String[]{"slt", "$t4", "$t4", "$t5"};
    String[] r5 = new String[]{"sub", "$s5", "$s4", "$s1"};
    String[] i8 = new String[]{"sw", "$t2", "100($s0)"};
    String[] i8_hex = new String[]{"sw", "$t2", "0x100($s0)"};
    String[] i8_neg = new String[]{"sw", "$t2", "-100($s0)"}; //-9999 = D8F1
    String[] sys = new String[]{"syscall"};
    String[] prof1 = new String[]{"or", "$k0", "$gp", "$ra"};
    String[] prof2 = new String[]{"andi", "$zero", "$ra", "9"};

    String[][] s1Sec1 = new String[][]{r1};
    String[][] s1Sec2 = new String[][]{i1};
    String[][] s1Sec3 = new String[][]{j1};

    String[][] s3Sec1 = new String[][]{r1, i1, i1_hex};
    String[][] s3Sec2 = new String[][]{r2, i2, i2_hex};
    String[][] s3Sec3 = new String[][]{i2_neg, i3, i3_hex};
    String[][] s3Sec4 = new String[][]{i4, i4_hex, i4_neg};
    String[][] s3Sec5 = new String[][]{j1, j1_hex, j1_min};
    String[][] s3Sec6 = new String[][]{j1_max, i5, i5_hex};
    String[][] s3Sec7 = new String[][]{i6, i6_hex, i6_neg};
    String[][] s3Sec8 = new String[][]{r3, i7, i7_hex};
    String[][] s3Sec9 = new String[][]{i7_neg, r4, r5};
    String[][] s3Sec10 = new String[][]{i8, i8_hex, i8_neg};
    String[][] s3Sec11 = new String[][]{sys, prof1, prof2, i3_neg};

}