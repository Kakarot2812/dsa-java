import java.util.*;
/*
 * Problem: Shortest Path in Undirected Graph (Unweighted)
 *
 * Approach:
 * - Since the graph is unweighted (each edge has weight = 1),
 *   we use Breadth First Search (BFS).
 * - BFS explores nodes level by level.
 * - The first time we visit a node, we get the shortest distance.
 *
 * Steps:
 * 1. Build adjacency list from edge list.
 * 2. Initialize distance array with Integer.MAX_VALUE.
 * 3. Set source distance = 0.
 * 4. Perform BFS:
 *      - For each neighbour:
 *          if current_distance + 1 < neighbour_distance
 *              update distance
 *              push neighbour into queue
 * 5. Replace unreachable nodes with -1.
 *
 * Time Complexity:
 *      O(N + M)
 *      N = number of vertices
 *      M = number of edges
 *
 * Space Complexity:
 *      O(N + M)
 *      - Adjacency list
 *      - Distance array
 *      - Queue
 */

public class ShortestPathUndirected {
    public static int[] shortestpath(int[][] edge,int N,int M,int src){
         ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
         for(int i=0;i<N;i++){
             adj.add(new ArrayList<>());
         }
         for(int i=0;i<M;i++){
             adj.get(edge[i][0]).add(edge[i][1]);
             adj.get(edge[i][1]).add(edge[i][0]);
         }
         Queue<Integer> queue = new ArrayDeque<>();
         int[] dist = new int[N];
         Arrays.fill(dist,Integer.MAX_VALUE);
         dist[src] = 0;
         queue.add(src);
         while(!queue.isEmpty()){
             int node = queue.poll();
                 for (int neighbour : adj.get(node)) {
                     if(dist[node] + 1 < dist[neighbour]){
                         dist[neighbour] = dist[node] + 1;
                         queue.add(neighbour);
                     }
                 }
             }
         for(int i=0;i<N;i++){
             if(dist[i] == Integer.MAX_VALUE){
                 dist[i] = -1;
             }
         }
         return dist;
    }
}
