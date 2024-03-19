package ASM.Text;

import ASM.TextSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextLineCleanerTest {
    TextSection text_sec = new TextSection();

    // Testing Mnemonic Cleaner Instructions
    String[] r1_strArr = new String[]{"add", "$s1", "$s2", "$s3"};
    String r1 = "add $s1,$s2, $s3";
    String r1_comment = "add $s1, $s2, $s3#comment after instruciton";
    String r1_spacesTab = " add  $s1,$s2,     $s3     ";
    String r1_comment_with_spacesTab = " add  $s1,        $s2,     $s3     #comment";

    String[] i1_strArr = new String[]{"addiu", "$s1", "$s2", "10"};
    String[] i1_hex_strArr = new String[]{"addiu", "$s1", "$s2", "0x10"};
    String[] i1_neg_strArr = new String[]{"addiu", "$s1", "$s2", "-10"};
    String i1 = "addiu $s1,$s2, 10";
    String i1_comment = "addiu $s1, $s2, 10#comment";
    String i1_spacesTab = " addiu   $s1,$s2,    10     ";
    String i1_comment_with_spacesTab = " addiu  $s1,        $s2,     10     #comment";
    String i1_hex = "addiu $s1,$s2, 0x10";
    String i1_hex_comment = "addiu $s1, $s2, 0x10#comment";
    String i1_hex_spacesTab = " addiu       $s1,    $s2,0x10     ";
    String i1_hex_comment_with_spacesTab = " addiu   $s1,$s2,   0x10     #comment";
    String i1_neg = "addiu $s1, $s2, -10";
    String i1_neg_comment = "addiu $s1, $s2, -10#comment";
    String i1_neg_spacesTab = " addiu    $s1,$s2,   -10     ";
    String i1_neg_comment_with_spacesTab = " addiu    $s1,$s2,   -10     #comment";

    // Label
    String[] j1_strArr = new String[]{"j", "JumpLabel"};
    String j1 = "j JumpLabel";
    String j1_comment = "j JumpLabel#comment";
    String j1_spacesTab = " j      JumpLable     ";
    String j1_comment_with_spacesTab = " j      JumpLable          #comment";

    String[] bne1_strArr = new String[]{"bne", "$t0", "$zero", "ITS_EVEN"};
    String bne1 = "bne $t0, $zero, ITS_EVEN";
    String bne1_comment = "bne $t0, $zero, ITS_EVEN#comment comment";
    String bne1_spacesTab = " bne    $t0,$zero,     ITS_EVEN";
    String bne1_comment_with_spacesTab = "  bne    $t0,$zero,     ITS_EVEN    #comment";

    String[] beq1_strArr = new String[]{"beq", "$t0", "$zero", "ITS_EVEN"};
    String beq1 = "beq $t0, $zero, ITS_EVEN";
    String beq1_comment = "beq $t0, $zero, ITS_EVEN#comment comment";
    String beq1_spacesTab = " beq    $t0,$zero,     ITS_EVEN";
    String beq1_comment_with_spacesTab = "  beq    $t0,$zero,     ITS_EVEN    #comment";

    // Empty/Comment lines
    String[] empty1_strArr = new String[]{""};
    String empty1 = "";
    String empty2 = " ";
    String empty3 = "    ";

    String[] comment1_strArr = new String[]{""};
    String comment1 = "#";
    String comment2 = " #comment";
    String comment3 = "    #randomline but multiple words with #$,:.-[q           ";


    // Pseodos
    String[] li_strArr = new String[]{"li", "$s1", "10"};
    String[] li_hex_strArr = new String[]{"li", "$s1", "0x10"};
    String[] li_neg_strArr = new String[]{"li", "$s1", "-10"};
    String li = "li $s1, 10";
    String li_comment = "li $s1,  10#comment";
    String li_spacesTab = " li   $s1,10     ";
    String li_comment_with_spacesTab = " li  $s1,             10     #comment";
    String li_hex = "li $s1,0x10";
    String li_hex_comment = "li $s1, 0x10#comment";
    String li_hex_spacesTab = " li       $s1,0x10     ";
    String li_hex_comment_with_spacesTab = " li   $s1,0x10     #comment";
    String li_neg = "li $s1, -10";
    String li_neg_comment = "li $s1, -10#comment";
    String li_neg_spacesTab = " li    $s1,-10     ";
    String li_neg_comment_with_spacesTab = " li    $s1,-10     #comment";

    String[] la_strArr = new String[]{"la", "$s1", "ITS_ODD"};
    String[] la_hex_strArr = new String[]{"la", "$s1", "0xITS_ODD"};
    String[] la_neg_strArr = new String[]{"la", "$s1", "-ITS_ODD"};
    String la = "la $s1, ITS_ODD";
    String la_comment = "la $s1,  ITS_ODD#comment";
    String la_spacesTab = " la   $s1,ITS_ODD     ";
    String la_comment_with_spacesTab = " la  $s1,             ITS_ODD     #comment";
    String la_hex = "la $s1,0xITS_ODD";
    String la_hex_comment = "la $s1, 0xITS_ODD#comment";
    String la_hex_spacesTab = " la       $s1,0xITS_ODD     ";
    String la_hex_comment_with_spacesTab = " la   $s1,0xITS_ODD     #comment";
    String la_neg = "la $s1, -ITS_ODD";
    String la_neg_comment = "la $s1, -ITS_ODD#comment";
    String la_neg_spacesTab = " la    $s1,-ITS_ODD     ";
    String la_neg_comment_with_spacesTab = " la    $s1,-ITS_ODD     #comment";

    String[] blt_strArr = new String[]{"blt", "$at", "$s3,", "branchtime"};
    String[] blt_hex_strArr = new String[]{"blt", "$at", "0xbranchtime"};
    String[] blt_neg_strArr = new String[]{"blt", "$at", "-branchtime"};
    String blt = "blt, $at, $s3, branchtime";
    String blt_comment = "blt, $at, $s3,  branchtime#comment";
    String blt_spacesTab = " blt, $at,$s3,          branchtime     ";
    String blt_comment_with_spacesTab = " blt, $at,$s3,          branchtime     #comment";
    String blt_hex = "blt, $at, 0xbranchtime";
    String blt_hex_comment = "blt, $at, 0xbranchtime#comment";
    String blt_hex_spacesTab = " blt, $at,0xbranchtime     ";
    String blt_hex_comment_with_spacesTab = " blt,$at,0xbranchtime     #comment";
    String blt_neg = "blt, $at, -branchtime";
    String blt_neg_comment = "blt, $at, -branchtime#comment";
    String blt_neg_spacesTab = " blt,   $at,-branchtime     ";
    String blt_neg_comment_with_spacesTab = " blt,   $at,-branchtime     #comment";

    // r
    @Test
    void setR1() {
        assertArrayEquals(r1_strArr, text_sec.text_line_cleaner(r1));
    }

    @Test
    void setR1_comment() {
        assertArrayEquals(r1_strArr, text_sec.text_line_cleaner(r1_comment));
    }

    @Test
    void setR1_spacesTab() {
        assertArrayEquals(r1_strArr, text_sec.text_line_cleaner(r1_spacesTab));
    }

    @Test
    void setR1_comment_with_spacesTab() {
        assertArrayEquals(r1_strArr, text_sec.text_line_cleaner(r1_comment_with_spacesTab));
    }

    // i
    @Test
    void setI1() {
        assertArrayEquals(i1_strArr, text_sec.text_line_cleaner(i1));
    }

    @Test
    void setI1_comment() {
        assertArrayEquals(i1_strArr, text_sec.text_line_cleaner(i1_comment));
    }

    @Test
    void setI1_spacesTab() {
        assertArrayEquals(i1_strArr, text_sec.text_line_cleaner(i1_spacesTab));
    }

    @Test
    void setI1_comment_with_spacesTab() {
        assertArrayEquals(i1_strArr, text_sec.text_line_cleaner(i1_comment_with_spacesTab));
    }

    @Test
    void setI1_hex() {
        assertArrayEquals(i1_hex_strArr, text_sec.text_line_cleaner(i1_hex));
    }

    @Test
    void setI1_hex_comment() {
        assertArrayEquals(i1_hex_strArr, text_sec.text_line_cleaner(i1_hex_comment));
    }

    @Test
    void setI1_hex_spacesTab() {
        assertArrayEquals(i1_hex_strArr, text_sec.text_line_cleaner(i1_hex_spacesTab));
    }

    @Test
    void setI1_hex_comment_with_spacesTab() {
        assertArrayEquals(i1_hex_strArr, text_sec.text_line_cleaner(i1_hex_comment_with_spacesTab));
    }

    @Test
    void setI1_neg() {
        assertArrayEquals(i1_neg_strArr, text_sec.text_line_cleaner(i1_neg));
    }

    @Test
    void setI1_neg_comment() {
        assertArrayEquals(i1_neg_strArr, text_sec.text_line_cleaner(i1_neg_comment));
    }

    @Test
    void setI1_neg_spacesTab() {
        assertArrayEquals(i1_neg_strArr, text_sec.text_line_cleaner(i1_neg_spacesTab));
    }

    @Test
    void setI1_neg_comment_with_spacesTab() {
        assertArrayEquals(i1_neg_strArr, text_sec.text_line_cleaner(i1_neg_comment_with_spacesTab));
    }


    // Labels
    @Test
    void setJ1() {
        assertArrayEquals(j1_strArr, text_sec.text_line_cleaner(j1));
    }

    @Test
    void setJ1_comment() {
        assertArrayEquals(j1_strArr, text_sec.text_line_cleaner(j1_comment));
    }

    @Test
    void setJ1_spacesTab() {
        assertArrayEquals(j1_strArr, text_sec.text_line_cleaner(j1_spacesTab));
    }

    @Test
    void setJ1_comment_with_spacesTab() {
        assertArrayEquals(j1_strArr, text_sec.text_line_cleaner(j1_comment_with_spacesTab));
    }

    @Test
    void setBne1() {
        assertArrayEquals(bne1_strArr, text_sec.text_line_cleaner(bne1));
    }

    @Test
    void setBne1_comment() {
        assertArrayEquals(bne1_strArr, text_sec.text_line_cleaner(bne1_comment));
    }

    @Test
    void setBne1_spacesTab() {
        assertArrayEquals(bne1_strArr, text_sec.text_line_cleaner(bne1_spacesTab));
    }

    @Test
    void setBne1_comment_with_spacesTab() {
        assertArrayEquals(bne1_strArr, text_sec.text_line_cleaner(bne1_comment_with_spacesTab));
    }


    @Test
    void setBeq1() {
        assertArrayEquals(beq1_strArr, text_sec.text_line_cleaner(beq1));
    }

    @Test
    void setBBeq1_comment() {
        assertArrayEquals(beq1_strArr, text_sec.text_line_cleaner(beq1_comment));
    }

    @Test
    void setBBeq1_spacesTab() {
        assertArrayEquals(beq1_strArr, text_sec.text_line_cleaner(beq1_spacesTab));
    }

    @Test
    void setBBeq1_comment_with_spacesTab() {
        assertArrayEquals(beq1_strArr, text_sec.text_line_cleaner(beq1_comment_with_spacesTab));
    }

    // Empty
    @Test
    void setEmpty1() {
        assertArrayEquals(empty1_strArr, text_sec.text_line_cleaner(empty1));
    }

    @Test
    void setEmpty2() {
        assertArrayEquals(empty1_strArr, text_sec.text_line_cleaner(empty2));
    }

    @Test
    void setEmpty3() {
        assertArrayEquals(empty1_strArr, text_sec.text_line_cleaner(empty3));
    }

    // Comments
    @Test
    void setComment1() {
        assertArrayEquals(comment1_strArr, text_sec.text_line_cleaner(comment1));
    }

    @Test
    void setComment2() {
        assertArrayEquals(comment1_strArr, text_sec.text_line_cleaner(comment2));
    }

    @Test
    void setComment3() {
        assertArrayEquals(comment1_strArr, text_sec.text_line_cleaner(comment3));
    }

    // Pseudos
    // i=li
    @Test
    void setLi1() {
        assertArrayEquals(li_strArr, text_sec.text_line_cleaner(li));
    }

    @Test
    void setLi_comment() {
        assertArrayEquals(li_strArr, text_sec.text_line_cleaner(li_comment));
    }

    @Test
    void setLi1_spacesTab() {
        assertArrayEquals(li_strArr, text_sec.text_line_cleaner(li_spacesTab));
    }

    @Test
    void setLi1_comment_with_spacesTab() {
        assertArrayEquals(li_strArr, text_sec.text_line_cleaner(li_comment_with_spacesTab));
    }

    @Test
    void setLi1_hex() {
        assertArrayEquals(li_hex_strArr, text_sec.text_line_cleaner(li_hex));
    }

    @Test
    void setLi1_hex_comment() {
        assertArrayEquals(li_hex_strArr, text_sec.text_line_cleaner(li_hex_comment));
    }

    @Test
    void setLi1_hex_spacesTab() {
        assertArrayEquals(li_hex_strArr, text_sec.text_line_cleaner(li_hex_spacesTab));
    }

    @Test
    void setLi1_hex_comment_with_spacesTab() {
        assertArrayEquals(li_hex_strArr, text_sec.text_line_cleaner(li_hex_comment_with_spacesTab));
    }

    @Test
    void setLi1_neg() {
        assertArrayEquals(li_neg_strArr, text_sec.text_line_cleaner(li_neg));
    }

    @Test
    void setLi1_neg_comment() {
        assertArrayEquals(li_neg_strArr, text_sec.text_line_cleaner(li_neg_comment));
    }

    @Test
    void setLi1_neg_spacesTab() {
        assertArrayEquals(li_neg_strArr, text_sec.text_line_cleaner(li_neg_spacesTab));
    }

    @Test
    void setLi1_neg_comment_with_spacesTab() {
        assertArrayEquals(li_neg_strArr, text_sec.text_line_cleaner(li_neg_comment_with_spacesTab));
    }

    // La
    @Test
    void setLa1() {
        assertArrayEquals(la_strArr, text_sec.text_line_cleaner(la));
    }

    @Test
    void setLa_comment() {
        assertArrayEquals(la_strArr, text_sec.text_line_cleaner(la_comment));
    }

    @Test
    void setLa1_spacesTab() {
        assertArrayEquals(la_strArr, text_sec.text_line_cleaner(la_spacesTab));
    }

    @Test
    void setLa1_comment_with_spacesTab() {
        assertArrayEquals(la_strArr, text_sec.text_line_cleaner(la_comment_with_spacesTab));
    }

    @Test
    void setLa1_hex() {
        assertArrayEquals(la_hex_strArr, text_sec.text_line_cleaner(la_hex));
    }

    @Test
    void setLa1_hex_comment() {
        assertArrayEquals(la_hex_strArr, text_sec.text_line_cleaner(la_hex_comment));
    }

    @Test
    void setLa1_hex_spacesTab() {
        assertArrayEquals(la_hex_strArr, text_sec.text_line_cleaner(la_hex_spacesTab));
    }

    @Test
    void setLa1_hex_comment_with_spacesTab() {
        assertArrayEquals(la_hex_strArr, text_sec.text_line_cleaner(la_hex_comment_with_spacesTab));
    }

    @Test
    void setLa1_neg() {
        assertArrayEquals(la_neg_strArr, text_sec.text_line_cleaner(la_neg));
    }

    @Test
    void setLa1_neg_comment() {
        assertArrayEquals(la_neg_strArr, text_sec.text_line_cleaner(la_neg_comment));
    }

    @Test
    void setLa1_neg_spacesTab() {
        assertArrayEquals(la_neg_strArr, text_sec.text_line_cleaner(la_neg_spacesTab));
    }

    @Test
    void setLa1_neg_comment_with_spacesTab() {
        assertArrayEquals(la_neg_strArr, text_sec.text_line_cleaner(la_neg_comment_with_spacesTab));
    }

    // BLT
    @Test
    void setBlt() {
        assertArrayEquals(blt_strArr, text_sec.text_line_cleaner(blt));
    }

    @Test
    void setBlt_comment() {
        assertArrayEquals(blt_strArr, text_sec.text_line_cleaner(blt_comment));
    }

    @Test
    void setBlt_spacesTab() {
        assertArrayEquals(blt_strArr, text_sec.text_line_cleaner(blt_spacesTab));
    }

    @Test
    void setBlt_comment_with_spacesTab() {
        assertArrayEquals(blt_strArr, text_sec.text_line_cleaner(blt_comment_with_spacesTab));
    }

    @Test
    void setBlt_hex() {
        assertArrayEquals(blt_hex_strArr, text_sec.text_line_cleaner(blt_hex));
    }

    @Test
    void setBlt_hex_comment() {
        assertArrayEquals(blt_hex_strArr, text_sec.text_line_cleaner(blt_hex_comment));
    }

    @Test
    void setBlt_hex_spacesTab() {
        assertArrayEquals(blt_hex_strArr, text_sec.text_line_cleaner(blt_hex_spacesTab));
    }

    @Test
    void setBlt_hex_comment_with_spacesTab() {
        assertArrayEquals(blt_hex_strArr, text_sec.text_line_cleaner(blt_hex_comment_with_spacesTab));
    }

    @Test
    void setBlt_neg() {
        assertArrayEquals(blt_neg_strArr, text_sec.text_line_cleaner(blt_neg));
    }

    @Test
    void setBlt_neg_comment() {
        assertArrayEquals(blt_neg_strArr, text_sec.text_line_cleaner(blt_neg_comment));
    }

    @Test
    void setBlt_neg_spacesTab() {
        assertArrayEquals(blt_neg_strArr, text_sec.text_line_cleaner(blt_neg_spacesTab));
    }

    @Test
    void setBlt_neg_comment_with_spacesTab() {
        assertArrayEquals(blt_neg_strArr, text_sec.text_line_cleaner(blt_neg_comment_with_spacesTab));
    }

}