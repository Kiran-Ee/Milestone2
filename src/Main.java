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

        // Creating an array of objects to store ints and strings
        Object[] mixedArray = new Object[4];

        // Adding elements to the array
        mixedArray[0] = 42;          // Integer
        mixedArray[1] = "Hello";     // String
        mixedArray[2] = 3.14;        // Double (also an Object)
        mixedArray[3] = true;        // Boolean (also an Object)

        // Accessing elements in the array
        int intValue = (int) mixedArray[0];
        String stringValue = (String) mixedArray[1];
        double doubleValue = (double) mixedArray[2];
        boolean booleanValue = (boolean) mixedArray[3];

        // Printing the values
        System.out.println("Integer Value: " + intValue);
        System.out.println("String Value: " + stringValue);
        System.out.println("Double Value: " + doubleValue);
        System.out.println("Boolean Value: " + booleanValue);
    }
}