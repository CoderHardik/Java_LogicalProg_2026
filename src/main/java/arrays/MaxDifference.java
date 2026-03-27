/*
How the Logic Works 
Using your provided array int a[] = {1, 4, 8, 15, 17};, here is a step-by-step breakdown of what the maxadjdiff method does: 

Step	Index i	Index j	Calculation (a[j] - a[i])	Current max
1	0 (1)	1 (4)	4 - 1 = 3	3
2	1 (4)	2 (8)	8 - 4 = 4	4
3	2 (8)	3 (15)	15 - 8 = 7	7
4	3 (15)	4 (17)	17 - 15 = 2	7
*/


public class MaxDifference {

    public static void main(String[] args) {
        int A[] = {1, 4, 8, 15, 17};
        System.out.println(maxdiff(A));
    }

    public static int maxdiff(int A[]){

        int max_diff=0, diff=0;

        for (int i=0; i<A.length-1; i++){
            diff = Math.abs(A[i+1]-A[i]);

            if(max_diff<diff){
                max_diff=diff;
            }
        }
        return max_diff;
    }
    
}
