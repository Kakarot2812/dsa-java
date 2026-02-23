import java.util.*;
/*
Problem: Minimum Effort Path (LeetCode)

Approach:
- This problem is solved using Dijkstra’s Algorithm (Min-Heap).
- Instead of minimizing total path sum, we minimize the maximum absolute
  height difference between adjacent cells along the path.
- For each move, effort is calculated as:
      max(previous_effort, abs(height_current - height_next))
- Use a priority queue to always process the cell with minimum effort first.
- Maintain a distance matrix to store minimum effort required to reach each cell.
- Stop early when bottom-right cell is reached.

Time Complexity:
    O((m * n) log (m * n))
    Each cell can be pushed into the priority queue and heap operations
    take log(m*n).

Space Complexity:
    O(m * n)
    For distance matrix and priority queue.

Key Concept:
    Modified Dijkstra (Min-Max path problem)
*/

class minEffort{
    int row;
    int col;
    int diff;
    public minEffort(int row,int col,int diff){
        this.row = row;
        this.col = col;
        this.diff = diff;
    }
}
public class MinimumEffortPath {
    
        public int minimumEffortPath(int[][] heights) {
            PriorityQueue<minEffort> pq = new PriorityQueue<>(
                    (x,y) -> x.diff - y.diff
            );
            int m = heights.length;
            int n = heights[0].length;

            int[][] dis = new int[m][n];
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    dis[i][j] = Integer.MAX_VALUE;
                }
            }
            dis[0][0] = 0;
            pq.add(new minEffort(0,0,0));

            while(!pq.isEmpty()){
                minEffort cell = pq.poll();
                int row = cell.row;
                int col = cell.col;
                int diff = cell.diff;

                if(diff > dis[row][col]) continue;

                if(row == m-1 && col == n-1){
                    return diff;
                }

                int[] dr = {-1,0,1,0};
                int[] dc = {0,1,0,-1};

                for(int i=0;i<4;i++){
                    int nrow = row + dr[i];
                    int ncol = col + dc[i];

                    if(nrow>=0 && nrow<m && ncol>=0 && ncol<n){
                        int ndiff= Math.max(Math.abs(heights[row][col]
                                - heights[nrow][ncol]),diff);
                        if(ndiff < dis[nrow][ncol]){
                            dis[nrow][ncol] = ndiff;
                            pq.add(new minEffort(nrow,ncol,ndiff));
                        }
                    }
                }
            }
            return 0;
        }
    }

