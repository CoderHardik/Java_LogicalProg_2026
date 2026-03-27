package arrays;

class MaxsubArrayAddition {
    public static void main(String[] args) {
        int a[] = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println(maxsub(a, k)); // Output: 9
    }

    public static int maxsub(int[] arr, int k) {
        if (arr.length < k) return 0;

        int maxSum = 0;
        int windowSum = 0;

        // 1. Calculate sum of the first window
        for (int i = 0; i < k; i++) {
            windowSum = windowSum + arr[i];
        }
        
        maxSum = windowSum;

        // 2. Slide the window: add next element, remove the first element of previous window
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum + arr[i];
            windowSum = windowSum - arr[i-k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}
