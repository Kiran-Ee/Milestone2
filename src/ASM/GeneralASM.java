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

        String data_dump_file = Arrays.toString(data_dump_arr);
        String text_dump_file = Arrays.toString(text_dump_arr);

        return new String[]{data_dump_file, text_dump_file};
    }

    public String[] asm_parser(String asm) {
        if(asm == null || asm.isEmpty()){
            return new String[]{"", ""};
        }
        String[] lines = asm.split("\\n");

        //store data and text
        StringBuilder dataS = new StringBuilder();
        StringBuilder textS = new StringBuilder();

        boolean inData = false;

        for(String l : lines){
            l = l.trim();

            //skip empty and comments
            if(l.isEmpty() || l.startsWith("#")){
                continue;
            }
            //ck for data
            if(l.startsWith(".data")){
                inData = true;
                continue; //skip data l
            } else if(l.startsWith(".text")){
                inData = false;
                continue;
            }

            //append d line to appropriate section
            if(inData){
                dataS.append(l).append("\n");
            } else {
                textS.append(l).append("\n");
            }
        }
        //combine data and txt into one str arr
        String[] dataAndText = new String[2];
        dataAndText[0] = dataS.toString();
        dataAndText[1] = textS.toString();

        return dataAndText;
    }

    public String[][] cleaner_factory(boolean isData, String sec) {
        //1
//        String[] lines = sec.split("\\n");
//
//        List<String[]> dataI = new ArrayList<>();
//        List<String[]> textI = new ArrayList<>();
//
//        boolean isDataSection = false;
//        for(String line: lines){
//            if(line.trim().startsWith(".data")){
//                isDataSection = true;
//                continue;
//            }else if(line.startsWith(".text")){
//                isDataSection = false;
//                continue;
//            }else if(!line.trim().isEmpty()){
//                String[] cleanedLine;
//                if(isDataSection){
//                    cleanedLine = data_obj.declaration_line_cleaner(line);
//                    if(cleanedLine.length > 0){
//                        dataI.add(cleanedLine);
//                    }
//                } else {
//                    cleanedLine = text_obj.text_line_cleaner(line);
//                    if(cleanedLine.length > 0){
//                        textI.add(cleanedLine);
//                    }
//                }
//            }
//        }
//        if(isData){
//            return dataI.toArray(new String[0][]);
//        } else {
//            return textI.toArray(new String[0][]);
//        }
//    }
        //2
//        String[] lines = sec.split("\\n");
////        String[] lines = sec.split(":");
//
//        List<String[]> cleanedInstr = new ArrayList<>();
//
//        for(String line : lines){
//            if(!line.trim().isEmpty() && !line.trim().startsWith(".data")){
//                String[] cleanedLine;
//                if(isData){
//                    cleanedLine = data_obj.declaration_line_cleaner(line);
//                } else {
//                    cleanedLine = text_obj.text_line_cleaner(line);
//                }
//                if(cleanedLine.length > 0){
//                    cleanedInstr.add(cleanedLine);
//                }
//            }
//
//        }
//        return cleanedInstr.toArray(new String[0][]);
//    }
        //3
        //.data or .text
//        String typeSection = isData ? ".data" : ".text";
//
//        String[] lines = sec.split("\\n");
//
//        List<String[]> cleanedInstr = new ArrayList<>();
//
//        for(String line : lines){
//            if(!line.trim().isEmpty() && line.trim().startsWith(typeSection)){
//                String[] cleanedLine;
//                if(typeSection.equals(".data")){
//                    cleanedLine = data_obj.declaration_line_cleaner(line);
//                } else if(typeSection.equals(".text")){
//                    cleanedLine = text_obj.text_line_cleaner(line);
//                } else {
//                    continue; //optional to possibly handle the unknown section type
//                }
//                cleanedInstr.add(cleanedLine);
//            }
//        }
//        String[][] converted = new String[cleanedInstr.size()][];
//        for(int i = 0; i < cleanedInstr.size(); i++){
//            converted[i] = cleanedInstr.get(i);
//        }
//        return converted;
//    }
        //4
        String[] lines = sec.split("\\n");

        List<String[]> cleanedInstr = new ArrayList<>();

        boolean inDataS = false;
        boolean inTextS = false;

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith(".data")) {
                inDataS = true;
                inTextS = false;
                continue;
            } else if (line.startsWith(".text")) {
                inDataS = false;
                inTextS = true;
                continue;
            }

            if ((isData && inDataS) || (!isData && inTextS)) {
                String[] cleanedLine = isData ? data_obj.declaration_line_cleaner(line) : text_obj.text_line_cleaner(line);
                if (cleanedLine.length > 0) {
                    cleanedInstr.add(cleanedLine);
                }
            }
        }

        return cleanedInstr.toArray(new String[0][]);
    }
}
