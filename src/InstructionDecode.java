import java.util.*;

public class InstructionDecode {
    static HashMap<Integer, String> addressToName;
    static HashMap<String, Integer> NameToValue;
    static int rs = -1;
    static int rt = -1;
    static int write = -1;
    static int imm = -1;
    static int N;
    static String ALUOp = "";
    static int RegDst = -1;
    static int ALUSrc = -1;
    static int RegWrite = -1;
    static int MemRead = -1;
    static int MemWrite = -1;
    static int Branch = -1;
    static int MemtoReg = -1;
    static int funct = -1;

    static int ReadData1=0;
    static int ReadData2=0;
    static long Inst=0;
    static int shamt=0;
    public static int getShamt() {
        return shamt;
    }

    public static int getImm() {
        return imm;
    }
    public static int getRs() {
        return NameToValue.get(addressToName.get(rs));
    }

    public static int getReadData1() {
        return ReadData1;
    }

    public static int getReadData2() {
        return ReadData2;
    }

    public  static int getRt() {
        return NameToValue.get(addressToName.get(rt));
    }

    public static  int getWrite() {
        return NameToValue.get(addressToName.get(write));
    }




    public static HashMap<Integer, String> getAddressToName() {
        return addressToName;
    }

    public static HashMap<String, Integer> getNameToValue() {
        return NameToValue;
    }

    public static  int getRegDst() {
        return RegDst;
    }

    public  static int getRegWrite() {
        return RegWrite;
    }

    public  static int getMemRead() {
        return MemRead;
    }

    public  static int getMemWrite() {
        return MemWrite;
    }

    public static  int getBranch() {
        return Branch;
    }

    public  static int getMemtoReg() {
        return MemtoReg;
    }


    public  static String getALUOp() {
        return ALUOp;
    }

    public  static int getALUSrc() {
        return ALUSrc;
    }



    public static void init(int n) {
        N = n;

        addressToName = new HashMap<>();
        NameToValue = new HashMap<>();
        for (int i = 0; i < N; i++) {
            addressToName.put(i, "r" + i);
            NameToValue.put("r" + i, i * 7);//DummyValues
        }
    }

    public  static void InstDecode(long instruction) {
        System.out.println("Decoded the following: ");
        Inst=instruction;
        if (instruction == -1) return;
        long op = instruction >> 26;
        imm = SignExtend((int) (instruction % (1 << 16)));
        funct = (int) (instruction % (1 << 6));
        instruction=instruction>>6;
        shamt=(int)(instruction % (1 << 6));
        instruction=instruction>>6;
        ContUnit((int) op);
        write = (int) ( instruction& ((1 << 5) - 1));
        instruction =instruction  >> 5;
        rt = (int) ( instruction& ((1 << 5) - 1));
        instruction = instruction >> 5;
        rs = (int) (instruction % (1 << 5));
       // System.err.println(rs);
        ReadData1=NameToValue.get(addressToName.get(rs));
        ReadData2=NameToValue.get(addressToName.get(rt));
        System.out.println("ReadData1 "+toBinary(ReadData1));
        System.out.println("ReadData2 "+toBinary(ReadData2));
        System.out.println("ALUop "+ALUOp);
        System.out.println("RegDst "+RegDst);
        System.out.println("ALUSrc "+ALUSrc);
        System.out.println("RegWrite "+RegWrite);
        System.out.println("MemToReg "+MemtoReg);
        System.out.println("MemRead "+MemRead);
        System.out.println("MemWrite "+MemWrite);
        System.out.println("Branch "+Branch);

//        if (op == 0) {
//            if (funct == 32)
//                DecodeAddOrSub("Add", ((instruction >> 11) & ((1 << 15) - 1)));
//            else DecodeAddOrSub("sub", ((instruction >> 11) & ((1 << 15) - 1)));
//            return;
//        }
//        if (op == 35) {
//            DecodeLw(instruction & ((1 << 26) - 1));
//            return;
//        }
//        if (op == 43) {
//            Decodesw(instruction & ((1 << 26) - 1));
//            return;
//        }
//        Decodebeq(instruction & ((1 << 26) - 1));



    }
    public static void DecodeR(){

    }

    public  static int SignExtend(int inst) {
        long res = inst;
        if (res >>15!=0) {
            //System.err.println("yes");
            long ex = (1 << 16) - 1;
            ex = ex << 16;
            res = res | ex;
        }
        return (int)res;
    }

    public  static void ContUnit(int op) {
        switch (op) {
            case 0:
                ALUOp = "10";
                RegDst = 1;
                ALUSrc = 0;
                RegWrite = 1;
                MemRead = 0;
                MemWrite = 0;
                Branch = 0;
                MemtoReg = 0;
                break;
            case 4:
                ALUOp = "01";
                RegDst = 0;
                ALUSrc = 0;
                RegWrite = 0;
                MemRead = 0;
                MemWrite = 0;
                Branch = 1;
                MemtoReg = 0;
                break;
            case 35:
                ALUOp = "00";
                RegDst = 0;
                ALUSrc = 1;
                RegWrite = 1;
                MemRead = 1;
                MemWrite = 0;
                Branch = 0;
                MemtoReg = 1;
                break;
            default:
                ALUOp = "00";
                RegDst = 0;
                ALUSrc = 1;
                RegWrite = 0;
                MemRead = 0;
                MemWrite = 1;
                Branch = 0;
                MemtoReg = 0;
                break;


        }

    }

    public void DecodeAddOrSub(String type, long reg) {
        write = (int) (reg & ((1 << 5) - 1));
        reg = reg >> 5;
        rt = (int) (reg & ((1 << 5) - 1));
        reg = reg >> 5;
        rs = (int) reg;
        System.out.print(type + " ");
        System.out.print("value in register " + addressToName.get((int) rs) + " which is " + toBinary(NameToValue.get(addressToName.get((int) rs))) + " ");
        System.out.print("and value in register " + addressToName.get((int) rt) + " which is " + toBinary(NameToValue.get(addressToName.get((int) rt))) + " ");
        System.out.println("and put it in register " + addressToName.get((int) write) + " And here the write flag is set to true");
    }

    public void DecodeLw(long reg) {
       // imm = (int) (reg & ((1 << 16) - 1));
        reg = reg >> 16;
        rt = (int) (reg & ((1 << 5) - 1));
        reg = reg >> 5;
        rs = (int) reg;
        System.out.print("lw from data memory address stored in register " + addressToName.get((int) rs) + " which is " + toBinary(NameToValue.get(addressToName.get((int) rs))) + " ");
        System.out.print("after adding an offset whose value is in register " + toBinary(imm));
        System.out.println("in register " + addressToName.get((int) rt) + " And here the write flag is set to true");

    }

    public void Decodesw(long reg) {
       //
        // imm = (int)(reg & ((1 << 16) - 1));
        reg = reg >> 16;
         rt = (int)(reg & ((1 << 5) - 1));
        reg = reg >> 5;
         rs =(int) reg;
        System.out.print("sw in data memory address stored in register " + addressToName.get((int) rs) + " which is " + toBinary(NameToValue.get(addressToName.get((int) rs))) + " ");
        System.out.print("after adding an offset whose value is " + imm);
        System.out.println(" the data in register " + addressToName.get((int) rt) + " which is " + toBinary(NameToValue.get(addressToName.get((int) rt))) + " And here the write flag is set to false");

    }

    public void Decodebeq(long reg) {
       // imm =(int)( reg & ((1 << 16) - 1));
        reg = reg >> 16;
         rt =(int) (reg & ((1 << 5) - 1));
        reg = reg >> 5;
         rs =(int) reg;
        System.out.print("check if value in register " + addressToName.get((int) rs) + " which is " + toBinary(NameToValue.get(addressToName.get((int) rs))) + " ");
        System.out.print("is equal to value in register " + addressToName.get((int) rt) + " which is " + toBinary(NameToValue.get(addressToName.get((int) rt))) + " ");
        System.out.println("and if yes add an offset to the next PC equal to " + toBinary(imm) + " and write flag is false");
    }

    public  static String toBinary(long x) {
        long xs=x;
        String res = "";
        while (x != 0) {
            res =( (2+(x % 2))%2) + res;
            x /= 2;
        }
        while (xs>=0&&res.length() < 32) {
            res = "0" + res;
        }
        while (xs<0&&res.length() < 32) {
            res = "1" + res;
        }

        return res;
    }


}
