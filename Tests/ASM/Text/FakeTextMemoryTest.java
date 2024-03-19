package ASM.Text;

import ASM.DataSection;
import ASM.TextSection;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

class FakeTextMemoryTest {
    TextSection text_obj = new TextSection();

    String[] text_ln1 = new String[]{"add", "$1", "$1","$1"};
    String[] text_ln2 = new String[]{"sub", "$1", "$1", "$1"};
    String[] text_ln3 = new String[]{"ITS_ODD"};
    String[] text_ln4 = new String[]{"j", "ITS_ODD"};
    String[] text_ln5 = new String[]{"beq", "$a0", "$zero", "ITS_ODD"};
    String[] text_ln6 = new String[]{"add", "$t2", "$v1", "$a0"};
    String[][] clean_text_sec_size6 = new String[][]{text_ln1, text_ln2, text_ln3,text_ln4 ,text_ln5 ,text_ln6 };


    @Test
    void set_clean_text_sec_size6() {
        LinkedHashMap<String, String[][]> hm = new LinkedHashMap<>();
        hm.put("1", new String[][]{text_ln2, new String[]{"00400000"}});
        hm.put("0", new String[][]{text_ln1, new String[]{"00400004"}});
        hm.put("ITS_ODD", new String[][]{text_ln3, new String[]{"00400008"}});
        hm.put("3", new String[][]{text_ln4, new String[]{"0040000c"}});
        hm.put("4", new String[][]{text_ln5, new String[]{"00400010"}});
        hm.put("5", new String[][]{text_ln6, new String[]{"00400014"}});

        assertEquals(hm, text_obj.fake_text_memory(clean_text_sec_size6));
    }
}