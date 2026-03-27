public class ReverseNum {
  public static void main(String[] args){
    int i = 123;
    System.out.println(revnum(i));
  }

  public static int revnum(int i){
    int temp=0;
    while(i>0){
    temp = (temp*10) + i%10;
    i = i/10;
  }
  return temp;
}
}