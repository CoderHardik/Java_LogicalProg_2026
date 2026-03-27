public class Factorial {
    public static void main(String[] args) {
        int num=5;
        System.out.println(fact(5));
    }

    public static int fact(int i){
        int result=1;
        if (i==0) {
            return result;
        }
        else
        {
        result= (fact(i-1)*i);
        }
        return result;
    }
}
