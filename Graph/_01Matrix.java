import java.util.*;

/*
Problem: 01 Matrix
Platform: LeetCode 542
Concept: BFS (multi-source)
Difficulty: Medium
Time Complexity: O(m * n)
Space Complexity: O(m * n) for visited array and queue
Explanation:
- Initialize a queue with all 0-cells and mark them visited.
- Perform BFS from all 0s simultaneously.
- For each 1-cell reached, record its distance from the nearest 0.
- Return the resulting matrix with distances.
*/


public class _01Matrix {
    class Node{
        int row;
        int col;
        int count;

        Node(int row,int col,int count){
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }
        public int[][] updateMatrix(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            int[][] ans = new int[m][n];
            boolean[][] vis = new boolean[m][n];

            Queue<Node> queue = new LinkedList<>();

            for(int i = 0;i < m;i++){
                for(int j=0;j<n;j++){
                    if(mat[i][j] == 0){
                        queue.add(new Node(i,j,0));
                        vis[i][j] = true;
                    }
                }
            }
            int[] dr = {-1,0,1,0};
            int[] dc = {0,1,0,-1};

            while(!queue.isEmpty()){
                int r = queue.peek().row;
                int c = queue.peek().col;
                int steps = queue.peek().count;
                queue.remove();
                ans[r][c] = steps;

                for(int i=0;i<4;i++){
                    int nrow = r + dr[i];
                    int ncol = c + dc[i];
                    if(nrow>=0 && nrow<m && ncol>=0 && ncol<n && vis[nrow][ncol] == false && mat[nrow][ncol] == 1){
                        vis[nrow][ncol] = true;
                        queue.add(new Node(nrow,ncol,steps + 1));
                    }
                }
            }
            return ans;
        }
    }

