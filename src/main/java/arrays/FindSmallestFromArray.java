package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Netflix program - given series has total num of episodes and array has list of watched episode. return lowest non watched
// watched = [1,3,5] and totalepisodes =5 then result should be 2
public class FindSmallestFromArray {

    public int findsmallest(int a[], int totalepisodes){

        Set <Integer> watched = new HashSet<>();
        for (int i: a){
            watched.add(i);
        }

        
        for (int start=0; start<totalepisodes; start++){
            if(!watched.contains(start)){
                return start;
            }
        }
        return -1;
    }
    
}
