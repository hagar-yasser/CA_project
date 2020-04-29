public class ExecuteClass {
    static int ALUresult =-1;
    static  int BranchAddressResult=-1;
    static String ZeroFlag ="0";
    static int ReadData2=-1;
    static String ALUoperation = "";
    public  static void Execute(int funct,String ALUOp,int ALUSrc,int ReadData1,int ReadData2x,int SignExtend,int pc){
        ReadData2=ReadData2x;
        BranchAddressResult=pc+(SignExtend%(1<<30))*4;
        if(InstructionDecode.Branch==1)
            InstructionFetch.branch(BranchAddressResult);
        ALUController(ALUOp,funct);
        if(ALUSrc==1)
            ALUEvaluator(ALUoperation,ReadData1,SignExtend);
        else ALUEvaluator(ALUoperation,ReadData1,ReadData2);
        System.out.println("Executed and Results are: ");
        System.out.println("ALUResult "+toBinary(ALUresult));
        System.out.println("Zero flag "+ZeroFlag);
        System.out.println("Branch Address "+toBinary(BranchAddressResult));
        System.out.println("ReadData2 "+toBinary(ReadData2));
    }
//    public static void main(String[]args){
//        Execute(0,"",0,0,0,4,4);
//        System.err.println(BranchAddressResult);
//    }
    public static void ALUController(String ALUOp,int funct){
        switch (ALUOp) {
            case "00":
                ALUoperation = "0010";
                break;
            case "01":
                ALUoperation = "0110";
                break;
            default:
                switch (funct) {
                    case 32:
                        ALUoperation = "0010";
                        break;
                    case 34:
                        ALUoperation = "0110";
                        break;
                    case 36:
                        ALUoperation = "0000";
                        break;
                    case 37:
                        ALUoperation = "0001";
                        break;
                    default:
                        ALUoperation = "0111";
                }


        }
    }
    public static void ALUEvaluator ( String Op, int Operand1 , int Operand2 ){
        String operationName="";
        switch (Op){
            case "0000":
                operationName="AND";
                ALUresult =Operand1&Operand2;
                break;
            case "0001":
                operationName="OR";
                ALUresult =Operand1|Operand2;
                break;
            case "0010":
                operationName="add";
                ALUresult =Operand1+Operand2;
                break;
            case "0110":
                operationName="sub";
                ALUresult =Operand1-Operand2;
                break;
            case "0111":
                operationName="slt";
                ALUresult =(Operand1<Operand2)?1:0;
                break;
            case "1100":
                operationName="NOR";
                ALUresult =~(Operand1|Operand2);
                break;
            default:
                operationName="invalid operation";
                System.err.println("invalid operation");
                break;
        }
        if(ALUresult ==0)
            ZeroFlag ="1";



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
