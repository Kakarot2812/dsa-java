import java.util.*;

public class NumberOfIslands {

    static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] vis = new boolean[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j] && grid[i][j] == '1') {
                    count++;
                    bfs(i, j, vis, grid);
                }
            }
        }
        return count;
    }

    private void bfs(int row, int col, boolean[][] vis, char[][] grid) {

        Queue<Pair> queue = new LinkedList<>();
        vis[row][col] = true;
        queue.add(new Pair(row, col));

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();

            for (int i = 0; i < 4; i++) {

                int nrow = curr.first + dr[i];
                int ncol = curr.second + dc[i];

                if (nrow >= 0 && nrow < grid.length &&
                        ncol >= 0 && ncol < grid[0].length &&
                        !vis[nrow][ncol] &&
                        grid[nrow][ncol] == '1') {

                    vis[nrow][ncol] = true;
                    queue.add(new Pair(nrow, ncol));
                }
            }
        }
    }
}
