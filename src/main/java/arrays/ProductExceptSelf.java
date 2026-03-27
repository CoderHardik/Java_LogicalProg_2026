package arrays;

import java.util.Arrays;

/*
Input:
[1,2,3,4]
Output:
[24,12,8,6]

Step 1 - Left Products: [1, 1, 2, 6]
- result[0]
- result[1] = a[0]*result[0] = 1*1 = 1
- result[2] = a[1]*result[1] = 2*1 = 2 and so on
Step 2 - Right product: 
i=3 - result[3] = result[3] *1 = 6*1 = 6
-   right =1*4= 4
i=2 - result[2] = result[2]* right =2*4 =8
    right = 3*4=12
i=1 - result[1]*right =1*12 =12
    right = 12*2 = 24


  
- 
*/

public class ProductExceptSelf {
    
public static int [] product(int a[]){
    int n = a.length;
    int result[] = new int[n];

    result[0]=1;

    for(int i=1; i<n; i++){
        result[i] = a[i-1]*result[i-1];
    }

    int right=1;

    for(int i=n-1; i>=0; i--){
        result[i] = result[i] * right;
        right = a[i]*right;
    }

    return result;
}

public static void main(String[] args) {
        // Test Input
        int[] input = {1, 2, 3, 4};
        
        // Create an instance of the solution
        int[] output = product(input);
        
        // Print the result: [24, 12, 8, 6]
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + Arrays.toString(output));
    }


}
