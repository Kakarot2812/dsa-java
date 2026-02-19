import java.util.ArrayList;
import java.util.Stack;
/*
 * Performs Topological Sort using DFS.
 *
 * Time Complexity: O(V + E)
 *  - Each vertex is visited once.
 *  - Each edge is explored once.
 *
 * Space Complexity: O(V)
 *  - Visited array takes O(V).
 *  - Recursion stack can take O(V) in worst case.
 *  - Stack to store topological order takes O(V).
 *
 * Note:
 *  - Works only for Directed Acyclic Graph (DAG).
 */
public class Topological {
    public static int[] topological(int n, ArrayList<ArrayList<Integer>> adj){
        boolean[] vis = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<n;i++){
            if(!vis[i]){
                dfs(i,vis,stack,adj);
            }
        }
        int [] ans = new int[n];
        int i=0;
        while(!stack.isEmpty()){
            ans[i] = stack.pop();
            i++;
        }
        return ans;
    }

    private static void dfs(int src,boolean[] vis,Stack<Integer> stack,ArrayList<ArrayList<Integer>> adj){

        vis[src] = true;
        for(int node : adj.get(src)){
            if(!vis[node]){
                dfs(node,vis,stack,adj);
            }
        }
        stack.push(src);
    }
}
