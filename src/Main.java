//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import ASM.GeneralASM;
import Util.General;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GeneralASM g = new GeneralASM();
        String[] dump_files = g.asm_to_address(args[0]);
        System.out.println(dump_files[0]); // IDK IF THIS IS HOW SHOULD BE SUBMITTED?
        System.out.println(dump_files[1]); // IDK IF THIS IS HOW SHOULD BE SUBMITTED?

//        General general = new General();
//        System.out.println(general.instruction_to_hex(args[0]));
//        System.out.println(); // newline for requirements
    }
}