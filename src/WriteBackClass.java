public class WriteBackClass {
    static int WriteData=-1;
    public  static void WriteBack(int ALUResult,int ReadData2,int RegDst,int MemToReg){
        System.out.println("in writing back stage: ");
        if(MemToReg==1){
            WriteData=ReadData2;
        }
        else WriteData=ALUResult;
        System.out.println("WriteData is "+toBinary(WriteData));
        if(InstructionDecode.RegWrite==1){
            if(RegDst==1){
                int write=InstructionDecode.getWrite();
                String name=InstructionDecode.getAddressToName().get(write);
                InstructionDecode.getNameToValue().replace(name,WriteData);
                System.out.println("replaced the value at reg "+name+" with writeData");
            }
            else {
                int write=InstructionDecode.getRt();
                String name=InstructionDecode.getAddressToName().get(write);
                InstructionDecode.getNameToValue().replace(name,WriteData);
                System.out.println("replaced the value at reg "+name+" with writeData");
            }
        }


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
