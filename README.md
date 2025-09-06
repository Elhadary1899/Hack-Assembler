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
├── Main.java # Entry point, assembles given .asm files <br>
├── Assembler.java # Core assembler logic </br>
├── Parser.java # Cleans input, parses commands</br>
├── Instruction_Category.java # Enum for A, C, and LABEL instructions</br>
├── Code.java # Converts mnemonics to binary codes</br>
├── SymbolTable.java # Handles predefined + user-defined symbols</br>
├── *.asm # Example Hack assembly programs</br>
└── *.hack # Generated binary machine code</br>

---

## ⚙️ How It Works
1. **Cleaning the Code**
   - Cleans the input file from any whitespaces or comments.
2. **First Pass**  
   - Reads the assembly file and records all labels `(LABEL)` with their corresponding ROM addresses in the symbol table.  
3. **Second Pass**
   - Translates instructions line by line:
     - `@value` → **A-instruction** (direct address or symbol lookup).  
     - `dest=comp;jump` → **C-instruction** (binary translation of `comp`, `dest`, and `jump`).  
4. **Output**  
   - Writes a `.hack` file containing the 16-bit binary machine code.
  
---

## 📝 Example
**Input (`Add.asm`):**
```asm
@2
D=A
@3
D=D+A
@0
M=D
```

##### output
0000000000000010
1110110000010000
0000000000000011
1110000010010000
0000000000000000
1110001100001000

---

## 📖 References
- Nand2Tetris Course
- The Elements of Computing Systems (Noam Nisan & Shimon Schocken)

---
