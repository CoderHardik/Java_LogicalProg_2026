public class fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonaccinum(7));

    }
    public static int fibonaccinum(int a){

        int initial=0;
        int next=1;
        int sum=0;

        if (a==0) return 0;
        if (a==1) return 1;

        for (int i=2; i<=a; i++){
            sum = initial+next;
            initial=next;
            next=sum;
        }
        return next;

    }

}
