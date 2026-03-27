public class FrogJump {
    public static void main(String[] args) {
        int n = 5;
        int res = frogJump(n);
        System.out.println(res);
    }

    public static int frogJump(int n){
        //This is fibonnaci implementation so similiar to stair case problem we did earlier
        if (n<=0) return 0;
        if (n==1) return 1; //ways frog can jum to step 1
        if (n==2) return 2; // ways from can jump to step 0 to step 2

        int first = 1;
        int second=2;
        int current=0;

        for (int i=3; i<=n; i++){
            current = first +second;
            first=second;
            second=current;
        }

        return second;
    }
    
}
