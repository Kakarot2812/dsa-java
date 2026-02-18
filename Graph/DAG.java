import java.util.ArrayList;

/*
Problem: Cycle Detection in Directed Graph
Concept: DFS + Recursion Stack
Time Complexity: O(V + E)
Space Complexity: O(V)

Explanation:
- Use visited[] to track visited nodes.
- Use path[] to track the current recursion stack.
- If a node is encountered that is already in the stack,
  a cycle exists.
*/

public class DAG {
    public static boolean isCycleDFS(int n, ArrayList<ArrayList<Integer>> adj){

        boolean[] vis = new boolean[n];
        boolean[] path = new boolean[n];
        for(int i=0;i<n;i++){
            if(!vis[i]){
                if( checkForCycle(i,vis,path,adj)){
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean checkForCycle(int node,boolean[] vis,
                                         boolean[] path,ArrayList<ArrayList<Integer>> adj){

        vis[node] = true;
        path[node] = true;

        for(int adjacentNode : adj.get(node) ){
            if(!vis[adjacentNode]) {
                if (checkForCycle(adjacentNode, vis,path, adj)) {
                    return true;
                }
            }
            else if (path[adjacentNode]) {
                return true;
            }
        }
        path[node] = false;
        return false;
    }
}
