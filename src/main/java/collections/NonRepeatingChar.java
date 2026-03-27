package collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class NonRepeatingChar {
    public static void main(String[] args) {
    nonrepeat("swiss");
  }

  public static void nonrepeat(String s){

    Map<Character, Integer> hm = new LinkedHashMap<>();
    for (Character c: s.toCharArray()){
      hm.put(c, hm.getOrDefault(c,0)+1);
    }

    for (Map.Entry<Character, Integer> me: hm.entrySet()){
      if(me.getValue()==1){
        System.out.println(me.getKey());
        break;
      }
    }
  }//end of main
}
