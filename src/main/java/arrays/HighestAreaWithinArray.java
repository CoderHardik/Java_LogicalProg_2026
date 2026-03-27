/*
int A[]= {3,4,1,9,6,8,2};
Here start with first and last index 3 and 2, take minimum of them and multiple with distance between them
pos[3] = 0 , pos [2]= 6 so min of them and distance = min (3,2) x [6-0] = 2x6=12
Start = 0 and end = A.length 
start ++ and end -- till start == end
Increment start and decrement the end in the end
In logic area = min (A[Start], A[End]) x Math.abs[start-end]

if (area > max_area)
max_area = area

One thing to remember, if End > Start then start-- else end++

*/


public class HighestAreaWithinArray {
    
    public static void main(String[] args) {
        int A[]= {3,4,1,9,6,8,2};
        int max_area = higharea(A);
    }

    public static int higharea(int A[]){
        int start =0;
        int end = A.length-1;
        int area=0, max_area=0;
        while (start<end) {
            
            area = Math.min(A[start], A[end]) * (Math.abs(start-end));

            max_area= Math.max(area,max_area);
            if (A[end]<A[start]){
                end--;
            }
            else{
                start++;
            }
        }
        return max_area;



    }

}
