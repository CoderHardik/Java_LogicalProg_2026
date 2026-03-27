import java.util.*;
public class InterSectionBetweenTwoArrays {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 1};
        int[] arr2 = {2, 2};
        System.out.println(findintersection(arr1, arr2));
    }
    
    public static List<Integer> findintersection(int A1[], int A2[]){

        HashMap<Integer, Integer> hm = new HashMap<>();
        ArrayList<Integer> al = new ArrayList<>();

        for (int i: A1){
            hm.put(i,hm.getOrDefault(i,0)+1); // getOrDefault way: "Check the 'Red' jar. If it's missing, pretend it has a 0 in it. Now add 1 to whatever that number is and update the jar."
        }

        for (int j: A2){
            if(hm.containsKey(j)&& hm.get(j)>0){
                al.add(j);
                hm.put(j,hm.get(j)-1); // remember that this is how we reduce count
            }
        }
        return al;
    }

}
