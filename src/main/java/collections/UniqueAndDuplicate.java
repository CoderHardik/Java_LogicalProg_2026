import java.util.HashMap;
import java.util.Map;
public class UniqueAndDuplicate {

    public static void main(String[] args) {
        char input[]= {'a','b','c','d','a','b'};

        HashMap <Character, Integer> hm = new HashMap<>();

        for (Character c: input){
            if (hm.containsKey(c)){
                hm.put(c, hm.get(c)+1);  // replace whole if else with single line hm.put (c, hm.getOrDefault(c, 0) + 1);
            }
            else{
                hm.put(c,1);  
            }

            
        }

        for (Map.Entry<Character,Integer> m : hm.entrySet()){

                if (m.getValue()>1){
                    System.out.println(m.getKey()+" is duplicated " +m.getValue());
                }
                else{
                    System.out.println(m.getKey()+" is unique ");
                }
            }
    }
    
}
