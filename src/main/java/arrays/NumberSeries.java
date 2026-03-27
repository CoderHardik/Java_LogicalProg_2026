/* Take input x
 * and print output x+xx+xxx+xxxx
 * 
 * i.e.
 * if 5 -> 5+55+555+5555 -> 5(1+11+111)
 * 
 */

public class NumberSeries {
    public static void main(String[] args) {
        System.out.println(numeries(5, 4));
    }

    public static int numeries(int x, int n) {
        int temp_sum=0;
        int sum=0;

        for (int i=0; i<=n; i++){

            temp_sum=temp_sum+x;
            sum = sum*10 + temp_sum;
        }

        return sum;
    }
    
}
