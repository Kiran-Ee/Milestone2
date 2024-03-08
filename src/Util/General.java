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
    String[] mnemonic_cleaner(String mnemonic) {
        ArrayList<String> cleaned_instr_AL = new ArrayList<>(); // for dynamically adding
        int index_last_index = find_last_index(mnemonic); // last valid index of assembly instruction
        int index_start_char = find_non_space(0, mnemonic); // pointer for beginning of current instruction
        int index_end_char = -1; // pointer for end of current instruction
        int index_comma = -1;
        int index_dollar_sign = mnemonic.indexOf("$"); // determines if "j" or not: I think all instructions except "j" have "$"
        int index_paren = -1;

        while (index_start_char <= index_last_index) // stop when reached last index
        {
            index_dollar_sign = mnemonic.indexOf("$", index_start_char + 1);
            index_comma = mnemonic.indexOf(",", index_start_char + 1);
            index_end_char = mnemonic.indexOf(" ", index_start_char + 1); // initialing the "end" pointer as the next space unless overshot the commas or $
            index_paren = mnemonic.indexOf(")", index_start_char + 1);

            if (index_end_char > index_paren && index_paren != -1) { // overshot parenthesis
                index_end_char = index_paren+1;
            } else if (index_end_char > index_comma && index_comma != -1) { // overshot comma
                index_end_char = index_comma;
            } else if (index_end_char > index_dollar_sign && index_dollar_sign != -1) { // overshot $
                index_end_char = index_dollar_sign;
            } else if (index_end_char == -1 || index_end_char > index_last_index) { // if no remaining space, comma, or $ after starting index then we're on the last instruction
                index_end_char = index_last_index + 1;
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
    private int find_non_space(int start, String str) {
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
    private int find_last_index(String str) {
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
    String instruction_factory(String[] cleaned_instr) { // package private "Util"
        String instruction = cleaned_instr[0];
        String hex = "";
        Operation op_obj = null;
        switch (instruction) {
            case "add":
                op_obj = new Add(cleaned_instr);
                break;
            case "addiu":
                op_obj = new Addiu(cleaned_instr);
                break;
            case "and":
                op_obj = new And(cleaned_instr);
                break;
            case "andi":
                op_obj = new AndI(cleaned_instr);
                break;
            case "beq":
                op_obj = new Beq(cleaned_instr);
                break;
            case "bne":
                op_obj = new Bne(cleaned_instr);
                break;
            case "j":
                op_obj = new j(cleaned_instr);
                break;
            case "lui":
                op_obj = new Lui(cleaned_instr);
                break;
            case "lw":
                op_obj = new Lw(cleaned_instr);
                break;
            case "or":
                op_obj = new Or(cleaned_instr);
                break;
            case "ori":
                op_obj = new Ori(cleaned_instr);
                break;
            case "slt":
                op_obj = new Slt(cleaned_instr);
                break;
            case "sub":
                op_obj = new Sub(cleaned_instr);
                break;
            case "sw":
                op_obj = new Sw(cleaned_instr);
                break;
            case "syscall":
                op_obj = new Syscall(cleaned_instr);
                break;
            default:
                throw new IllegalArgumentException("Entered invalid operation to instruction_factory");
        }
        hex = op_obj.get_hex();

        // Java will remove the left most "0" for the Hex string, so adding it back for the prof's requirements
        while (hex.length() < 8) { // padding
            hex = "0" + hex;
        }

        return hex;
    }


    /**
     * @param register: A String representing a register.
     * @return: Returns a binary string representing the register
     */
    public static String register_to_binary(String register) {
        String binary = "";
        if (register.equals("$zero") || register.equals("$0") || register.equals("$r0"))
            binary = "00000";
        else if (register.equals("$at") || register.equals("$1"))
            binary = "00001";
        else if (register.equals("$v0") || register.equals("$2"))
            binary = "00010";
        else if (register.equals("$v1") || register.equals("$3"))
            binary = "00011";
        else if (register.equals("$a0") || register.equals("$4"))
            binary = "00100";
        else if (register.equals("$a1") || register.equals("$5"))
            binary = "00101";
        else if (register.equals("$a2") || register.equals("$6"))
            binary = "00110";
        else if (register.equals("$a3") || register.equals("$7"))
            binary = "00111";
        else if (register.equals("$t0") || register.equals("$8"))
            binary = "01000";
        else if (register.equals("$t1") || register.equals("$9"))
            binary = "01001";
        else if (register.equals("$t2") || register.equals("$10"))
            binary = "01010";
        else if (register.equals("$t3") || register.equals("$11"))
            binary = "01011";
        else if (register.equals("$t4") || register.equals("$12"))
            binary = "01100";
        else if (register.equals("$t5") || register.equals("$13"))
            binary = "01101";
        else if (register.equals("$t6") || register.equals("$14"))
            binary = "01110";
        else if (register.equals("$t7") || register.equals("$15"))
            binary = "01111";
        else if (register.equals("$s0") || register.equals("$16"))
            binary = "10000";
        else if (register.equals("$s1") || register.equals("$17"))
            binary = "10001";
        else if (register.equals("$s2") || register.equals("$18"))
            binary = "10010";
        else if (register.equals("$s3") || register.equals("$19"))
            binary = "10011";
        else if (register.equals("$s4") || register.equals("$20"))
            binary = "10100";
        else if (register.equals("$s5") || register.equals("$21"))
            binary = "10101";
        else if (register.equals("$s6") || register.equals("$22"))
            binary = "10110";
        else if (register.equals("$s7") || register.equals("$23"))
            binary = "10111";
        else if (register.equals("$t8") || register.equals("$24"))
            binary = "11000";
        else if (register.equals("$t9") || register.equals("$25"))
            binary = "11001";
        else if (register.equals("$k0") || register.equals("$26"))
            binary = "11010";
        else if (register.equals("$k1") || register.equals("$27"))
            binary = "11011";
        else if (register.equals("$gp") || register.equals("$28"))
            binary = "11100";
        else if (register.equals("$sp") || register.equals("$29"))
            binary = "11101";
        else if (register.equals("$fp") || register.equals("$30"))
            binary = "11110";
        else if (register.equals("$ra") || register.equals("$31"))
            binary = "11111";
        else
            throw new IllegalArgumentException("Entered invalid register to register_to_binary");

        return binary;
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

        if (is_hex == -1)  // not hex imm
        {
            if (immed == "") { // special case, no offset given ... lw $r1, ($r2)
                dec = 0;
            } else {
                dec = Integer.parseInt(immed); // String(Decimal) -> int(decimal)
            }

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
        }
        // ADD if else(binary.length>16){PAD w/ 26 bits!}
        else { // pos - pad remaining bits
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
            hex = "0" + hex; //IF IT'S NEGATIVE (signed) THEN THIS MIGHT NEED TO BE F?
        }
        return hex;
    }


    /**
     * Used by any operation with an offset and register format. Ex: 50($t)
     *
     * @param address: String representing a base & offset.
     * @return String array: [0]:base [1]:offset
     */
    public static String[] offset_parser(String address) {
        if (!(address instanceof String))
            throw new IllegalArgumentException("Can't send in not String to offset_parser");

        String offset = address.substring(0, address.indexOf('('));
        String base = address.substring(address.indexOf('(') + 1, address.indexOf(')'));

        String[] s = new String[]{base, offset};
        return s; // returns [base, offset]
    }
} //end: General Class




