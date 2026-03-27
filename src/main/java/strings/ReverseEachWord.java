public class ReverseEachWord{
  public static void main(String[] args){
    String s= "This is test";
    System.out.println(reverseword(s));
  }

  public static String reverseword(String s){
    String st[] = s.split(" ");
    StringBuilder sb = new StringBuilder();
    for (String sc: st){
      for (int i =sc.length()-1; i>=0; i--){
        sb.append(sc.charAt(i));
      }
      sb.append(" ");
    }//end of for
  return sb.toString();
}//end of method

}