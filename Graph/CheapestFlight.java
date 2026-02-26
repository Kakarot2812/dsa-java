import java.util.*;
/*
Problem: Cheapest Flights Within K Stops

Approach:
- Build adjacency list from flight edges.
- Use BFS-like traversal while tracking:
      (stops_used, current_node, total_cost)
- Maintain a distance array to store minimum cost to reach each node.
- Only explore neighbors if:
      stops_used <= k
      and new_cost < recorded_cost
- If destination is unreachable within k stops, return -1.

Key Idea:
This is a shortest path problem with an additional constraint:
      number_of_stops <= k

Instead of classic Dijkstra, we use level-based traversal
because stops act as layers.

Time Complexity:
    O(E * k)
    In worst case, each edge can be relaxed up to k times.

Space Complexity:
    O(N + E)
    For adjacency list, queue, and distance array.
*/
class Flight{
        int node;
        int distance;
        public Flight(int node,int distance){
            this.node = node;
            this.distance = distance;
        }
    }
    class Cheap{
        int stop;
        int node;
        int distance;
        public Cheap(int stop,int node,int distance){
            this.stop = stop;
            this.node = node;
            this.distance = distance;
        }
    }
    class CheapestFlight{
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            ArrayList<ArrayList<Flight>> adj = new ArrayList<>();
            for(int i=0;i<n;i++){
                adj.add(new ArrayList<>());
            }
            int m = flights.length;
            for(int i=0;i<m;i++){
                adj.get(flights[i][0]).add(new Flight(flights[i][1],flights[i][2]));
            }
            int[] dis = new int[n];
            Arrays.fill(dis,Integer.MAX_VALUE);
            dis[src] = 0;

            Queue<Cheap> queue = new LinkedList<>();
            queue.add(new Cheap(0,src,0));

            while(!queue.isEmpty()){
                Cheap cell = queue.poll();
                int stop = cell.stop;
                int node = cell.node;
                int distance = cell.distance;

                if(stop > k) continue;
                for(Flight neighbour : adj.get(node)){
                    int adjNode = neighbour.node;
                    int weight = neighbour.distance;

                    if(distance + weight < dis[adjNode] && stop <= k){
                        dis[adjNode] = distance + weight;
                        queue.add(new Cheap(stop+1,adjNode,dis[adjNode]));
                    }
                }
            }
            if(dis[dst] == Integer.MAX_VALUE){
                return -1;
            }
            return dis[dst];
        }
    }


