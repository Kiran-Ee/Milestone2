package Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MnemonicCleanerTest {
    General gen = new General();

    String[] r1_strArr = new String[]{"add", "$s1", "$s2", "$s3"};
    String r1 = "add $s1, $s2, $s3";
    String r1_comment = "add $s1, $s2, $s3#comment";
    String r1_spacesTab = " add  $s1,$s2,     $s3     ";
    String r1_comment_with_spacesTab = " add  $s1,        $s2,     $s3     #comment";

    String[] i1_strArr = new String[]{"addiu", "$s1", "$s2", "10"};
    String i1 = "addiu $s1, $s2, 10";
    String i1_comment = "addiu $s1, $s2, 10#comment";
    String i1_spacesTab = " addiu   $s1,$s2,    10     ";
    String i1_comment_with_spacesTab = " addiu  $s1,        $s2,     10     #comment";

    String[] i1_hex_strArr = new String[]{"addiu", "$s1", "$s2", "0x10"};
    String i1_hex = "addiu $s1, $s2, 0x10";
    String i1_hex_comment = "addiu $s1, $s2, 0x10#comment";
    String i1_hex_spacesTab = " addiu       $s1,    $s2,0x10     ";
    String i1_hex_comment_with_spacesTab = " addiu   $s1,$s2,   0x10     #comment";

    String[] i1_neg_strArr = new String[]{"addiu", "$s1", "$s2", "-10"};
    String i1_neg = "addiu $s1, $s2, -10";
    String i1_neg_comment = "addiu $s1, $s2, -10#comment";
    String i1_neg_spacesTab = " addiu    $s1,$s2,   -10     ";
    String i1_neg_comment_with_spacesTab = " addiu    $s1,$s2,   -10     #comment";

    // Should jumps be able to take dec,neg dec, hex, or binary? (Book just shows labels)
    String[] j1_strArr = new String[]{"j", "1000"};
    String j1 = "j 1000"; // assuming just decimal? 1000 = 03E8
    String j1_comment = "j 1000#comment";
    String j1_spacesTab = " j      1000     ";
    String j1_comment_with_spacesTab = " j      1000          #comment";

    // New professor's format
    String[] r2_strArr = new String[]{"add", "$s1", "$s2", "$3"};
    String r2 = "add $s1, $s2, $3";
    String r2_comment = "add $s1, $s2, $3#comment";
    String r2_spacesTab = " add  $s1,$s2,     $3     ";
    String r2_comment_with_spacesTab = " add  $s1,        $s2,     $3     #comment";

    String[] prof1_strArr = new String[]{"or", "$t2", "$ra", "$t6"};
    String prof1 = " or $t2, $ra, $t6# Comment";

    String[] prof2_strArr = new String[]{"or", "$k0", "$gp", "$ra"};
    String prof2 = "or $k0, $gp, $ra# Comment";

    String[] prof3_strArr = new String[]{"andi", "$zero", "$ra", "9"};
    String prof3 = " andi $zero, $ra, 9";

    String[] prof4_strArr = new String[]{"sw", "$v0", "($a1)"};
    String prof4 = "sw $v0, ($a1)# Comment";

    String[] prof5_strArr = new String[]{"sw", "$v0", "0x($a1)"};
    String prof5 = "sw $v0, 0x($a1)# Comment";

    // Testing Return Type
    @Test
    void return_StringArr() {
        assertInstanceOf(String[].class, gen.mnemonic_cleaner(r1));
    }

    // Testing R Return Array
    @Test
    void return_correct_r1_StringArr() {
        assertArrayEquals(r1_strArr, gen.mnemonic_cleaner(r1));
    }

    @Test
    void return_correct_r1_comment_StringArr() {
        assertArrayEquals(r1_strArr, gen.mnemonic_cleaner(r1_comment));
    }

    @Test
    void return_correct_r1_spacesTab_StringArr() {
        assertArrayEquals(r1_strArr, gen.mnemonic_cleaner(r1_spacesTab));
    }

    @Test
    void return_correct_r1_comment_with_spacesTab_StringArr() {
        assertArrayEquals(r1_strArr, gen.mnemonic_cleaner(r1_comment_with_spacesTab));
    }

    // Testing I Return Array
    @Test
    void return_correct_i1_StringArr() {
        assertArrayEquals(i1_strArr, gen.mnemonic_cleaner(i1));
    }

    @Test
    void return_correct_i1_comment_StringArr() {
        assertArrayEquals(i1_strArr, gen.mnemonic_cleaner(i1_comment));
    }

    @Test
    void return_correct_i1_spacesTab_StringArr() {
        assertArrayEquals(i1_strArr, gen.mnemonic_cleaner(i1_spacesTab));
    }

    @Test
    void return_correct_i1_comment_with_spacesTab_StringArr() {
        assertArrayEquals(i1_strArr, gen.mnemonic_cleaner(i1_comment_with_spacesTab));
    }

    // hex immediate
    @Test
    void return_correct_i1_hex_StringArr() {
        assertArrayEquals(i1_hex_strArr, gen.mnemonic_cleaner(i1_hex));
    }

    @Test
    void return_correct_i1_hex_comment_StringArr() {
        assertArrayEquals(i1_hex_strArr, gen.mnemonic_cleaner(i1_hex_comment));
    }

    @Test
    void return_correct_i1_hex_spacesTab_StringArr() {
        assertArrayEquals(i1_hex_strArr, gen.mnemonic_cleaner(i1_hex_spacesTab));
    }

    @Test
    void return_correct_i1_hex_comment_with_spacesTab_StringArr() {
        assertArrayEquals(i1_hex_strArr, gen.mnemonic_cleaner(i1_hex_comment_with_spacesTab));
    }

    // negative immediate
    @Test
    void return_correct_i1_neg_StringArr() {
        assertArrayEquals(i1_neg_strArr, gen.mnemonic_cleaner(i1_neg));
    }

    @Test
    void return_correct_i1_neg_comment_StringArr() {
        assertArrayEquals(i1_neg_strArr, gen.mnemonic_cleaner(i1_neg_comment));
    }

    @Test
    void return_correct_i1_neg_spacesTab_StringArr() {
        assertArrayEquals(i1_neg_strArr, gen.mnemonic_cleaner(i1_neg_spacesTab));
    }

    @Test
    void return_correct_i1_neg_comment_with_spacesTab_StringArr() {
        assertArrayEquals(i1_neg_strArr, gen.mnemonic_cleaner(i1_neg_comment_with_spacesTab));
    }

    // Testing J Return Array
    @Test
    void return_correct_j1_StringArr() {
        assertArrayEquals(j1_strArr, gen.mnemonic_cleaner(j1));
    }

    @Test
    void return_correct_j1_comment_StringArr() {
        assertArrayEquals(j1_strArr, gen.mnemonic_cleaner(j1_comment));
    }

    @Test
    void return_correct_j1_spacesTab_StringArr() {
        assertArrayEquals(j1_strArr, gen.mnemonic_cleaner(j1_spacesTab));
    }

    @Test
    void return_correct_j1_comment_with_spacesTab_StringArr() {
        assertArrayEquals(j1_strArr, gen.mnemonic_cleaner(j1_comment_with_spacesTab));
    }

    @Test
    void return_correct_r2_StringArr() {
        assertArrayEquals(r2_strArr, gen.mnemonic_cleaner(r2));
    }

    @Test
    void return_correct_r2_comment_StringArr() {
        assertArrayEquals(r2_strArr, gen.mnemonic_cleaner(r2_comment));
    }

    @Test
    void return_correct_r2_spacesTab_StringArr() {
        assertArrayEquals(r2_strArr, gen.mnemonic_cleaner(r2_spacesTab));
    }

    @Test
    void return_correct_r2_comment_with_spacesTab_StringArr() {
        assertArrayEquals(r2_strArr, gen.mnemonic_cleaner(r2_comment_with_spacesTab));
    }

    @Test
    void return_prof1() {
        assertArrayEquals(prof1_strArr, gen.mnemonic_cleaner(prof1)); //exposed flaw with ending criterion: it was possible for the end pointer to go past the last character
    }

    @Test
    void return_prof2() {
        assertArrayEquals(prof2_strArr, gen.mnemonic_cleaner(prof2));
    }
    @Test
    void return_prof3() {
        assertArrayEquals(prof3_strArr, gen.mnemonic_cleaner(prof3)); //when last character is the last index.
    }
    @Test
    void return_prof4() {
        assertArrayEquals(prof4_strArr, gen.mnemonic_cleaner(prof4)); //when last character is the last index.
    }

    @Test
    void return_prof5() {
        assertArrayEquals(prof5_strArr, gen.mnemonic_cleaner(prof5)); //when last character is the last index.
    }
}