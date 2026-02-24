import java.util.*;
/*
 * Bellman-Ford Algorithm
 *
 * Purpose:
 * Finds the shortest path from a source vertex to all other vertices
 * in a weighted directed graph. Works with negative edge weights.
 *
 * Steps:
 * 1. Initialize all distances as infinity except source (0).
 * 2. Relax all edges V-1 times.
 * 3. Perform one more relaxation to detect negative weight cycles.
 *
 * If a negative cycle is detected, returns {-1}.
 *
 * Time Complexity: O(V * E)
 * Space Complexity: O(V)
 */

public class BellmanFord {
    public static int[] bellmanFord(int V,ArrayList<ArrayList<Integer>> adj,int src){
        int[] dis = new int[V];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[src] = 0;

        for(int i=0;i<V-1;i++){
            for(ArrayList<Integer> it : adj){
                int u = it.get(0);
                int v = it.get(1);
                int wt = it.get(2);
                if(dis[u] != Integer.MAX_VALUE && dis[u] + wt < dis[v]){
                    dis[v] = dis[u] + wt;
                }
            }
        }
        for(ArrayList<Integer> it : adj){
            int u = it.get(0);
            int v = it.get(1);
            int wt = it.get(2);
            if(dis[u] != Integer.MAX_VALUE && dis[u] + wt < dis[v]){
                return new int[]{-1};
            }
        }
        return dis;
    }
}
