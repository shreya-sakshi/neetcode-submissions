class Solution {
    class Pair
    {
        int first;
        int second;

        Pair(int first , int second)
        {
            this.first = first;
            this.second = second;
        }
    }
    class Tuple
    {
        int first;
        int second;
        int third;
        
        Tuple(int first , int second , int third)
        {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0 ; i<n ; i++)
        {
            adj.add(new ArrayList<>());
        }

        int m = flights.length;

        for(int i=0 ; i<m ; i++)
        {
            adj.get(flights[i][0]).add(new Pair(flights[i][1] , flights[i][2]));
        }

        Queue<Tuple> q = new LinkedList<>();

        q.add(new Tuple (0,src,0));

        int[] dist = new int[n];

        for(int i=0 ; i<n ; i++)
        {
            dist[i] = (int) 1e9;
        }

        dist[src] = 0;

        while(!q.isEmpty())
        {
            Tuple curr = q.peek();
            q.remove();

            int stops = curr.first;
            int node = curr.second;
            int cost = curr.third;

            if(stops > k) continue;

            for(Pair itr : adj.get(node))
            {
                int adjnode = itr.first;
                int edw = itr.second;

                if(cost + edw < dist[adjnode])
                {
                    dist[adjnode] = cost + edw;
                    q.add(new Tuple(stops + 1 , adjnode , cost + edw));
                }
            }
        }
        if(dist[dst] == 1e9) return -1;
        return dist[dst];  
    }
}