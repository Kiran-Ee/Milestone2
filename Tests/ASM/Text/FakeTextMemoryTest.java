package ASM.Text;

import ASM.DataSection;
import ASM.TextSection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FakeTextMemoryTest {
    TextSection text_obj = new TextSection();
    LinkedHashMap<String, String[][]> hm = new LinkedHashMap<>();
    LinkedHashMap<String, String[][]> hm1 = new LinkedHashMap<>();


    String[] text_ln1 = new String[]{"add", "$1", "$1","$1"};
    String[] text_ln2 = new String[]{"sub", "$1", "$1", "$1"};
    String[] text_ln3 = new String[]{"ITS_ODD:"};
    String[] text_ln4 = new String[]{"j", "ITS_ODD"};
    String[] text_ln5 = new String[]{"beq", "$a0", "$zero", "ITS_ODD"};
    String[] text_ln6 = new String[]{"add", "$t2", "$v1", "$a0"};
    String[][] clean_text_sec_size6 = new String[][]{text_ln1, text_ln2, text_ln3, text_ln4, text_ln5, text_ln6};

    String[] text2_ln1 = new String[]{"add", "$s1", "$s2", "$s2"};
    String[] text2_ln2 = new String[]{"addiu", "$s1", "$s2", "10"};
    String[] text2_ln3 = new String[]{"addiu", "$s1", "$2", "0x10"};
    String[] text2_ln4 = new String[]{"j", "RandomLabel"};
    String[] text2_ln5 = new String[]{"li", "$s1", "42"};
    String[] text2_ln6 = new String[]{"la", "$s1", "Label1"};
    String[] text2_ln7 = new String[]{"blt", "$s1", "$s2", "RandomLabel"};
    String[] text2_ln8 = new String[]{"bne", "$s1", "$s2", "RandomLabel"};
    String[] text2_ln9 = new String[]{"beq", "$s1", "$s2", "RandomLabel"};
    String[] text2_ln10 = new String[]{"RandomLabel:"};
    String[] text2_ln11 = new String[]{"add", "$s1", "$s2", "$3"};
    String[][] clean_text_sec_size11 = new String[][]{text2_ln1, text2_ln2, text2_ln3, text2_ln4, text2_ln5, text2_ln6, text2_ln7, text2_ln8, text2_ln9,text2_ln10,  text2_ln11};

    @BeforeEach
    void setUp() {
        hm.put("0", new String[][]{text_ln1, new String[]{"00400000"}});
        hm.put("1", new String[][]{text_ln2, new String[]{"00400004"}});
        hm.put("ITS_ODD:", new String[][]{new String[]{"2"}, new String[]{"00400008"}});
        hm.put("3", new String[][]{text_ln4, new String[]{"00400008"}});
        hm.put("4", new String[][]{text_ln5, new String[]{"0040000c"}});
        hm.put("5", new String[][]{text_ln6, new String[]{"00400010"}});

        hm1.put("0", new String[][]{text2_ln1, new String[]{"00400000"}});
        hm1.put("1", new String[][]{text2_ln2, new String[]{"00400004"}});
        hm1.put("2", new String[][]{text2_ln3, new String[]{"00400008"}});
        hm1.put("3", new String[][]{text2_ln4, new String[]{"0040000c"}});
        hm1.put("4", new String[][]{text2_ln5, new String[]{"00400010"}});
        hm1.put("5", new String[][]{text2_ln6, new String[]{"00400014"}});
        hm1.put("6", new String[][]{text2_ln7, new String[]{"0040001c"}});
        hm1.put("7", new String[][]{text2_ln8, new String[]{"00400024"}});
        hm1.put("8", new String[][]{text2_ln9, new String[]{"00400028"}});
        hm1.put("RandomLabel:", new String[][]{new String[]{"9"}, new String[]{"0040002c"}});
        hm1.put("10", new String[][]{text2_ln11, new String[]{"0040002c"}});
    }

    @Test
    void set_clean_text_sec_size6() {
        LinkedHashMap<String, String[][]> actual_hm = text_obj.fake_text_memory(clean_text_sec_size6);

        Set<Map.Entry<String, String[][]>> hm_entrySet = hm.entrySet();
        assertEquals(hm.keySet(), actual_hm.keySet()); // checking keys

        for(Map.Entry<String, String[][]> e : hm_entrySet) {
            String key = e.getKey();
            String[][] array1 = e.getValue();
            String[][] array2 = actual_hm.get(key);
            assertArrayEquals(array1, array2, "The arrays for key " + key + " are not equal.");  // checking vals
        }
    }

    @Test
    void set_clean_text_sec_size11() {
        LinkedHashMap<String, String[][]> actual_hm = text_obj.fake_text_memory(clean_text_sec_size11);

        Set<Map.Entry<String, String[][]>> hm_entrySet = hm1.entrySet();
        assertEquals(hm1.keySet(), actual_hm.keySet()); // checking keys

        for(Map.Entry<String, String[][]> e : hm_entrySet) {
            String key = e.getKey();
            String[][] array1 = e.getValue();
            String[][] array2 = actual_hm.get(key);
            assertArrayEquals(array1, array2, "The arrays for key " + key + " are not equal.");  // checking vals
        }
    }
}