class Solution {
    public void islandsAndTreasure(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> q = new LinkedList<>();

        for(int i=0; i<n ; i++)
        {
            for(int j=0; j<m ; j++)
            {
                if(grid[i][j] == 0)
                {
                    q.add(new int[]{i,j});
                }
            }
        }

        int[] drow={-1,0,1,0};
        int[] dcol={0,1,0,-1};

        while(!q.isEmpty())
        {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for(int i=0; i< 4 ; i++)
            {
                int nr = r + drow[i];
                int nc = c + dcol[i];

                if(nr>=0 && nr < n && nc>=0 && nc < m && grid[nr][nc] == Integer.MAX_VALUE)
                {
                    grid[nr][nc]=grid[r][c]+1;
                    q.add(new int[]{nr,nc});
                }
            }
        }

    }
}
