# Hack Assembler (Java)
A Java implementation of the Hack Assembler from the **Nand2Tetris** course.  
This project translates Hack assembly language (`.asm`) programs into 16-bit binary machine code (`.hack`), supporting **symbols, labels, and variables**.

---

## 📌 Features
- **Two-pass assembly**:
  - First pass: Builds the symbol table and resolves labels.
  - Second pass: Translates A- and C-instructions into binary.
- **Predefined symbols** (`R0`–`R15`, `SP`, `LCL`, `ARG`, `THIS`, `THAT`, `SCREEN`, `KBD`).
- **Custom variables** starting at RAM address 16.
- **Parser** that strips comments and whitespace.
- **Code module** that maps mnemonics to binary codes.
- **Symbol table** for managing predefined and user-defined symbols.

---

## 📂 Project Structure
├── Main.java # Entry point, assembles given .asm files
├── Assembler.java # Core assembler logic
├── Parser.java # Cleans input, parses commands
├── Instruction_Category.java # Enum for A, C, and LABEL instructions
├── Code.java # Converts mnemonics to binary codes
├── SymbolTable.java # Handles predefined + user-defined symbols
├── *.asm # Example Hack assembly programs
└── *.hack # Generated binary machine code

---


---

## ⚙️ How It Works
1. **First Pass**  
   - Reads the assembly file and records all labels `(LABEL)` with their corresponding ROM addresses in the symbol table.  

2. **Second Pass**  
   - Translates instructions line by line:
     - `@value` → **A-instruction** (direct address or symbol lookup).  
     - `dest=comp;jump` → **C-instruction** (binary translation of `comp`, `dest`, and `jump`).  

3. **Output**  
   - Writes a `.hack` file containing the 16-bit binary machine code.

---
