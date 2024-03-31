//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import ASM.GeneralASM;
import Util.General;

import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        String asm_path = args[0];
        String asm_file = "";
        try {
            FileReader fileReader = new FileReader(new File(asm_path));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(counter + ": \n" + asm_file + "\n\n");
                asm_file = asm_file + line + "\n";
                counter++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GeneralASM gen = new GeneralASM();

        System.out.println("ASM FILE: \n" + asm_file + "\n\n");
        String[] dump_files = gen.asm_to_address(asm_file);
        System.out.println("Data Dump String:" + dump_files[0] + "\n\n"); // shifted over 2 left
        System.out.println("Text Dump String:" + dump_files[1] + "\n\n"); // messing up la instruction

        String baseFileName = asm_path.substring(0, asm_path.lastIndexOf('.'));
        String textOutputFilePath = baseFileName + ".text";
        String dataOutputFilePath = baseFileName + ".data";

        String textSectionData = dump_files[1];
        String dataSectionData = dump_files[0];

        try {
            BufferedWriter textWriter = new BufferedWriter(new FileWriter(textOutputFilePath));
            textWriter.write(textSectionData);
            textWriter.close();

            BufferedWriter dataWriter = new BufferedWriter(new FileWriter(dataOutputFilePath));
            dataWriter.write(dataSectionData);
            dataWriter.close();

            System.out.println("Files created successfully.");
        } catch (IOException e) {
            System.err.println("Error creating files: " + e.getMessage());
        }
    }
}