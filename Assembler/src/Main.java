import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Assembler assembler = new Assembler();

            assembler.assemble("Add.asm", "Add.hack");
            assembler.resetAssembler();

            assembler.assemble("Max.asm", "Max.hack");
            assembler.resetAssembler();

            assembler.assemble("Rect.asm", "Rect.hack");
            assembler.resetAssembler();

            assembler.assemble("Pong.asm", "Pong.hack");
            assembler.resetAssembler();

            new java.io.PrintWriter("prog.txt").close();
            System.out.println("Assembling completed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}