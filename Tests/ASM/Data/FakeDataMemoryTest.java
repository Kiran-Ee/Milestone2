package ASM.Data;

import ASM.DataSection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// will probs add more tests later but this is a good start ...
// also, we might change the data structure entirely.
class FakeDataMemoryTest {
    DataSection data_obj = new DataSection();
    String[] dataLn1 = new String[]{"input_request", "Enter your integer: "};
    String[] dataLn2 = new String[]{"even_output", "Your integer is EVEN!"};
    String[] dataLn3 = new String[]{"odd_output", "Your integer is ODD!"};
    String[][] clean_data_sec_size3 = new String[][]{dataLn1, dataLn2, dataLn3};

    String[] dataLn4 = new String[]{"kirans_label1Char", "1234"};
    String[] dataLn5 = new String[]{"kirans_label2Char", "56"};
    String[] dataLn6 = new String[]{"kirans_label3Char", "7"};
    String[] dataLn7 = new String[]{"kirans_label4Char", ""};
    String[][] clean_data_sec_size4 = new String[][]{dataLn4, dataLn5, dataLn6, dataLn7};

    @Test
    void set_clean_data_sec_size3() {
        LinkedHashMap<String, String[]> hm = new LinkedHashMap<>();
        hm.put("input_request", new String[]{"Enter your integer: ", "10010000"});
        hm.put("even_output", new String[]{"Your integer is EVEN!", "10010015"});
        hm.put("odd_output", new String[]{"Your integer is ODD!", "1001002b"});

        LinkedHashMap<String, String[]> actual_hm = data_obj.fake_data_memory(clean_data_sec_size3);

        Set<Map.Entry<String, String[]>> hm_entrySet = hm.entrySet();
        assertEquals(hm.keySet(), actual_hm.keySet()); // checking keys

        for(Map.Entry<String, String[]> e : hm_entrySet) {
            String key = e.getKey();
            String[] array1 = e.getValue();
            String[] array2 = actual_hm.get(key);
            assertTrue(Arrays.equals(array1, array2), "The arrays for key " + key + " are not equal.");  // checking vals
        }
    }

    @Test
    void set_clean_data_sec_size4() {
        LinkedHashMap<String, String[]> hm = new LinkedHashMap<>();
        hm.put("kirans_label1Char", new String[]{"1234", "10010000"});
        hm.put("kirans_label2Char", new String[]{"56", "10010005"});
        hm.put("kirans_label3Char", new String[]{"7", "10010008"});
        hm.put("kirans_label4Char", new String[]{"", "1001000a"});

        LinkedHashMap<String, String[]> actual_hm = data_obj.fake_data_memory(clean_data_sec_size4);

        Set<Map.Entry<String, String[]>> hm_entrySet = hm.entrySet();
        assertEquals(hm.keySet(), actual_hm.keySet()); // checking keys

        for(Map.Entry<String, String[]> e : hm_entrySet) {
            String key = e.getKey();
            String[] array1 = e.getValue();
            String[] array2 = actual_hm.get(key);
            assertTrue(Arrays.equals(array1, array2), "The arrays for key " + key + " are not equal.");  // checking vals
        }
    }
}