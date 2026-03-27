/*Eg. 
I/P : [1, 2, 4, 4, 1, 3, 7, 5, 5, 2]
O/P : 4
Following program is aimed to have o/p 4
Observe that here 1 is also repeating and first element of 1 is appearing before 4
but repeating element of 4 is appearing first

*/

import java.util.ArrayList;
public class FirstDuplicate {

    public static void main(String[] args) {
        int a [] = {1, 2, 4, 4, 1, 3, 7, 5, 5, 2};
        System.out.println(findfirstdup(a));
    }

    public static int findfirstdup(int a[]){
        ArrayList <Integer> dupedetect = new ArrayList<>();
        int dup=0;
        for (int i:a){
            if(dupedetect.contains(i)){
                dup=i;
                break;
            }
            else {
                dupedetect.add(i);
            }
        }//for
        return dup;

    }//method
    
}//class



/* Use following instead of above as set is effiecient for search than list

 Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new LinkedHashSet<>(); // Using LinkedHashSet to keep order

        for (int id : events) {
            // .add returns false if the element is already in the set
            if (!seen.add(id)) {
                duplicates.add(id);
            }
        }

        */