public class StringSequenceCheck{

  public static void main(String[] args) {    
    System.out.println(stringcheck("abc", "def", "abcdef"));
  }

  public static boolean stringcheck(String A, String B, String C){

    if (C.length() != A.length()+B.length()) return false;

    int i=0, j=0;
    for (char ch: C.toCharArray()){
      if(i<A.length() && ch==A.charAt(i)){
        i++;
      }
      else if (j<B.length() && ch==B.charAt(j)){
        j++;
      }
      else{
        return false;
      }
    }
    return true;
  }
}