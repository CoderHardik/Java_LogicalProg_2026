/*
Given a sorted array, remove the duplicates in-place such that each element appears only once and return the new length.
Do not allocate extra space for another array; you must do this by modifying the input array in-place with O(1) extra memory.
"Input: nums = [1, 1, 2, 2, 3, 4, 4]
Output: 4 (and the first four elements of the array become [1, 2, 3, 4])

The Overwrite: When nums[i] != nums[i - 1], it means we've moved past a "block" of duplicates and found a new value. 
We immediately move it to the front at writeIndex.

When an interviewer asks to "Remove Duplicates In-Place," 
they aren't actually deleting memory (which is impossible with fixed-size arrays in Java). 
Instead, they want you to reorganize the array so the unique elements are at the front.

Here is how you explain the return value and the "removal" process to an interviewer:
1. The "Virtual" ArrayExplain that the int you return defines the new effective size of the array.
The Interviewer's View: They will treat your return value (\(k\)) as the length. They will only look at nums[0] through nums[k-1].What happened to the duplicates? 
They still exist at the end of the array (garbage values), but they are ignored.

Since Java arrays have a fixed size, we cannot physically shrink the array. 
By returning the count of unique elements, I am defining a 'valid window' at the start of the array. 
The elements after this index are considered 'trash.' 
This satisfies the \(O(1)\) space requirement because we aren't creating a new, smaller array.

Input: [1, 1, 2, 2, 3]
Your Code Modifies it to: [1, 2, 3, 2, 3] (The 2, 3 at the end are just leftovers).
Your Code Returns: 3

*/

public class Solution {
    public int removeDuplicates(int[] nums) {
        // Edge case: if array is empty
        if (nums.length == 0) {
            return 0;
        }

        // Slow pointer (writeIndex): position to overwrite with the next unique element
        int writeIndex = 1;

        // Fast pointer (i): scans through the array
        for (int i = 1; i < nums.length; i++) {
            // Check if the current element is different from the previous one
            if (nums[i] != nums[i - 1]) {
                // Overwrite the element at writeIndex with the new unique element
               // Basically loop checking only unique element and then only copy to writeindex. only increment write index if unique (since in condition)
                nums[writeIndex] = nums[i];
                // Increment slow pointer
                writeIndex++;
            }
        }

        // writeIndex represents the number of unique elements
        return writeIndex;
    }
}
