public class SymbolTable extends Table{
    private int pos = 16;

    public SymbolTable() {
        super();
        for(int i = 0; i < pos; i++)
            table.put("R"+i, i);

        table.put("SCREEN", 16384);
        table.put("KBD", 24576);
        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
    }

    @Override
    public void push(String memory){
        if(table.containsKey(memory))
            return;

        table.put(memory, pos);
        pos++;
    }

    @Override
    public Boolean contains(String label) {
        return table.containsKey(label);
    }

    @Override
    public Integer get(String memo) {
        return table.get(memo);
    }

    @Override
    public void push(String label, int lineNumber) {}
}
