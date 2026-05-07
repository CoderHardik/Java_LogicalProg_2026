/*
Problem statement:
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order

input = [1,1,1,2,2,3], k = 2
output = [1, 2] -> 1 appears 3 times, 2 appears twice.

1. Count Frequencies (The HashMap)First, you need to know how many times each number appears. 
Iterate through the array and store the counts in a HashMap (or Dictionary).
Input: [1,1,1,2,2,3]
Map: {1: 3, 2: 2, 3: 1}
2. Maintain a Min-Heap of size \(K\)This is the "clever" part. We use a Min-Heap based on the frequency (not the number itself).
Iterate through the keys in your HashMap.
Push each number into the Min-Heap.The Rule: If the heap size exceeds \(k\), pop the top element.
Why a Min-Heap? In a Min-Heap, the smallest value is always at the top. 
By popping it, you are constantly discarding the least frequent elements, leaving only the \(k\) most frequent ones inside.

3. Collect the ResultsOnce you’ve processed all the keys from the Map, the \(k\) elements remaining in the heap are your "winners." 
Extract them into a list and return them.

Example Walkthrough
(\(k=2\))Map: {1: 3, 2: 2, 3: 1}
Add 1 (freq 3): Heap = [(3, 1)]
Add 2 (freq 2): Heap = [(2, 2), (3, 1)]
Add 3 (freq 1): Heap = [(1, 3), (2, 2), (3, 1)]. Size is 3, which is > k.
Pop! The Min-Heap kicks out the smallest frequency: (1, 3).
Result: The heap contains (2, 2) and (3, 1). 
The top elements are 2 and 1.

PriorityQueue<Integer> heap = new PriorityQueue<>(
            (n1, n2) -> count.get(n1) - count.get(n2)
Explaination of above code:
PriorityQueue<Integer>: This creates the heap.
By default, Java's PriorityQueue is a Min-Heap, meaning it wants to keep the "smallest" item at the very top.

(n1, n2) -> ...: This is a Lambda Expression (a shortcut for a Comparator). Since we are storing numbers (Integer), but want to sort them by their frequency, we have to tell the heap how to compare two numbers (n1 and n2).

count.get(n1) - count.get(n2): This tells Java: "Compare these two numbers by looking up their counts in our HashMap.
"If count(n1) is smaller than count(n2), the result is negative, and n1 stays higher up (closer to being kicked out).
This ensures the element with the lowest frequency is always at the "head" (top) of the queue. 

 for (int n : count.keySet()) {
            heap.add(n
Explaination of above code section:
This loop is the "engine" of the program. It acts like a filter that ensures only the "strongest" (most frequent) elements survive.
Here is the step-by-step breakdown:
1. for (int n : count.keySet())
The count map contains every unique number and its frequency (e.g., {10: 5 times, 20: 1 time}). keySet() tells the loop to look at each unique number one by one.
2. heap.add(n);
You take the current number and toss it into the heap. The heap immediately uses that Comparator we wrote earlier to look up the number's frequency in the map and bubble it to the correct position.
3. if (heap.size() > k)
This is your capacity check. If you only want the "Top 2" elements, but your heap just grew to 3 elements, you have one too many.
4. heap.poll();
Since this is a Min-Heap, the poll() method always grabs the element at the very top—which is the one with the lowest frequency currently in the heap.
By removing the "weakest" element every time you go over size \(K\), you are guaranteed that at the end of the loop, the \(K\) elements left behind are the ones with the highest counts.

Visual Dry RunImagine nums = [1,1,1, 2,2, 3] and \(k=2\).
Your Map is {1: 3, 2: 2, 3: 1}.
Process 1: Add to heap.Heap: [1] (size 1, ok)
Process 2: Add to heap.Heap: [2, 1] (size 2, ok. Note: 2 is at the top because frequency 2 < frequency 3)
Process 3: Add to heap.Heap: [3, 2, 1] (size 3, Too big!)
The Poll: heap.poll() looks at frequencies. Frequency of 3 is the smallest (only 1).3 is kicked out.Heap: [2, 1]            
*/


import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Build frequency map (O(N))
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // 2. Initialize a Min-Heap (O(N log K))
        // We compare elements based on their frequency stored in the map
        PriorityQueue<Integer> heap = new PriorityQueue<>(
            (n1, n2) -> count.get(n1) - count.get(n2)
        );

        // 3. Keep only the k most frequent elements in the heap
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll(); // Remove the element with the lowest frequency
            }
        }

        // 4. Build output array (O(K log K))
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }
}
