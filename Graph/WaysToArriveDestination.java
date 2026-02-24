import java.util.*;
/*
    Problem: Count Ways to Arrive at Destination

    Approach:
    - Use Dijkstra’s Algorithm to find shortest distance from node 0.
    - Maintain an additional array `ways[]` to count number of shortest paths.
    - If a shorter path is found → update distance and copy ways.
    - If an equal shortest path is found → add ways (mod 1e9+7).

    Important Points:
    - Use long for distance to prevent overflow.
    - Use modulo while updating ways.
    - Skip stale priority queue entries.

    Time Complexity: O((V + E) log V)
    Space Complexity: O(V + E)
*/

class Path{
    int node;
    long distance;
    public Path(int node,long distance){
        this.node = node;
        this.distance = distance;
    }
}
class Solution {
    int MOD = 1000000007;
    public int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<Path>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        int m = roads.length;
        for(int i=0;i<m;i++){
            adj.get(roads[i][0]).add(new Path(roads[i][1],roads[i][2]));
            adj.get(roads[i][1]).add(new Path(roads[i][0],roads[i][2]));
        }
        long[] dis = new long[n];
        int[] ways = new int[n];

        Arrays.fill(dis,Long.MAX_VALUE);
        Arrays.fill(ways,0);

        dis[0] = 0;
        ways[0] = 1;
        PriorityQueue<Path> queue = new PriorityQueue<>(
                (x,y) -> Long.compare(x.distance,y.distance)
        );
        queue.add(new Path(0,0));
        while(!queue.isEmpty()){
            Path cell = queue.poll();
            int adjNode = cell.node;
            long distance = cell.distance;

            if(distance > dis[adjNode]) continue;
            for(Path it : adj.get(adjNode)){
                int neighbour = it.node;
                long weight = it.distance;
                if(weight + distance < dis[neighbour]){
                    dis[neighbour] = weight + distance;
                    queue.add(new Path(neighbour,dis[neighbour]));
                    ways[neighbour] = ways[adjNode];
                }
                else if(distance + weight == dis[neighbour]){
                    ways[neighbour] = (ways[neighbour] + ways[adjNode])%MOD;
                }
            }
        }
        return ways[n-1]%MOD;
    }
}