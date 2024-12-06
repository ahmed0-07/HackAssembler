import java.util.HashMap;

public class Coder {
    public HashMap<String,String> compA;
    public HashMap<String,String> compM;
    public HashMap<String,String> dst;
    public HashMap<String,String> jmp;

    public Coder(){
        compA = new HashMap<String, String>();
        compM = new HashMap<String, String>();
        dst = new HashMap<String, String>();
        jmp = new HashMap<String, String>();

        compA.put("0","101010");compA.put("1","111111");compA.put("-1","111010");
        compA.put("D","001100");compA.put("A","110000");compA.put("!D","001101");
        compA.put("!A","110001");compA.put("-D","001111");compA.put("-A","110011");
        compA.put("D+1","011111");compA.put("A+1","110111");compA.put("D-1","001110");
        compA.put("A-1","110010");compA.put("D+A","000010");compA.put("D-A","010011");
        compA.put("A-D","000111");compA.put("D&A","000000");compA.put("D|A","010101");

        compM.put("M","110000");compM.put("!M","110001");compM.put("-M","110011");
        compM.put("M+1","110111");compM.put("M-1","110010");compM.put("D+M","000010");
        compM.put("D-M","010011");compM.put("M-D","000111");compM.put("D&M","000000");
        compM.put("D|M","010101");

        dst.put("","000");dst.put("M","001");dst.put("D","010");dst.put("MD","011");
        dst.put("A","100");dst.put("AM","101");dst.put("AD","110");dst.put("AMD","111");

        jmp.put("","000");jmp.put("JGT","001");jmp.put("JEQ","010");jmp.put("JGE","011");
        jmp.put("JLT","100");jmp.put("JNE","101");jmp.put("JLE","110");jmp.put("JMP","111");
    }

    public String handleAInstruction(int instruction){
        String binary = Integer.toBinaryString(instruction);
        return String.format("%15s", binary).replace(' ', '0');
    }

    public String handleCInstruction(String instruction){
        String comp = "", dis = "", jump = "";
        String binary = "111";
        String[] cutAtSemi = instruction.split(";");
        String[] cutAtEqual = cutAtSemi[0].split("=");
        if(cutAtSemi.length == 2){
            jump = cutAtSemi[1];
        }
        if(cutAtEqual.length == 2) {
            dis = cutAtEqual[0];
        }
        if(cutAtEqual.length == 2){
            comp = cutAtEqual[1];
        }
        else{
            comp  = cutAtEqual[0];
        }

        String a;
        String comp_binary;
        if(compA.containsKey(comp)){
            a = "0";
            comp_binary = compA.get(comp);
        }
        else{
            a = "1";
            comp_binary = compM.get(comp);
        }

        String dis_binary = dst.get(dis);
        String jump_binary = jmp.get(jump);
        return binary + a + comp_binary + dis_binary + jump_binary;
    }
}
