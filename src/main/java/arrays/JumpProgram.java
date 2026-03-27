public class JumpProgram {

    public static void main(String[] args) {
        int A [] = {2,3,1,1,4};
        
    }

    public static boolean jumpGame(int A[]){
        int farthest=0;

        for(int i=0; i<A.length-1; i++)
        {   // If the current index is greater than the farthest we can reach, we're stuck
            if (farthest < i) return false;

            // Update the farthest point reachable - calculate based on at element i you can jump A[i]. you can only win if i+A[i] reaches to end.
            farthest = Math.max(farthest, i+A[i]);
            if (farthest >= A.length-1) return true;
        }
        return false;
    }
}
