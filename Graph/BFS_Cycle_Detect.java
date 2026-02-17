import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem: Detect Cycle in Undirected Graph
Concept: BFS
Difficulty: Medium
Time Complexity: O(V + E)
Space Complexity: O(V)
Explanation:
- Traverse each component of the graph.
- Use BFS and store (node, parent) in queue.
- If a visited neighbor is found that is NOT the parent,
  then a cycle exists.
*/

class Pair{
    int node;
    int parent;

    public Pair(int node,int parent){
        this.node = node;
        this.parent = parent;
    }
}
public class BFS_Cycle_Detect {
    public static boolean isCycleBFS(int n, ArrayList<ArrayList<Integer>> adj){

        boolean[] vis = new boolean[n];
        for(int i=0;i<n;i++){
            if(!vis[i]){
                if( checkForCycle(i,vis,adj)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkForCycle(int src,boolean[] vis,ArrayList<ArrayList<Integer>> adj){

        Queue<Pair> queue = new LinkedList<>();
        vis[src] = true;

        queue.offer(new Pair(src,-1));

        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            int node = pair.node;
            int parent = pair.parent;

            for(int neighbour : adj.get(node)){
                if(!vis[neighbour]){
                    vis[neighbour] = true;
                    queue.offer(new Pair(neighbour , node));
                }
                else if(parent != neighbour){
                    return true;
                }
            }
        }
        return false;
    }
}
