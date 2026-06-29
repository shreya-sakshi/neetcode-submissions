class Solution {
    public String foreignDictionary(String[] words) {

        Map<Character,List<Character>> adj = new HashMap<>();
        Map<Character,Integer> indegree = new HashMap<>();

        for(String word : words)
        {
            for(char ch : word.toCharArray())
            {
                adj.putIfAbsent(ch , new ArrayList<>());
                indegree.putIfAbsent(ch,0);
            }
        }

        for(int i=0; i<words.length-1; i++)
        {
            String w1 = words[i];
            String w2 = words[i+1];

            if(w1.length() > w2.length() && w1.startsWith(w2))
            {
                return "";
            }

            int len = Math.min(w1.length() , w2.length());

            for(int j=0; j<len;j++)
            {
                char c1=w1.charAt(j);
                char c2=w2.charAt(j);

                if(c1!=c2)
                {
                    adj.get(c1).add(c2);
                    indegree.put(c2,indegree.get(c2)+1);
                    break;
                }
            }
        }

        Queue<Character> q = new LinkedList<>();
        for(char ch : indegree.keySet())
        {
            if(indegree.get(ch) == 0)
            {
                q.add(ch);
            }
        }

        StringBuilder result = new StringBuilder();

        while(!q.isEmpty())
        {
            char curr = q.poll();
            result.append(curr);

            for(char neighbour : adj.get(curr))
            {
                indegree.put(neighbour , indegree.get(neighbour)-1);

                if(indegree.get(neighbour) ==  0)
                {
                    q.add(neighbour);
                }
            }

        }

        
        if(result.length() != indegree.size())
        { 
            return "";
        }
        
        return result.toString();
      
    }
}
