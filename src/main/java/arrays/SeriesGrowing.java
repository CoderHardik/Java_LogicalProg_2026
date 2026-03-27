/*
Series - s_n = a + (2^0 * b) + (2^1 * b) + (2^2 * b) + ... + (2^{n-1} * b)
Term 0: a + 1*b
Term 1: a + 1*b + 2*b
Term 2: a + 1*b + 2*b + 4*b
Term 3: a + 1*b + 2*b + 4*b + 8*b

To compute this efficiently, we can maintain a running sum. Instead of re-calculating the powers of 
 from scratch for every term, we start with and in each iteration  , we add to the current total. 

So
Term 0 = a+b
Term 1 = a+b+2b = (old term) +2b
Term 2 = a+b+2b+4b = (old term) + 4b 

every time new term is old term+2x(pre term)
old term +prev term
and prev term will 2 prev term next time

Sample Input
2
0 2 10
5 3 5
Sample Output

2 6 14 30 62 126 254 510 1022 2046
8 14 26 50 98
Explanation
0 - a+b
1- a+b+2b

 */

public class SeriesGrowing {
    public static void main(String[] args) {
       int a=5, b=3, n=5;
       seriesResp(a, b, n) ;
    }

    public static int seriesResp(int a, int b, int n){
        int sum = a+b;
        int temp = b;

        if (n==0) return sum;

        for (int i=1; i<=n; i++){
            temp=2*temp;
            sum = sum+temp;
        }
        return sum;
    }
    
}
