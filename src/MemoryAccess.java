import java.util.ArrayList;

public class MemoryAccess {
    static  int[] DataMemory=new int[1024];
    static int [][]Cache=new int[8][3];
    static int ALUResult = -1;
    static int ReadData = -1;
    static int lastAdded=0;

    public  static void AddData(int i) {
        DataMemory[lastAdded++]=i;
    }
    public  static void MemAccess(int Result,int ReadData2,int MemWrite,int MemRead){
        System.out.println("In Data Memory: ");
        ALUResult=Result;
        System.out.println("ALUResult "+toBinary(ALUResult));
        if(MemRead==1){
            if(Cache[Result%8][0]==1&&Cache[Result%8][1]==Result/8)
                ReadData=Cache[Result%8][2];
            else {
                ReadData = DataMemory[Result];
                Cache[Result%8][0]=1;
                Cache[Result%8][1]=Result/8;
                Cache[Result%8][2]=DataMemory[Result];
            }
            System.out.println("Have Read data "+toBinary(ReadData)+" from address "+toBinary(Result));
        }
        if(MemWrite==1){
//            DataMemory.remove(Result);
//            DataMemory.add(Result,ReadData2);
            DataMemory[Result]=ReadData2;
            System.out.println("Wrote "+toBinary(ReadData2)+" in address "+toBinary(Result));
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
