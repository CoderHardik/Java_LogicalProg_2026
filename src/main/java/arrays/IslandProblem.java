
    import java.util.*;
/*
Think of this program as a search and rescue mission on a map. 
The goal is to count how many distinct "islands" (groups of '1's) exist in a sea of '0's.

1. The Walkthrough (The Nested Loops)
The program starts at the top-left corner of the grid and looks at every single cell one by one (like reading a book).
If it sees water ('0'), it keeps moving.
If it sees land ('1'), it stops and asks: "Have I already counted this piece of land?"

2. Founding an Island (islandCount++)
If the program finds land that it hasn't visited before, it knows it has discovered a brand new island. It taps a counter to mark +1.
But there’s a catch: an island can be many cells wide. If the program just kept moving to the next cell, it might count the same island multiple times. 
To avoid this, it needs to "claim" the entire island at once.

3. The "Infection" Scan (The BFS)
Once a new island is found, the bfs (Breadth-First Search) method kicks in. You can think of this like pouring ink on that one spot of land:
The ink spreads to all connected land ('1's) nearby—up, down, left, and right.
Every cell the ink touches is added to a visited set.
The visited set acts like a "No Trespassing" sign. When the main walkthrough (Step 1) continues later, 
it will see those signs and know not to count those cells as new islands.

Summary
Loop through every cell.
Found a new '1'? That's an island! Increment your count.
Spread out from that '1' to find all its neighbors and mark them as "already seen."
Repeat until the whole map is checked.
 */


public class IslandProblem {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int islandCount = 0;
        
        // Keep track of visited nodes in a set as requested
        // Using a String "r,c" as the key for simplicity in Java
        Set<String> visited = new HashSet<>();

        // Loop over each element
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If it's land ('1') and hasn't been visited yet
                if (grid[r][c] == '1' && !visited.contains(r + "," + c)) {
                    islandCount++;
                    // BFS to visit the entire island
                    bfs(grid, r, c, visited);
                }
            }
        }
        return islandCount;
    }

    private void bfs(char[][] grid, int startR, int startC, Set<String> visited) {
        /*
        In Java, Queue works exactly like a line at a coffee shop:
        First in, First out (FIFO): The first person to join the line is the first one served.
        queue.offer(): This "adds" a coordinate to the back of the line.
        queue.poll(): This "takes" the person at the very front of the line so you can work on them.
         */
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        visited.add(startR + "," + startC);
        // above will initially check if there is land than in for loop we will add all land around it.
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            //code that actually "takes a step" to check the neighboring cells. -for current (2,2) check dir (1.0)(UP) -> (3,2) and so on
            for (int[] dir : directions) {
                int nr = curr[0] + dir[0];
                int nc = curr[1] + dir[1];

                // Check bounds, if it's land, and if not visited
                if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length 
                    && grid[nr][nc] == '1' && !visited.contains(nr + "," + nc)) {
                    
                    queue.offer(new int[]{nr, nc});
                    visited.add(nr + "," + nc);
                }
            }
        }
    }

    public static void main(String[] args) {
        IslandCounter solver = new IslandCounter();
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        }; // this is char array, '1' is char. 1 is int. If we create int array, it will use more space so char [][] more space effiecient
        System.out.println("Total Islands: " + solver.numIslands(grid)); // Output: 3
    }
}

    
}
