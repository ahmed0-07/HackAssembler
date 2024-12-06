import java.io.File;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("No file specified");
            return;
        }

        HackAssembler assembler = new HackAssembler(args[0]);
        assembler.run();
    }
}