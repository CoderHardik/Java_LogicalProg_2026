public class MoveEvenFront{
  public static void main(String[] args) {
    int A []= {1,2,3,4,5,6};
    int res[] = movefront(A);
    for (int a: res){
      System.out.println(a);
    }
  }

  public static int[] movefront(int A[]){
    int counter=0;
    int temp=0;
    for(int i=0; i<A.length; i++){
      if(A[i]%2==0){
        temp = A[i];
        A[i]=A[counter];
        A[counter]=temp;
        counter++;
      }
    }
    return A;
  }
}