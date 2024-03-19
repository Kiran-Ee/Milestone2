//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import Util.General;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        General general = new General();
//        System.out.println(general.instruction_to_hex(args[0]));
//        System.out.println(); // newline for requirements
        String str1 = "    ";  // Only contains spaces
        String str2 = "\t\t\t"; // Only contains tabs
        String str3 = "  \t  "; // Contains both spaces and tabs
        String str4 = "Hello";  // Contains non-whitespace characters

        System.out.println(isJustWhiteSpace(str1)); // true
        System.out.println(isJustWhiteSpace(str2)); // true
        System.out.println(isJustWhiteSpace(str3)); // true
        System.out.println(isJustWhiteSpace(str4)); // false
    }

    public static boolean isJustWhiteSpace(String str) {
        // Trim removes leading and trailing whitespace
        // If the resulting string is empty, then it contains only whitespace
        return str.trim().isEmpty();
        }
}