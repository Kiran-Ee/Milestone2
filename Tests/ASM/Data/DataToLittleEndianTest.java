package ASM.Data;

import ASM.DataSection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class DataToLittleEndianTest {
    DataSection data_sec = new DataSection();
    private LinkedHashMap<String, String[]> hm1;
    private LinkedHashMap<String, String[]> hm2;

    String[] exp_return_hm1 = new String[]{ // from MARS data dump
            "65746e45",
            "6f792072",
            "69207275",
            "6765746e",
            "203a7265",
            "756f5900",
            "6e692072",
            "65676574",
            "73692072",
            "45564520",
            "5900214e",
            "2072756f",
            "65746e69",
            "20726567",
            "4f207369",
            "00214444"};

    String[] exp_return_hm2 = new String[]{
            "34333231",
            "00363500",
            "00000037"};

    @BeforeEach
    void setUp() { // allows each test method to use this data ... wasn't working w/o this
        hm1 = new LinkedHashMap<>();
        hm1.put("input_request", new String[]{"Enter your integer: ", "10010000"}); // {label: ["DataValue", "Memory Address"]}
        hm1.put("even_output", new String[]{"Your integer is EVEN!", "10010015"});
        hm1.put("odd_output", new String[]{"Your integer is ODD!", "1001002b"});

        hm2 = new LinkedHashMap<>();
        hm2.put("kirans_label1Char", new String[]{"1234", "10010000"});
        hm2.put("kirans_label2Char", new String[]{"56", "10010005"});
        hm2.put("kirans_label3Char", new String[]{"7", "10010008"});
        hm2.put("kirans_label4Char", new String[]{"", "1001000a"});
    }

    @Test
    void setHm1() {
        String[] testing_arr = data_sec.data_to_little_endian(hm1);
        assertArrayEquals(exp_return_hm1, testing_arr);
    }

    @Test
    void setHm2() {
        String[] testing_arr = data_sec.data_to_little_endian(hm2);
        assertArrayEquals(exp_return_hm2, testing_arr);
    }
}