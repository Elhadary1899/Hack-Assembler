import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<String> lines;
    private int currentLineCounter = 0;
    private String currentLine;

    Parser(List<String> lines){
        this.lines = cleanLines(lines);
    }

    public List<String> cleanLines(List<String> lines){
        ArrayList<String> cleaned = new ArrayList<>();
        for(String line : lines) {
            line = line.replaceAll("//.*", "").trim();
            if(!line.isEmpty()){
                cleaned.add(line);
            }
        }
        return cleaned;
    }

    public boolean hasMoreCommands(){
        return currentLineCounter < lines.size();
    }

    public void advance(){
       currentLine = lines.get(currentLineCounter++);
    }

    public int getCurrentLineCounter(){
        return this.currentLineCounter;
    }

    public Instruction_Category commandType(){
        if(currentLine.charAt(0) == '@'){
            return Instruction_Category.A_COMMAND;
        } else if(currentLine.charAt(0) == '('){
            return Instruction_Category.LABEL;
        }else{
            return Instruction_Category.C_COMMAND;
        }
    }

    public String symbol(){
        if(commandType() == Instruction_Category.A_COMMAND) return currentLine.substring(1);
        else if(commandType() == Instruction_Category.LABEL) return currentLine.substring(1, currentLine.length() - 1);
        else return null;
    }

    public String dest(){
        if(!currentLine.contains("=")) return "";
        else return currentLine.split("=")[0];
    }

    public String comp(){
        String[] parts = currentLine.split("=");
        String compPart = parts.length == 2 ? parts[1] : parts[0];
        return compPart.split(";")[0];
    }

    public String jump(){
        if(!currentLine.contains(";")) return "";
        return currentLine.split(";")[1];
    }
}
