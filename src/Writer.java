import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer {
    PrintWriter writer;

    public Writer(String filename) {
        File OUTPUT_FILE = new File(filename + ".hack");
        try {
            this.writer = new PrintWriter(OUTPUT_FILE);
        }
        catch (FileNotFoundException e){
            throw new IllegalStateException("File not found");
        }
    }

    public void write(String text) {
        writer.println(text);
        writer.flush();
    }
}
