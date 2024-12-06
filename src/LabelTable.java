import javax.swing.*;

public class LabelTable extends Table {
    public LabelTable() {
        super();
    }

    @Override
    public void push(String memory) {}

    public void push(String label, int lineNumber){
        if(table.containsKey(label))
            return;

        table.put(label, lineNumber);
    }

    public Integer get(String label) {
        return table.get(label);
    }

    public Boolean contains(String label) {
        return table.containsKey(label);
    }
}
