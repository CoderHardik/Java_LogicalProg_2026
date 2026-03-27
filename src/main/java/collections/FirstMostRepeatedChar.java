import java.util.LinkedHashMap;
import java.util.Map;

public class FirstMostRepeatedChar {

    public static void main (String args[]){

        char [] input = {'a','b','c','d','a','b','b'};
        firstmaxrepeated(input);

    }

    public static void firstmaxrepeated(char [] ch){

        LinkedHashMap<Character, Integer> lm = new LinkedHashMap<>();
        int max_repeat=0;
        char k='\0';
        for(char c: ch){
            if(lm.containsKey(c)){
                lm.put(c,(lm.get(c)+1));
                max_repeat= lm.get(c);
            }
            else{
                lm.put(c,1);
            }
        }

        for (Map.Entry<Character, Integer>m: lm.entrySet()){
            if (m.getValue()==max_repeat){
                k = m.getKey();
                break;
            }
            
        }
        System.out.println(k+" is your first max first repeated Character "+max_repeat);

    }
    
}
