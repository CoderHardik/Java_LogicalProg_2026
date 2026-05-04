/*
Given [[1,3],[2,6],[8,10],[15,18]] give output with merge interval [[1,6],[8,10],[15,18]]

Solution:
- sort array first so you see array in linear fashion
Remember - Arrays.sort(a, (i,j)->Integer.compare(i[0], j[0]));
- Check first element of next is less than or equal to last element of previous
(current[1]>=next[0])

*/
public package collections;

import java.util.*;

class MergeIntervals {

    //[[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
    public static List<int []> merge(int a[] []){

        Arrays.sort(a, (i,j)->Integer.compare(i[0], j[0]));

        List<int []> result = new ArrayList<>();
        int current[] = a[0];

        for(int next[]: a){// a is 2D so next is 1D array
           // [[1,3],[2,6] -> 3>2 -> [1,6]
            if(current[1]>=next[0]){// if current end overlap with next start update current end to max
                current[1]=Math.max(current[1],next[1]);
            }
            else{
                current=next; // if no overlap then move to next
                result.add(current);
            } 
        }
        return result;
    }
}
