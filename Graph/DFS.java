import java.util.ArrayList;

/**
 * DFS traversal for a connected graph.
 * Given a graph represented as an adjacency list, this class
 * performs Depth First Search starting from node 0.
 */

public class DFS {
    ArrayList<Integer> DFS(int n,ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[n];
        ArrayList<Integer> ans = new ArrayList<>();

        dfs(0,visited,adj,ans);
        return ans;
    }

    /**
     * Recursive DFS helper function.
     *
     * @param node    Current node
     * @param visited Array tracking visited nodes
     * @param adj     Adjacency list
     * @param ans     DFS traversal result list
     */

    public static void dfs(int node,boolean[] visited,ArrayList<ArrayList<Integer>> adj,ArrayList<Integer> ans){
        visited[node] = true;
        ans.add(node);

        for(int neighbour : adj.get(node)){
            if(!visited[neighbour]){
                dfs(neighbour,visited,adj,ans);
            }
        }
    }
}
