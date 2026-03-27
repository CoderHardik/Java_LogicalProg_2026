package collections;

import java.util.*;
import java.util.stream.Collectors;

public class TopTwoElements {

public static void main(String[] args) {
    int a[]  = {1,1,1,2,2,3};

    int k = 2;
    twoelement(a, k);
}
    public static void twoelement(int a[], int k){

        Map<Integer, Integer> hm = new HashMap<>();
        for (int i:a){
            hm.put(i, hm.getOrDefault(i, 0)+1);
        }

        Map<Integer, Integer> result = hm.entrySet()
                                        .stream()
                                        .sorted(Map.Entry.comparingByValue())
                                        .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (e1,e2)->e1,
                                        LinkedHashMap::new));


        // Assume k is show top k result

        int counter=0; 
        for(Map.Entry<Integer, Integer>me : result.entrySet()){
            if(counter<k){
                System.out.println(me.getValue());
            }
            counter++;
        }


    }
    
}
