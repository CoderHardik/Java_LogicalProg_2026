/*
Problem Statement:You are given an array of meeting time intervals intervals where each interval intervals[i] = [start_i, end_i] represents a meeting's start and end time. 
Your goal is to return the minimum number of conference rooms required to hold all meetings without any conflicts.
Example 1: Basic OverlapInput: intervals = [[0, 30], [5, 10], [15, 20]]
Output: 2

Explanation:The first meeting [0, 30] is ongoing when the second meeting [5, 10] starts, 
requiring a second room.When the third meeting [15, 20] starts, the second meeting has already ended (at time 10), 
so you can reuse its room.At peak usage (e.g., between time 5 and 10), 2 rooms are active simultaneously.


Strategy:
1. Sort all start times in one array.
2. Sort all end times in another array.
3. Use two pointers (one for starts, one for ends).
- If a meeting starts before the earliest one ends, you need a new room (count++).
- If a meeting starts after (or at the same time) a meeting ends, you can reuse a room (move the end pointer forward).

Code walk through:

Intervals: [0, 30], [5, 10], [15, 20]
Sorted Starts: [0, 5, 15]
Sorted Ends: [10, 20, 30]
Guest 1 arrives at 0: Earliest checkout is 10. 0 < 10. Need Room 1.
Guest 2 arrives at 5: Earliest checkout is still 10. 5 < 10. Need Room 2.
Guest 3 arrives at 15: Earliest checkout is 10. 15 > 10. Reuse a room! (Move endPtr to 20).

*/

public int minMeetingRooms(int[][] intervals) {
    int n = intervals.length;
    int[] starts = new int[n];
    int[] ends = new int[n];

    for (int i = 0; i < n; i++) {
        starts[i] = intervals[i][0];
        ends[i] = intervals[i][1];
    }

    Arrays.sort(starts);
    Arrays.sort(ends);

    int rooms = 0;
    int endPtr = 0;

    for (int i = 0; i < n; i++) {
        // If a meeting starts before the oldest one ends, we need a room
        if (starts[i] < ends[endPtr]) {
            rooms++;
        } else {
            // Otherwise, a room freed up, so we don't increment rooms
            // Just move the end pointer to the next meeting that will finish
            endPtr++;
        }
    }
    return rooms;
}
