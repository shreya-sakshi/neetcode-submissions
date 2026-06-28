class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
          int vis[] = new int[numCourses];
          int dfsvis[] = new int[numCourses];
          List<List<Integer>> adj = new ArrayList<>();
          for(int i=0; i<numCourses ; i++)
          {
             adj.add(new ArrayList<>());
          }

          for(int[] pre : prerequisites)
          {
             adj.get(pre[1]).add(pre[0]);
          }

          for(int i=0; i<numCourses ; i++)
          {
            if(vis[i] == 0)
            {
                if(iscyclic(i,adj,vis,dfsvis) ) return false;
            }
          }
          return true;
    }
    private boolean iscyclic( int node ,List<List<Integer>> adj ,int[] vis, int [] dfsvis)
    {
           vis[node]=1;
           dfsvis[node]=1;

           for(Integer it : adj.get(node))
           {
                  if(vis[it] == 0)
                  {
                     if (iscyclic( it ,adj, vis, dfsvis) == true)
                     return true;
                  }
                  else if(dfsvis[it] == 1)
                  {
                     return true;
                  }
           }
           dfsvis[node] = 0;
           return false;
    }
}