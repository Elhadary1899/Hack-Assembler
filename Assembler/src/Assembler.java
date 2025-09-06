import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Assembler {
    private SymbolTable symbolTable = new SymbolTable();
    private int variableAddress = 16;

    public void assemble(String inputPath, String outputPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputPath));
        Parser parser = new Parser(lines);

        int instructionAddress = 0;
        while (parser.hasMoreCommands()) {
            parser.advance();
            if (parser.commandType() == Instruction_Category.LABEL) {
                symbolTable.addEntry(parser.symbol(), instructionAddress);
            } else {
                instructionAddress++;
            }
        }

        parser = new Parser(lines);
        List<String> output = new ArrayList<>();

        while (parser.hasMoreCommands()) {
            parser.advance();

            switch (parser.commandType()) {
                case Instruction_Category.A_COMMAND:
                    String command = parser.symbol();
                    int address;
                    if (isNumeric(command)) {
                        address = Integer.parseInt(command);
                    }else {
                        if (!symbolTable.containsSymbol(command)){
                            symbolTable.addEntry(command, variableAddress++);
                        }
                        address = symbolTable.getAddress(command);
                    }
                    output.add(to16BitBinary(address));
                    break;
                case Instruction_Category.C_COMMAND:
                    String binary = "111" + Code.comp(parser.comp()) + Code.dest(parser.dest()) + Code.jump(parser.jump());
                    output.add(binary);
                    break;
            }
        }

        Files.write(Paths.get(outputPath), output);
    }

    public void resetAssembler(){
        symbolTable = new SymbolTable();
        variableAddress = 16;
    }

    private boolean isNumeric(String s){
        return s.matches("\\d+");
    }
    private String to16BitBinary(int value){
        return String.format("%16s", Integer.toBinaryString(value)).replace(' ', '0');
    }
}
