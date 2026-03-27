import java.util.*;

public class ReverseSubsetOfArray {

    public static void main(String[] args) {
        
    }

    public static List<Integer> revsubset(int A[], int n){

        if (A.length % n != 0) {
        System.out.println("not a valid N");
        return new ArrayList<>(); // Return empty list instead of 'break'
    }

        int block = A.length/n; // size of each block 
        int reverse_counter = 1; // pointer that will move
        int m=1; //current segment

        List<Integer> al = new ArrayList<>();
        
        while(m<=block){// when current segment reaches max segment then terminate

            if(reverse_counter<=n){
                al.add(m*n-reverse_counter);
                reverse_counter++;
            }
            else{
                m++;
                reverse_counter=1;
            }
        }
        return al;
    }
    
}
