public class HackAssembler {
    Parser parser;
    Writer output;
    Coder coder;
    SymbolTable symbols;
    LabelTable labels;

    public HackAssembler(String file) {
        parser = new Parser(file);
        String Output = file.substring(0, file.lastIndexOf("."));
        output = new Writer(Output);
        coder = new Coder();
        symbols = new SymbolTable();
        labels = new LabelTable();
    }

    public void firstIteration(){
        int linenumber = 0;
        while(parser.hasMoreLines()){
            parser.advance();
            if(parser.notValidInstruction()){
                continue;
            }

            if(parser.insIsLabel()) { // if is label (xxx)
                labels.push(parser.labelIns(), linenumber);
            }
            else
                linenumber++;
        }

        parser.init();
    }

    public void secondIteration(){
        while(parser.hasMoreLines()){
            parser.advance();
            if(parser.notValidInstruction()){
                continue;
            }

            if(parser.insIsSymbol()){ //if is @xxxx (A - instruction)
                String memo = parser.symbolIns();
                int instruction = 0;
                try {
                    instruction = Integer.parseInt(memo);
                }
                catch(NumberFormatException e){
                    if(symbols.contains(memo)) {
                        instruction = symbols.get(memo);
                    } else if (labels.contains(memo)) {
                        instruction = labels.get(memo);
                    }
                    else{
                        symbols.push(memo);
                        instruction = symbols.get(memo);
                    }
                }

                String to_output = '0' + coder.handleAInstruction(instruction);
                output.write(to_output);
            }
            else if(parser.insIsC()){ // c instruction
                String to_output = coder.handleCInstruction(parser.CIns());
                output.write(to_output);
            }
        }
    }

    public void run()
    {
        firstIteration();
        secondIteration();
    }
}
