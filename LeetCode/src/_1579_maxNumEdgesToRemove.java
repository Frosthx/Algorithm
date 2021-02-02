import java.util.Arrays;

public class _1579_maxNumEdgesToRemove {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        if(n < 1 || edges.length < 1)   return -1;
        for(int[] edge : edges){
            edge[1]--;
            edge[2]--;
        }
        int res = 0;
        UnionFind alice = new UnionFind(n);
        for(int[] edge : edges)
            if(edge[0] == 3 && !alice.union(edge[1], edge[2]))
                res++;
        UnionFind bob = new UnionFind(alice);
        for(int[] edge : edges)
            if(edge[0] == 1 && !alice.union(edge[1], edge[2]) ||
                    edge[0] == 2 && !bob.union(edge[1], edge[2]))
                res++;
        if(alice.getSet() > 1 || bob.getSet() > 1)
            return -1;
        return res;
    }
    static class UnionFind{
        private final int[] parent;
        private int set;
        public UnionFind(int n){
            parent = new int[n];
            set = n;
            for(int i = 0; i < n; i++)
                parent[i] = i;
        }
        public UnionFind(UnionFind uf){
            parent = Arrays.copyOf(uf.parent, uf.parent.length);
            set = uf.set;
        }
        public boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return false;
            parent[rootX] = rootY;
            set--;
            return true;
        }
        private int find(int x){
            if(parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
        public int getSet(){
            return set;
        }
    }
}
