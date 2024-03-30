package Operations;

import static Util.General.immediate_to_hex;

// Josiah
public class Li implements PseudoOperation {
    private String[] originalInstruction;
    private Lui luihalf;
    private Ori orihalf;
    private Addiu add;
    public String flag = ""; // this will help with fake_text_memory to find out how much each instruction takes up in memory

    /*
        "+":
            1] Addiu: Within 16 - SIGNED bit range: [-32768, 32767] [8000, 7FFF] -> [0, 32767] [0, 7FFF] bc only positive
            2] Ori: Within 16 - UNSIGNED bit range: [-65536, 65535] [FFFF0000, 0000FFFF] ->[0, 65535] [0, 0000FFFF] bc only positive
            3] Lui + Ori: Greater than 16 bit Unsiged range
        "-":
            1] Addiu: Within 16 - SIGNED bit range: [-32768, -1] & [8000, FFFF] bc only negative
            2] Lui + Ori: Greater than 16 bit Unsiged range
     */
    public Li(String[] cleaned_instructions) { // why do we need to change "cleaned_instructions" immediate? y not leave it alone?
        String imm = cleaned_instructions[2];
        if ((imm.contains("-"))) { // "-"
            if (Integer.parseInt(imm) > -32769) { // "-" in 16 bit range
                imm = immediate_to_hex(imm, true);
                flag = "Addiu";
                add = new Addiu(new String[]{"addiu", cleaned_instructions[1], "$0", "0x" + imm});
            } else { // "-" bigger than 16 bit range
                int immediate = Integer.parseInt(imm);
                // Extract the upper and lower 16 bits
                int upper = (immediate & 0xFFFF0000) >>> 16;
                int lower = immediate & 0xFFFF;
                flag = "Lui + Ori";
                luihalf = new Lui(new String[]{"lui", "$at", "0x" + Integer.toHexString(upper)});
                orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$at", "0x" + Integer.toHexString(lower)});
            }
        } else { // "+"
            if (imm.contains("0x")) {
                imm = imm.substring(2); // Remove 0x for parsing
            } else {
                imm = immediate_to_hex(imm, false);
            }
            int intImm = Integer.parseInt(imm, 16);
            if (intImm <= 0x7FFF) { // "+" in 16-bit SIGNED range
                flag = "Addiu";
                add = new Addiu(new String[]{"addiu", cleaned_instructions[1], "$0", "0x" + imm});

            } else if (intImm <= 0x0000FFFF) { // "+" in 16 bit UNSIGNED range
                flag = "Ori";
                orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$0", "0x" + imm});

            } else { // "+" outside 16 bit UNSIGNED range
                // Extract the upper and lower 16 bits
                int upper = (intImm & 0xFFFF0000) >>> 16;
                int lower = intImm & 0xFFFF;
                flag = "Lui + Ori";
                luihalf = new Lui(new String[]{"lui", "$at", "0x" + Integer.toHexString(upper)});
                orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$at", "0x" + Integer.toHexString(lower)});
            }
        }
        originalInstruction = cleaned_instructions;
    }

//    public Li(String[] cleaned_instructions) {
//        if (!(cleaned_instructions[2].startsWith("0x"))) { // no "hex immediate"
//            if (cleaned_instructions[2].startsWith("-")) { // "-"
//                if (Integer.parseInt(cleaned_instructions[2]) > -32769) { // "-" in 16 bit range
//                    cleaned_instructions[2] = immediate_to_hex(cleaned_instructions[2], true);
//                    flag = "Addiu";
//                    add = new Addiu(new String[]{"addiu", cleaned_instructions[1], "$0", "0x" + cleaned_instructions[2]});
//                } else { // "-" bigger than 16 bit range
//                    int immediate = Integer.parseInt(cleaned_instructions[2]); // Example signed immediate value from li instruction
//                    // Extract the upper and lower 16 bits
//                    int upper = (immediate & 0xFFFF0000) >>> 16;
//                    int lower = immediate & 0xFFFF;
//                    flag = "Lui + Ori";
//                    luihalf = new Lui(new String[]{"lui", "$at", "0x" + Integer.toHexString(upper)});
//                    orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$at", "0x" + Integer.toHexString(lower)});
//                }
//            } else { // "+" ... handle as unsigned.
//                cleaned_instructions[2] = immediate_to_hex(cleaned_instructions[2], false);
//                if (Integer.parseInt(cleaned_instructions[2], 16) <= 0x7FFF) { // "+" in 16 bit range
//                    flag = "Addiu";
//                    add = new Addiu(new String[]{"addiu", cleaned_instructions[1], "$0", "0x" + cleaned_instructions[2]});
//                } else { // "+" bigger than 16 bit range
//                    flag = "Ori";
//                    orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$0", "0x" + cleaned_instructions[2]});
//                }
//                // pretty sure a "positive" immediate can be lui + ori if bigger than unsigned 16 bit range
//            }
//        } else { // "0x"
//            cleaned_instructions[2] = cleaned_instructions[2].substring(2); // Remove 0x
//            if (cleaned_instructions[2].length() < 4) { // in 16 bit SIGNED range.
//                flag = "Addiu";
//                add = new Addiu(new String[]{"addiu", cleaned_instructions[1], "$0", cleaned_instructions[2]});
//            } else {
//                var first = "0x" + hexFix(cleaned_instructions[2].substring(0, cleaned_instructions[2].length() - 4)); //Gets first part and makes it into 4 length for lui
//                var last = "0x" + cleaned_instructions[2].substring(cleaned_instructions[2].length() - 4); //Gets last 4 for ori
//                if (!first.equals("0x0000")) {
//                    flag = "Lui + Ori";
//                    luihalf = new Lui(new String[]{"lui", "$at", first});
//                    orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$at", last});
//                } else {
//                    flag = "Ori";
//                    orihalf = new Ori(new String[]{"ori", cleaned_instructions[1], "$0", last});
//                }
//            }
//        }
//        originalInstruction = cleaned_instructions;
//    }

    private String hexFix(String hex) {
        if (hex.length() == 4) {
            return hex;
        } else if (hex.length() == 3) {
            return "0" + hex;
        } else if (hex.length() == 2) {
            return "00" + hex;
        } else if (hex.length() == 1) {
            return "000" + hex;
        } else {
            return "0000";
        }
    }

    public String[] get_hex() {
        //Put two hexes in array arr[0] = lui.hex arr[1] = ori.hex
        if (add != null) {
            return new String[]{add.get_hex(), ""};
        }
        if (luihalf == null) {
            return new String[]{orihalf.get_hex(), ""};
        }
        return new String[]{luihalf.get_hex(), orihalf.get_hex()};
    }

    public String[] getInstruction() {
        return originalInstruction;
    }
}
