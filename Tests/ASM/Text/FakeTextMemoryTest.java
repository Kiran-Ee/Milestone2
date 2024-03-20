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

    String[] text_ln1 = new String[]{"add", "$1", "$1","$1"};
    String[] text_ln2 = new String[]{"sub", "$1", "$1", "$1"};
    String[] text_ln3 = new String[]{"ITS_ODD:"};
    String[] text_ln4 = new String[]{"j", "ITS_ODD"};
    String[] text_ln5 = new String[]{"beq", "$a0", "$zero", "ITS_ODD"};
    String[] text_ln6 = new String[]{"add", "$t2", "$v1", "$a0"};
    String[][] clean_text_sec_size6 = new String[][]{text_ln1, text_ln2, text_ln3,text_ln4 ,text_ln5 ,text_ln6 };

    @BeforeEach
    void setUp() {
        hm.put("0", new String[][]{text_ln1, new String[]{"00400000"}});
        hm.put("1", new String[][]{text_ln2, new String[]{"00400004"}});
        hm.put("ITS_ODD:", new String[][]{new String[]{"2"}, new String[]{"0040000c"}});
        hm.put("3", new String[][]{text_ln4, new String[]{"0040000c"}});
        hm.put("4", new String[][]{text_ln5, new String[]{"00400010"}});
        hm.put("5", new String[][]{text_ln6, new String[]{"00400014"}});
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
}