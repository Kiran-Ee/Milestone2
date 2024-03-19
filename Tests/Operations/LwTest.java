package Operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LwTest {
    String[] instr = {"lw", "$t0", "10", "$s1"}; //Form: lw, rt, offset(base)
    String[] instr1 = {"lw", "$0", "0", "$s0"};

    String[] instr_max = {"lw", "$0", "32767", "$a0"};
    String[] instr_invalid = {"lw", "$10", "32768", "$s0"}; // shouldn't work bc of signed range [-32768, 32767]

    String[] instr_hex = {"lw", "$5", "0x9999", "$a0"};
    String[] instr_negMin = {"lw", "$4", "-32768", "$a0"};
    @Test
    public void good_variable_setting1() { //?????
        Lw lw = new Lw(instr);
        String[] result = lw.getInstruction();

        //assertEquals("lw", result[0]);
        assertEquals("$t0", result[2]); //destination
        assertEquals("10", result[3]); //offset
        assertEquals("$s1", result[1]); //base
    }

    @Test
    public void good_variable_setting2() {
        Lw lw = new Lw(instr1);
        String[] result = lw.getInstruction();

        assertEquals("$0", result[2]); //destination
        assertEquals("0", result[3]); //offset
        assertEquals("$s0", result[1]); //base
    }

    @Test
    public void good_variable_setting3() {
        Lw lw = new Lw(instr_hex);
        String[] result = lw.getInstruction();

        assertEquals("$5", result[2]); //destination
        assertEquals("0x9999", result[3]); //offset
        assertEquals("$a0", result[1]); //base
    }

    @Test
    public void good_variable_setting4() {
        Lw lw = new Lw(instr_negMin);
        String[] result = lw.getInstruction();

        assertEquals("$4", result[2]); //destination
        assertEquals("-32768", result[3]); //offset
        assertEquals("$a0", result[1]); //base
    }



    @Test
    public void return_correct_hex_instr_instr() {
        Lw lw = new Lw(instr);
        assertEquals("8e28000a", lw.get_hex());
    }

    @Test
    public void return_correct_hex_instr_instr1() {
        Lw lw = new Lw(instr1);
        assertEquals("8e000000", lw.get_hex());
    }

    @Test
    public void return_correct_hex_instr_instrMax() {
        Lw lw = new Lw(instr_max);
        assertEquals("8c807fff", lw.get_hex());
    }

    @Test
    public void return_correct_hex_instr_instrHex() {
        Lw lw = new Lw(instr_hex);
        assertEquals("8c859999", lw.get_hex());
    }

    @Test
    public void return_correct_hex_instr_instrNegMin() {
        Lw lw = new Lw(instr_negMin);
        assertEquals("8c848000", lw.get_hex());
    }

    @Test
    public void return_correct_hex_instr_instrInvalid() {
        Lw lw = new Lw(instr_invalid);
        assertThrows(IllegalArgumentException.class, () -> {
            lw.get_hex();
        });
    }
}
