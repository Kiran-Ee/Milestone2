//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import Util.General;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        General general = new General();
        System.out.println(general.instruction_to_hex(args[0]));
        System.out.println(); // newline for requirements
    }
}