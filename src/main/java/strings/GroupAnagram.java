import java.util.*;

/*
"Given an array of strings, group the anagrams together and return them as a list of sublists." 

An anagram is defined as a word or phrase formed by rearranging the letters of another, using all the original letters exactly once. 

Example Interaction
Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
Output: [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]
Clarification: Interviewers often specify that you can return the answer in any order

1. Initialize a Hash Map
Create a HashMap<String, List<String>> to store groups. 
The sorted string serves as a "signature" that is identical for all words in an anagram group. 


2. Process Each String
Iterate through the input array. For each string: 

Convert it to a char[] and use Arrays.sort() to normalize it.
Convert the sorted array back into a String to use as the map key. 

3. Group and Return
Check if the key exists in the map. If not, create a new ArrayList. Add the original (unsorted) string to the list. 
Finally, return map.values() wrapped in a new ArrayList. 

Complexity Analysis
Time Complexity: , where 
 is the number of strings and 
 is the maximum length of a string. This accounts for iterating through 
 strings and sorting each one.

** Example dry run for section 3 which is key: **

Step 1: Processing "eat"
The Key: The code sorts "eat" to get the key "aet".
The if check: map.containsKey("aet") is false.
Inside the if: It puts an empty list into the map for that key.
Map status: {"aet": []}
The add line: map.get("aet").add("eat")
Map status: {"aet": ["eat"]}
Step 2: Processing "tea"
The Key: The code sorts "tea" to get the key "aet".
The if check: map.containsKey("aet") is now true.
Inside the if: It skips this block (it doesn't need to create a new list because one already exists).
The add line: map.get("aet").add("tea")
Map status: {"aet": ["eat", "tea"]}

*/

public class GroupAnagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        // If the input is null or empty, return an empty list
        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        // Map to store sorted string as key and list of anagrams as value
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            // 1. Convert string to char array and sort it
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            
            // 2. Use the sorted string as the unique key. value of create string. ex If your charArray contains the characters ['h', 'e', 'l', 'l', 'o'], running this line of code will join them together and store the word "hello" inside the key variable.
            String key = String.valueOf(charArray);
            
            // 3. Add original string to the corresponding list in the map
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        
        // Return all grouped anagrams as a list of lists
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        AnagramGrouper grouper = new AnagramGrouper();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(grouper.groupAnagrams(input));
    }
}
