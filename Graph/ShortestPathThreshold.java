import java.util.*;
/*
 * Problem: Find the city with the smallest number of neighbors
 * reachable within a given distance threshold.
 *
 * Approach:
 * - Use Floyd–Warshall algorithm to compute shortest distance
 *   between all pairs of cities.
 * - Build a distance matrix initialized with Integer.MAX_VALUE.
 * - Set self-distance to 0.
 * - Relax all pairs through intermediate nodes.
 * - For each city, count how many cities are reachable within
 *   the threshold distance.
 * - Return the city with the minimum reachable count.
 * - If multiple cities have the same count, return the city
 *   with the greatest index (handled using <= condition).
 *
 * Time Complexity: O(n^3)
 * Space Complexity: O(n^2)
 */
class ShortestPathThershold {
    public int findTheCity(int n, int[][] edges, int threshold) {
        int m = edges.length;
        int[][] dis = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            dis[u][v] = wt;
            dis[v][u] = wt;
        }

        for (int i = 0; i < n; i++) {
            dis[i][i] = 0;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dis[i][k] == Integer.MAX_VALUE ||
                            dis[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] +
                            dis[k][j]);
                }
            }
        }

        int countCity = n;
        int cityNo = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dis[i][j] <= threshold) {
                    count++;
                }
            }
            if (count <= countCity) {
                countCity = count;
                cityNo = i;
            }
        }
        return cityNo;
    }
}

