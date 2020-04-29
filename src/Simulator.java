public class Simulator {
    static boolean Fetch=false;
    static boolean Decode=false;
    static boolean Execute=false;
    static boolean Memory=false;
    static boolean WriteBack=false;
    static long CurrentInstruction=0;
    public static void main(String[]args){
        //InstructionFetch im=new InstructionFetch();
       // InstructionDecode rf=new InstructionDecode(32);
        InstructionDecode.init(32);
        InstructionFetch.AddInstruction((3<<21)+(2<<16)+(1<<11)+32);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction((4<<21)+(3<<16)+(3<<11)+34);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction((43*(long)Math.pow(2,26))+(3<<21)+(2<<16));
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction((35*(long)Math.pow(2,26))+(3<<21)+(2<<16));
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction((4*(long)Math.pow(2,26))+(3<<21)+(2<<16));
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(0);
        InstructionFetch.AddInstruction(2*(long)Math.pow(2,26)+50);
        for (int i = 0; i <224 ; i++) {
            MemoryAccess.AddData(0);
        }
//
//        if(InstructionFetch.hasNext()){
//            Fetch=true;
//            CurrentInstruction=InstructionFetch.InstFetch();
//            System.out.println();
//        }
//       while(Fetch||Decode||Execute||Memory||WriteBack) {
//            if(Memory) {
//                WriteBackClass.WriteBack(ExecuteClass.ALUresult, InstructionDecode.ReadData2,
//                        InstructionDecode.getRegDst(), InstructionDecode.getMemtoReg());
//                Memory =false;
//                WriteBack =true;
//                System.out.println();
//            }
//
//          if(Execute) {
//              MemoryAccess.MemAccess(ExecuteClass.ALUresult, InstructionDecode.ReadData2,
//                      InstructionDecode.getMemWrite(), InstructionDecode.getMemRead());
//              Execute=false;
//              Memory=true;
//              System.out.println();
//          }
//
//          if(Decode) {
//              ExecuteClass.Execute(InstructionDecode.funct, InstructionDecode.getALUOp(), InstructionDecode.getALUSrc(), InstructionDecode.getReadData1(),
//                      InstructionDecode.getReadData2(), InstructionDecode.getImm(), InstructionFetch.pc);
//              Decode=false;
//              Execute=true;
//              System.out.println();
//          }
//          if(Fetch) {
//              InstructionDecode.InstDecode(CurrentInstruction);
//              Fetch=false;
//              Decode=true;
//              System.out.println();
//          }
//           if(InstructionFetch.hasNext()){
//               Fetch=true;
//               CurrentInstruction=InstructionFetch.InstFetch();
//               System.out.println();
//           }
//
//        }
        while(InstructionFetch.hasNext()){
            CurrentInstruction=InstructionFetch.InstFetch();
            System.out.println();
            InstructionDecode.InstDecode(CurrentInstruction);
            System.out.println();
            ExecuteClass.Execute(InstructionDecode.funct, InstructionDecode.getALUOp(), InstructionDecode.getALUSrc(), InstructionDecode.getReadData1(),
                    InstructionDecode.getReadData2(), InstructionDecode.getImm(), InstructionFetch.pc);
            System.out.println();
            MemoryAccess.MemAccess(ExecuteClass.ALUresult, InstructionDecode.ReadData2,
                    InstructionDecode.getMemWrite(), InstructionDecode.getMemRead());
            System.out.println();
            WriteBackClass.WriteBack(ExecuteClass.ALUresult, InstructionDecode.ReadData2,
                    InstructionDecode.getRegDst(), InstructionDecode.getMemtoReg());
            System.out.println();
        }
    }
}
