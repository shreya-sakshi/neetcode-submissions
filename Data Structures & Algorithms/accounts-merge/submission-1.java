class Solution {

    class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];

            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int findUPar(int node) {

            if(node == parent[node])
                return node;

            return parent[node] =
                    findUPar(parent[node]);
        }

        void unionBySize(int u, int v) {

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if(ulp_u == ulp_v)
                return;

            if(size[ulp_u] < size[ulp_v]) {

                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            }
            else {

                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();

        DSU ds = new DSU(n);

        HashMap<String , Integer> mapMailNode  = new HashMap<String , Integer>();

        for(int i=0 ; i<n ; i++)
        {
            for(int j = 1 ; j < accounts.get(i).size() ; j++)
            {
                String mail = accounts.get(i).get(j);

                if(!mapMailNode.containsKey(mail))
                {
                    mapMailNode.put(mail,i);
                }
                else
                {
                    ds.unionBySize(i, mapMailNode.get(mail));
                }
            }
        }

        ArrayList<String>[] mergedMail = new ArrayList[n];

        for(int i=0;i<n;i++)
        {
            mergedMail[i] = new ArrayList<String>();
        }

        for(Map.Entry<String , Integer> it : mapMailNode.entrySet())
        {
            String mail = it.getKey();

            int node = ds.findUPar(it.getValue());

            mergedMail[node].add(mail);
        }

        List<List<String>> ans = new ArrayList<>();

        for(int i=0; i< n ; i++)
        {
            if(mergedMail[i].size() == 0) continue;

            Collections.sort(mergedMail[i]);

            List<String> temp = new ArrayList<>();

            temp.add(accounts.get(i).get(0));

            for(String it : mergedMail[i])
            {
                temp.add(it);
            }

            ans.add(temp);
        }

        return ans;

    }
}