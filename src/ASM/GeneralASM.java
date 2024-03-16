package ASM;

import java.util.LinkedHashMap;

// Tanja
public class GeneralASM {
    DataSection data_obj = new DataSection();
    TextSection text_obj = new TextSection();

    public String[] asm_to_address(String asm) {
        String[] secs = asm_parser(asm);

        String[][] cleaned_data_sec = cleaner_factory(true, secs[0]);
        String[][] cleaned_text_sec = cleaner_factory(false, secs[1]);

        LinkedHashMap<String, String[]> data_memory = data_obj.fake_data_memory(cleaned_data_sec); // {"label": ["data","addr"], ...}
        LinkedHashMap<String, String[]> text_memory = text_obj.fake_text_memory(cleaned_text_sec); // {"label"/#: ["data","addr"], ...}


        String[] data_dump_arr = data_obj.data_to_little_endian(data_memory);
        String[] text_dump_arr = text_obj.text_to_hex_instructions(data_memory, text_memory);

        String data_dump_file = data_dump_arr.toString();
        String text_dump_file = text_dump_arr.toString();

        return new String[]{data_dump_file, text_dump_file};
    }

    public String[] asm_parser(String asm) {
        return null;
    }

    public String[][] cleaner_factory(boolean isData, String sec) {
        return null;
    }
}
