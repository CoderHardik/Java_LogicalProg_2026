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

    

