package ASM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class GeneralASM {
    DataSection data_obj = new DataSection();
    TextSection text_obj = new TextSection();

    public String[] asm_to_address(String asm) {
        String[] secs = asm_parser(asm);

        String[][] cleaned_data_sec = cleaner_factory(true, secs[0]);
        String[][] cleaned_text_sec = cleaner_factory(false, secs[1]);

        LinkedHashMap<String, String[]> data_memory = data_obj.fake_data_memory(cleaned_data_sec); // {"label": ["data","dtaAddr"], ...}
        LinkedHashMap<String, String[][]> text_memory = text_obj.fake_text_memory(cleaned_text_sec); // {"label"/#: [["intsr"],["txtAddr"]], ...}

        String[] data_dump_arr = data_obj.data_to_little_endian(data_memory);
        String[] text_dump_arr = text_obj.text_to_hex_instructions(data_memory, text_memory);

        // creating strings with line breaks ...
        String data_dump_file = "";
        for (int i = 0; i<data_dump_arr.length; i++)
            data_dump_file += (i+1 != data_dump_arr.length) ? data_dump_arr[i] + "\n" : data_dump_arr[i] ;

        String text_dump_file = "";
        for (int i = 0; i<text_dump_arr.length; i++)
            text_dump_file += (i+1 != text_dump_arr.length) ? text_dump_arr[i] + "\n" : text_dump_arr[i] ;

        return new String[]{data_dump_file, text_dump_file};
    }

    public String[] asm_parser(String asm) {
       int data_label = asm.indexOf(".data")+ 5;
       int text_label = asm.indexOf(".text");
       String data_sec = asm.substring(data_label + 1, text_label);
       String text_sec = asm.substring(text_label + 5);
       return new String[]{data_sec, text_sec};
    }

    public String[][] cleaner_factory(boolean isData, String sec) {
        String[] lines = sec.split("\\n");

        List<String[]> cleanedInstr = new ArrayList<>();
        for (String line : lines) {
            line = line.trim();
            String[] cleanedLine = isData ? data_obj.declaration_line_cleaner(line) : text_obj.text_line_cleaner(line);
            if (cleanedLine.length > 0) {
                cleanedInstr.add(cleanedLine);
            }
        }
        return cleanedInstr.toArray(new String[0][]);
    }
}
