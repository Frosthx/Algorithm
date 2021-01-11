import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class _1202_smallestStringWithSwaps {
    private class UnionFind{            //并查集
        private final int[] parent;
        private final int[] rank;
        public UnionFind(int n){
            parent = new int[n];        //父节点
            rank = new int[n];          //树高
            for(int i = 0; i < n; ++i){
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return;
            if(rank[rootX] == rank[rootY]){
                parent[rootX] = rootY;
                rank[rootY]++;
            }
            else if(rank[rootX] < rank[rootY])
                parent[rootX] = parent[rootY];
            else
                parent[rootY] = rootX;
        }
        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if(pairs.size() == 0)
            return s;

        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for(List<Integer> pair : pairs){
            int x = pair.get(0);
            int y = pair.get(1);
            unionFind.union(x, y);
        }

        char[] charArray = s.toCharArray();
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>(len);
        for(int i = 0; i < len; ++i){
            int root = unionFind.find(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).offer(charArray[i]);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < len; ++i){
            int root = unionFind.find(i);
            stringBuilder.append(map.get(root).poll());
        }
        return stringBuilder.toString();
    }
}
