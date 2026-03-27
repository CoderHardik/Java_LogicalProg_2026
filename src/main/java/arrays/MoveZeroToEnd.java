
public class MoveZeroToEnd{

  public static void main(String [] args){

    int a[] ={1,2,0,4,5};
    int res[] = movezero(a);
    for(int x: res){
      System.out.println(x);
    }
  }

  public static int [] movezero(int A[]){
    int counter = 0;
    int temp=0;
   for (int i = 0; i < A.length; i++){
      if (A[i]!=0){
        temp = A[i];
        A[i]=A[counter];
        A[counter]=temp;
        counter++ ;
      }
    }
    return A;
  }

}