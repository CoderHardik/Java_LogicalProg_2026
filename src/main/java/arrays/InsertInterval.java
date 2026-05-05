/*
Question:
You have a list of sorted, non-overlapping intervals representing time slots. 
Your task is to insert a new time slot into this list. If the new slot overlaps with any existing ones, you must merge them so the final list remains sorted and non-overlapping.

Example Scenario (The "Merge" Case)Input: intervals = [[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]], newInterval = [4, 8]
Analysis: The new interval [4, 8] overlaps with [3, 5], [6, 7], and [8, 10].Output: [[1, 2], [3, 10], [12, 16]]
The "Three-Phase" StrategyInterviewers look for an \(O(N)\) time complexity solution where you traverse the list once:
1. Left Side: Add all intervals that end before the new interval starts.
2. Middle (Merge): While intervals overlap, update the newInterval by taking the min of starts and max of ends.
3. Right Side: Add the merged newInterval, then add all remaining intervals that start after it ends

Refined Logic (The 3-Step Pass)Instead of collecting all overlapping intervals into a temporary array, you can update the newInterval in place as you iterate.
Left Side (Before Overlap):Condition: current[end] < new[start]
Action: These don't touch the new interval. Add them directly to result.

Middle (The Merge):Condition: current[start] <= new[end] (This covers all overlap cases: your start/end/start-in-middle scenarios).
Action: Update the newInterval:new[start] = min(new[start], current[start])new[end] = max(new[end], current[end])
Only add the final newInterval to result once this loop finishes.

Right Side (After Overlap):Condition: current[start] > new[end]Action: Add all remaining intervals to result.
*/


import java.util.*;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 1. LEFT PHASE: Add all intervals that end before the new interval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2. MERGE PHASE: Merge all overlapping intervals
        // An interval overlaps if its start is less than or equal to the newInterval's end
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Add the final merged interval
        result.add(newInterval);

        // 3. RIGHT PHASE: Add all remaining intervals that start after the merged interval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        // Convert the list back to a 2D array for the output
        return result.toArray(new int[result.size()][]);
    }
}
