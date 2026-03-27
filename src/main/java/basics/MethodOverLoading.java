public class MethodOverLoading {
    public static void main(String [] args){
        int sum, a,b,c;
        a=10;
        b=20;
        c=15;
        sum = addNum (a,b);
        System.out.println(sum);
    }

    public static int addNum (int x,int y){
        return (x+y);
    }

    public static int addNum(int x,int y,int z){
        return (x+y+z);
    }
    
}
