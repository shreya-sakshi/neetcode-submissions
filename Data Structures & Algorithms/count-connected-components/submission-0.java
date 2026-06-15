class Solution {
    private void dfs(int node , ArrayList<ArrayList<Integer>> adjLs , int[] vis)
    {
        vis[node] = 1;

        for(Integer neighbour : adjLs.get(node))
        {
            if(vis[neighbour] == 0)
            {
                dfs(neighbour , adjLs , vis);
            }
        }
    }
    public int countComponents(int n, int[][] edges) {
         ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
         for(int i = 0 ; i < n ; i++)
         {
            adjLs.add(new ArrayList<>());
         }

         for(int[] edge : edges)
         {
            int u = edge[0];
            int v = edge[1];

            adjLs.get(u).add(v);
            adjLs.get(v).add(u);
         }

         int vis[] = new int[n];
         int count = 0 ; 

         for(int i = 0 ; i< n ; i++)
         {
            if(vis[i] == 0)
            {
                count++;
                dfs(i , adjLs , vis);
            }
         }

         return count;
          
    }
}
