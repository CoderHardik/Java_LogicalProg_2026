/*
Assume that you have an array given to you, arr1=[‘a’, ‘b’, ‘c’, ‘d’, ‘t’].. 
Now you let user enter a string and validate that string they enter is made from characters we have in array. 
Show it is valid if it consist all words and say it is not if it is not..

I got 2 user string to test..
St1 = cat;   \\ valid string
st2= add;  \\invalid string since ‘d’ is repeated

- Create hashmap and add given array to it - map <Character, Integer>
- Create Char array from given string
- interate over array and check if that character is in map
- If it is in map, then count++ and remove character from map
-
*/

import java.util.*;

public class UserWordFromGivenArray {
    public static void main(String[] args) {
        char arr1[] = {'a', 'b', 'c', 'd', 't'};
        String input = "tad";
        checkUserWordFromArray(arr1, input);

    }

    public static void checkUserWordFromArray(char ch1[], String s){
        char ch[] = s.toCharArray();
        ArrayList<Character> al = new ArrayList<>();
        int count=0;
        
        // Create ArraList from Given master Array
        for (char c:ch1){
            al.add(c);
        }
        //Check if given String is in Arraylist, if so increment count and remove that element from arraylist to avoid detecting same character again
        for (char c:ch){
            if (al.contains(c)){
                count++;
                al.remove((Character) c);
            }
        }
        // Check if count is same as user string then your string is in master array. Also handle edge case
        if (ch.length<1){
            System.out.println("Array is invalid");
            return;
        }
        
        if (count==ch.length){
            System.out.println("Your word belongs to master Array");
        }
        else
        {
            System.out.println("Your word does not belong to master Array");
        }
    }
    
}
