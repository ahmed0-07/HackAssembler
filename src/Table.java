import java.util.HashMap;

public abstract class Table {
    HashMap<String, Integer> table;

    public Table() {
        table = new HashMap<>();
    }
    public abstract void push(String memory);
    public abstract void push(String label, int lineNumber);
    public abstract Boolean contains(String label);
    public abstract Integer get(String label);
}
