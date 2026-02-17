import java.util.*;


/*
Problem: Rotting Oranges
Platform: LeetCode 994
Concept: BFS, Grid Traversal
Difficulty: Medium
Time Complexity: O(m * n)
Space Complexity: O(m * n) for visited array and queue
Explanation:
- Traverse the grid to enqueue all initially rotten oranges (2) with time = 0.
- Keep count of fresh oranges (1).
- Perform BFS level by level.
- For each rotten orange, infect adjacent fresh oranges and increment time.
- If all fresh oranges are infected, return total time; otherwise, return -1.
*/


public class RottenOranges {
    class Pair{
        int row;
        int col;
        int tm;

        public Pair(int row,int col,int tm){
            this.row = row;
            this.col = col;
            this.tm = tm;
        }
    }
        public int orangesRotting(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            Queue<Pair> queue = new LinkedList<>();
            boolean[][] vis = new boolean[m][n];
            int countf=0;

            for(int i =0;i<m;i++){
                for(int j= 0;j<n;j++){
                    if(grid[i][j] == 2){
                        vis[i][j] = true;
                        queue.add(new Pair(i,j,0));
                    }
                    if(grid[i][j] == 1){
                        countf++;
                    }
                }
            }

            int count = 0;
            int time = 0;
            int[] dr = {-1,0,1,0};
            int[] dc = {0,1,0,-1};
            while(!queue.isEmpty()){
                int r = queue.peek().row;
                int c = queue.peek().col;
                int t = queue.peek().tm;
                queue.remove();
                time  = Math.max(time,t);

                for(int i = 0;i< 4;i++){
                    int nrow = r + dr[i];
                    int ncol = c + dc[i];
                    if(nrow>=0 && nrow<m && ncol>=0 && ncol<n && vis[nrow][ncol] == false && grid[nrow][ncol] == 1){
                        count++;
                        vis[nrow][ncol] = true;
                        queue.add(new Pair(nrow,ncol,t+1));
                    }
                }
            }
            if(count != countf){
                return -1;
            }
            return time;
        }
    }


