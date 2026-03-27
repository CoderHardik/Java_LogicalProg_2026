import java.util.*;
public class FindUniqueCharSet {

    public static void main(String[] args) {
        char ch[]= {'a','b','c','d','a','b'};
         HashSet<Character> hs = uniqueCharSet(ch);
         for(char c: hs){
            System.out.println(c);
        }
    }

    public static HashSet<Character> uniqueCharSet(char ch[]){
        HashSet <Character> hs = new HashSet<>();

        for(char c: ch){
            hs.add(c);
        }

        return hs;
    }
    
}
