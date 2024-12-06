import java.io.*;
import java.util.Scanner;

public class Parser {
    Scanner reader;
    private String currentInstruction;
    File ASM_FILE;

    public Parser(String f) {
        ASM_FILE = new File(f);
        init();
    }

    public void init(){
        try{
            this.reader = new Scanner(ASM_FILE);
        }
        catch (FileNotFoundException e) {
            throw new IllegalStateException("File not found");
        }
    }

    public Boolean hasMoreLines() {
        return reader.hasNextLine();
    }

    public Boolean notValidInstruction() {
        return (this.currentInstruction.isEmpty() || (this.currentInstruction.charAt(0) == '/' && this.currentInstruction.charAt(1) == '/'));
    }

    public void advance(){
        this.currentInstruction = reader.nextLine();
        this.currentInstruction = this.currentInstruction.replaceAll("//.*", "").trim();
        this.currentInstruction = this.currentInstruction.stripLeading();
        this.currentInstruction = this.currentInstruction.stripTrailing();
    }

    public boolean insIsSymbol(){
        return (this.currentInstruction.charAt(0) == '@');
    }

    public String symbolIns(){
        return this.currentInstruction.substring(1);
    }

    public boolean insIsLabel(){
        return (this.currentInstruction.startsWith("(") && this.currentInstruction.endsWith(")"));
    }

    public String labelIns(){
        return this.currentInstruction.substring(1, this.currentInstruction.length() - 1);
    }

    public Boolean insIsC(){
        return !insIsSymbol() && !insIsLabel();
    }

    public String CIns(){
        return this.currentInstruction;
    }

}
