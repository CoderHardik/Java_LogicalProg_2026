public class HighestConsecutiveProduct {

    public static void main(String[] args) {
        int A[] = {3,2,1,0,4};
        System.out.println(highcons(A));
    }
    
    public static int highcons(int A[]){

        int temp_max=1;

        for (int i=0; i<A.length-1; i++){
            if ((A[i] * A[i+1])>temp_max){
            temp_max = A[i] * A[i+1];
            }
        }//end of for

        return temp_max;
    }
}
