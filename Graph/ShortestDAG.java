import java.util.*;
/*
    Shortest Path in a Directed Acyclic Graph (DAG)
    ------------------------------------------------
    Approach:
    1. Build adjacency list with (node, weight) pairs.
    2. Perform Topological Sort using DFS.
    3. Initialize distance array:
       - Source (0) = 0
       - Others = Infinity
    4. Process nodes in topological order.
    5. Relax edges only if current node is reachable.
    6. Convert unreachable nodes to -1.

    Time Complexity: O(N + M)
    Space Complexity: O(N + M)

    Where:
    n = Number of vertices
    m = Number of edges
*/
class Node{
    int first;
    int second;
    public Node(int first,int second){
        this.first = first;
        this.second = second;
    }
}
public class ShortestDAG {
    public static int[] shortestPath(int n,int m,int[][] edges){
        ArrayList<ArrayList<Node>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<Node>());
        }

        for(int i=0;i<m;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new Node(v,wt));
        }

        boolean[] vis = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++) {
            if (!vis[i]) {
                dfs(i, adj, vis, stack);
            }
        }
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = 0;
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if (dist[node] != Integer.MAX_VALUE) {
                for (int i = 0; i < adj.get(node).size(); i++) {
                    int v = adj.get(node).get(i).first;
                    int wt = adj.get(node).get(i).second;
                    if (dist[node] + wt < dist[v]) {
                        dist[v] = dist[node] + wt;
                    }
                }
            }
        }
        for(int i = 0; i < n; i++){
            if(dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }
        return dist;
    }
    private static void dfs(int node ,ArrayList<ArrayList<Node>> adj,boolean[] vis,Stack<Integer> stack){
        vis[node] = true;
        for(int i=0;i < adj.get(node).size();i++){
            int v = adj.get(node).get(i).first;
            if(!vis[v]){
                dfs(v,adj,vis,stack);
            }
        }
        stack.add(node);
    }
}
