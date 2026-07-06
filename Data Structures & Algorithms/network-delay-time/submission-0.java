class Solution {
    class Pair
    {
        int node;
        int dist;
        Pair(int node , int dist)
        {
            this.node = node;
            this.dist = dist;
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i=0 ; i<=n ; i++)
        {
            adj.add(new ArrayList<>());
        }

        for(int[] edges : times)
        {
            int u = edges[0];
            int v = edges[1];
            int w = edges[2];

            adj.get(u).add(new Pair(v,w));
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist , (int)1e9);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.dist - b.dist);

        dist[k] = 0;
        pq.add(new Pair(k,0));

        while(!pq.isEmpty())
        {
            Pair curr = pq.poll();

            int node = curr.node;
            int d = curr.dist;

            if(d > dist[node]) continue;

            for(Pair nbr : adj.get(node))
            {
                int adjnode = nbr.node;
                int edgwt = nbr.dist;

                if(d + edgwt < dist[adjnode])
                {
                    dist[adjnode] = d + edgwt;
                    pq.add(new Pair(adjnode , d + edgwt));
                }
            }
        }

        int maxtime = 0;

        for(int i=1 ; i <=n ; i++)
        {
            if(dist[i] == (int)1e9)
               return -1;

            maxtime = Math.max(maxtime , dist[i]);
        }

        return maxtime;
        
    }
}