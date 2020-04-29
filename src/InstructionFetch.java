import java.util.*;

public class InstructionFetch {
    static ArrayList<Long> InstructionMem=new ArrayList<>();
     static int pc=0;

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
    public static  void AddInstruction(long i){
        InstructionMem.add(i);
    }
    public  static long InstFetch(){
        long res= InstructionMem.get(pc);
        System.out.println
                ("fetched instruction "+toBinary(InstructionMem.get(pc)));
        ProgCount(res);
        return res;

    }
    public  static void ProgCount(long res){
        pc+=4;
        System.out.println("incremented pc value is "+toBinary(pc));
    }
    public static void branch(int BranchResult){
        pc=BranchResult;
    }
    public static boolean hasNext(){
        return pc< InstructionMem.size();
    }
}
