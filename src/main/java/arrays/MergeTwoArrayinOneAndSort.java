import java.util.Arrays;

public class MergeTwoArrayinOneAndSort {

    public static void main(String[] args) {
        int A[]= {1,2,4,6,0,0,0};
		int B[]= {3,5,8};
		
		int m = 4;
		int n = 3;
        System.out.println(Arrays.toString(mergeandsort(A, B, m,n));
    }

    //The number of elements initialized in nums1 and nums2 are m and n respectively.
	//m and n are just initialized elements and not length of array
	// Here it is given that both A and B are sorted - If not, use Array.sort(A).
	
    public static int[] mergeandsort(int A[], int B[], int m, int n){

        while(m>0 && n>0){
            if(A[m-1]>B[n-1]){
                A[m+n-1]=A[m-1];
                m--;
            }
            else{
                A[m+n-1]=B[n-1];
                n--;
            }
        }

        while (m>0){
            A[m+n-1]=A[m-1];
            m--;
        }
        while (n>0){
            A[m+n-1]=B[n-1];
            n--;
        }

        return A;
    }
    
}
