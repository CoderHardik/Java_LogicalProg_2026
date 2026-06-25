/*
Outer Loop (while (left < right)): Drives the two pointers from the outside edges toward the center until they meet.
Block 1 (while ... left++): Skips invalid symbols on the left to find the next valid character.
Block 2 (while ... right--): Skips invalid symbols on the right to find the next valid character.
Block 3 (if ... return false): Ignores capitalization and checks if the two active characters match.
Block 4 (left++; right--;): Steps inward to set up the next pair of characters for comparison.
Final Line (return true): Confirms the string is a palindrome after all pairs match successfully.
*/

package strings;

public class ValidPalindromString {



    public boolean isPalindrome(String s) {
        // Initialize pointers at the very beginning and end
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // 1. Move left pointer forward if it's not a letter or digit
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // 2. Move right pointer backward if it's not a letter or digit
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // 3. Compare characters after converting to lowercase
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false; // Mismatch found, not a palindrome
            }

            // 4. Move both pointers inward for the next comparison
            left++;
            right--;
        }
        
        return true; // All valid characters matched
    }
}

    

