package Util;

import Operations.*;

import java.util.ArrayList;

public class General {

    public String instruction_to_hex(String mnemonic) {
        String[] cleaned_instr = mnemonic_cleaner(mnemonic); // returns ["add", "s0", "s1", "t7"]
        String hex_instr = instruction_factory(cleaned_instr); // returns "022F8020"
        return hex_instr;
    }

    /**
     * @param: mnemonic - A String containing to desired instruction to parse into it’s respective parts.
     * @return: Cleaned list of strings representing 1 part of an assembly instruction.
     * Example: “  add$s0, $a1,      $t7 #ignore" ->  ["add","$s0","$a1","t7"]
     */
    public static String[] mnemonic_cleaner(String mnemonic) {
        ArrayList<String> cleaned_instr_AL = new ArrayList<>(); // for dynamically adding
        int index_last_index = find_last_index(mnemonic); // last valid index of assembly instruction
        int index_start_char = find_non_space(0, mnemonic); // pointer for beginning of current instruction
        int index_end_char = -1; // pointer for end of current instruction

        while (index_start_char <= index_last_index) { // stop when reached last index
            int[] try_end_vals = new int[5];
            try_end_vals[0] = mnemonic.indexOf("$", index_start_char + 1);
            try_end_vals[1] = mnemonic.indexOf(",", index_start_char + 1);
            try_end_vals[2] = mnemonic.indexOf(" ", index_start_char + 1);
            try_end_vals[3] = mnemonic.indexOf(")", index_start_char + 1);
            try_end_vals[4] = mnemonic.indexOf("(", index_start_char);
            index_end_char = index_last_index + 1; // assuming last index is min unless proven o.w.

            if (try_end_vals[4] == index_start_char) { // handling offset: final term
                index_start_char = try_end_vals[4] + 1; // "(" + 1
                index_end_char = try_end_vals[3]; // ")"
            } else {
                for (int i : try_end_vals) {
                    if (i == -1) continue;
                    index_end_char = Math.min(index_end_char, i);
                }
            }

            String op = mnemonic.substring(index_start_char, index_end_char);
            cleaned_instr_AL.add(op);

            index_start_char = find_non_space(index_end_char + 1, mnemonic); // advancing to next "non-space" char
        }

        // Converting ArrayList<String> -> String[]
        String[] cleaned_instr = new String[cleaned_instr_AL.size()];
        cleaned_instr = cleaned_instr_AL.toArray(cleaned_instr);
        return cleaned_instr;
    }

    /*
        Helper method for "mnemonic_cleaner". Looking for 1st "non" white space character.
        @param:
            start – the starting index, inclusive, to search from
            str – input – the string to search for a non-white space.
        @return:
            returns the start index if no space found
     */
    public static int find_non_space(int start, String str) {
        int index_non_space = -1;
        for (int i = start; i < str.length(); i++) {
            char s = str.charAt(i);
            if (!Character.isWhitespace(s)) { // should check for spaces, tabs, and other white space char
                index_non_space = i;
                break;
            }
        }
        if (index_non_space == -1) index_non_space = start; // not found whitespace then return beginning index
        return index_non_space;
    }


    /*
       Helper method for "mnemonic_cleaner". Looking for last index of the instruction.
       Helps end the loop correctly and find the last instruction.
       @param:
           str – input – the entire assembly instruction w/ or w/o comment or spaces at the end.
       @return:
           returns the start index if no space found
    */
    private static int find_last_index(String str) {
        int index_start = str.length() - 1;
        int index_comment = str.indexOf("#");
        int index_last_index = -1;

        if (index_comment != -1) { // if commented, start before it
            index_start = index_comment - 1;
        }

        for (int i = index_start; i >= 0; i--) { // start looking for next "non whitespace" backwards
            if (!Character.isWhitespace(str.charAt(i))) {
                index_last_index = i;
                break;
            }
        }
        return index_last_index;
    }

    /**
     * @param cleaned_instr: A String[] which contains the necessary parts of a MIPS instruction.
     * @return: string of the hexadecimal representing to instruction.
     */
    public static String instruction_factory(String[] cleaned_instr) {
        String instruction = cleaned_instr[0];
        String hex = "";
        Operation op_obj = switch (instruction) {
            case "add" -> new Add(cleaned_instr);
            case "addiu" -> new Addiu(cleaned_instr);
            case "and" -> new And(cleaned_instr);
            case "andi" -> new AndI(cleaned_instr);
            case "beq" -> new Beq(cleaned_instr);
           // case "bne" -> new Bne(cleaned_instr);
            case "j" -> new j(cleaned_instr);
            case "lui" -> new Lui(cleaned_instr);
            case "lw" -> new Lw(cleaned_instr);
            case "or" -> new Or(cleaned_instr);
            case "ori" -> new Ori(cleaned_instr);
            case "slt" -> new Slt(cleaned_instr);
            case "sub" -> new Sub(cleaned_instr);
            case "sw" -> new Sw(cleaned_instr);
            case "syscall" -> new Syscall(cleaned_instr);
            default -> throw new IllegalArgumentException("Entered invalid operation to instruction_factory");
        };
        return pad_hex(op_obj.get_hex(), 8);// Java will remove the left most "0" for the Hex string, so adding it back for the prof's requirements
    }

    public static String[] pseudo_instruction_factory(String[] cleaned_instr) {
        String[] hex_arr;
        PseudoOperation ps_op_obj = switch (cleaned_instr[0]) {
            case "li" -> new Li(cleaned_instr);
            case "la" -> new La(cleaned_instr);
            // case "blt" -> new Blt(cleaned_instr);
            default -> throw new IllegalArgumentException("Send invalid instruction to pseudo_instruction_factory");
        };
        hex_arr = ps_op_obj.get_hex();
        for (int i = 0; i < hex_arr.length; i++)
            hex_arr[i] = pad_hex(hex_arr[i], 8);

        return hex_arr;
    }

    /**
     * @param register: A String representing a register.
     * @return: Returns a binary string representing the register
     */
    public static String register_to_binary(String register) {
        return switch (register) {
            case "$zero", "$0", "$r0" -> "00000";
            case "$at", "$1" -> "00001";
            case "$v0", "$2" -> "00010";
            case "$v1", "$3" -> "00011";
            case "$a0", "$4" -> "00100";
            case "$a1", "$5" -> "00101";
            case "$a2", "$6" -> "00110";
            case "$a3", "$7" -> "00111";
            case "$t0", "$8" -> "01000";
            case "$t1", "$9" -> "01001";
            case "$t2", "$10" -> "01010";
            case "$t3", "$11" -> "01011";
            case "$t4", "$12" -> "01100";
            case "$t5", "$13" -> "01101";
            case "$t6", "$14" -> "01110";
            case "$t7", "$15" -> "01111";
            case "$s0", "$16" -> "10000";
            case "$s1", "$17" -> "10001";
            case "$s2", "$18" -> "10010";
            case "$s3", "$19" -> "10011";
            case "$s4", "$20" -> "10100";
            case "$s5", "$21" -> "10101";
            case "$s6", "$22" -> "10110";
            case "$s7", "$23" -> "10111";
            case "$t8", "$24" -> "11000";
            case "$t9", "$25" -> "11001";
            case "$k0", "$26" -> "11010";
            case "$k1", "$27" -> "11011";
            case "$gp", "$28" -> "11100";
            case "$sp", "$29" -> "11101";
            case "$fp", "$30" -> "11110";
            case "$ra", "$31" -> "11111";
            default -> throw new IllegalArgumentException("Entered invalid register to register_to_binary");
        };
    }//


    /**
     * @param immed  - String representing a decimal or hexadecimal number that needs to be converted into a hex String.
     * @param signed - boolean indicating whether we can use Java’s Integer.parseInt() method or not.
     * @return Examples:
     * 10         -> Binary] 1010 -> Pad] 0000 0000 0000 1010 -> Hex] 000A.
     * |-10| = 10 -> Binary] 1010 -> Pad] 0000 0000 0000 1010 -> Invert] 1111 1111 1111 0101 -> "+1"] 1111 1111 1111 0110 = signed: 2's complement representation -> Hex] FFF6.
     * 0xAA       -> Hex] 00AA
     */
    public static String immediate_to_hex(String immed, boolean signed) {
        int is_hex = immed.indexOf("x"); // looking for the "x" in "0x"
        String binary = "";
        String hex = "";
        int dec = -1;

        if (is_hex == -1) { // not hex imm
            if (immed == "")  // special case, no offset given ... lw $r1, ($r2)
                dec = 0;
            else
                dec = Integer.parseInt(immed); // String(Decimal) -> int(decimal)

            boolean invalid_signed = ((dec > 32767 || dec < -32768) && (signed));
            boolean invalid_unsigned = ((dec < 0) && !(signed));
            if (invalid_signed || invalid_unsigned)
                throw new IllegalArgumentException("Sent in incorrect range when signed or negative number and unsigned in immediate_to_hex");

            binary = dec_to_binary(dec, signed, 16); // int(decimal) -> String(Binary)

            dec = Integer.parseInt(binary, 2); // String(Binary) -> String(Decimal) ... needed to convert binary to hex.
            hex = Integer.toHexString(dec); // String(Decimal) -> String(Hex)
        } else { // hex imm
            hex = immed.substring(is_hex + 1); // ignore "0x"
            hex = hex.toLowerCase();
        }
        hex = pad_hex(hex, 4); // only if leading "0" in binary because Java removes it
        return hex;
    }


    /**
     * Used by any instruction with a "26 bit immediate" - jump
     *
     * @param instrIndex: String representing a decimal or hexadecimal number that needs to be converted into a binary String.
     * @param signed:     boolean indicating signed/unsigned.
     * @return String representing the binary string of the 26 bit instruction index value.
     */
    public static String instr_index_to_binary(String instrIndex, boolean signed) {
        int is_hex = instrIndex.indexOf("x"); // looking for the "x" in "0x"
        String binary = "";
        int dec = -1;

        if (is_hex != -1) { // sent hex
            instrIndex = instrIndex.substring(is_hex + 1); // ignore "0x"
            dec = Integer.parseInt(instrIndex, 16);
        } else {
            dec = Integer.parseInt(instrIndex); // String(Decimal) -> int(decimal)
        }

        boolean invalid_signed = ((dec > 33554431 || dec < -33554432) && (signed));
        boolean invalid_unsigned = ((dec < 0) && !(signed));
        if (invalid_signed || invalid_unsigned)
            throw new IllegalArgumentException("Sent in incorrect range when signed or negative number and unsigned in immediate_to_hex");

        binary = dec_to_binary(dec, signed, 26); // int(decimal) -> String(Binary)

        return binary;
    }

    /* Helper method for "immediate_to_hex." Returns 16 digit binary string for unsigned or sign decimals.
        @param:
           dec – int to convert to binary.
           signed - Boolean to indicate how to convert.
       @return: returns binary string of 16 digits.
     */
    private static String dec_to_binary(int dec, boolean signed, int padding) {
        String binary = "";
        int dividand = dec;
        int remainder = 0;
        boolean negative = (dec < 0);

        if (signed) {
            binary = Integer.toBinaryString(dec); //  correctly converts signed decimals to binary
        } else {
            while (dividand > 0) { // manually convert decimal to binary string bc java's .parseInt() method doesn't work for unsigned
                remainder = dividand % 2;
                dividand = (int) dividand / 2;
                binary = remainder + binary; // creating bitString
            }
        }
        return pad_binary(binary, negative, padding); // returns 16 bit: "+" needs padding, "-" needs cutting
    }


    /* Helper method for "dec_to_binary". Pads the missing binary digits of the string or removes unnecessary bits if negative.
       @param:
           binary – String in needing of padding.
                            If it's "-", then we need to cut it in half because Integer.parseInt returns 32 bits.
           negative - Boolean to indicate what the padding/cutting should be
       @return: returns binary string of "padding" number of digits.
     */
    private static String pad_binary(String binary, boolean negative, int padding) {
        String pad = "0";
        int length = binary.length();
        if (negative) { // neg - handled correctly by Integer.parseInt but in "32 bits" not "16"
            binary = binary.substring(32 - padding);
        } else { // pos - pad remaining bits
            for (int i = 0; i < padding - length; i++) {
                binary = pad + binary;
            }
        }
        return binary;
    }

    /**
     * Add's "0"s to a hexadecimal string because Integer.parseInt() removes them
     * Don't need to consider padding "f"'s because leading "1"'s won't be removed.
     *
     * @param hex
     * @param padding
     * @return
     */
    public static String pad_hex(String hex, int padding) {
        while (hex.length() < padding) { // padding
            hex = "0" + hex;
        }
        return hex;
    }
} //end: General Class




