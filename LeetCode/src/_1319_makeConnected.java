import java.util.Arrays;

public class _1319_makeConnected {
    public static int makeConnected(int n, int[][] connections) {
        if(connections.length < n - 1)
            return -1;
        UnionFind unionFind = new UnionFind(n);
        for(int[] conn : connections)
            unionFind.union(conn[0], conn[1]);
        return unionFind.getBranch() - 1;
    }
    static class UnionFind{
        private final int[] parent;
        private int branch;
        public UnionFind(int n){
            parent = new int[n];
            branch = 0;
            Arrays.fill(parent, -1);
        }
        public int find(int x){
            if(parent[x] == -1){
                parent[x] = x;
                branch++;
            }
            if(x != parent[x])
                parent[x] = find(parent[x]);
            return parent[x];
        }
        private void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return;
            parent[rootX] = rootY;
            branch--;
        }
        public int getBranch(){
            int res = branch;
            for(int x : parent)
                if(x == -1)
                    res++;
            return res;
        }
    }
}
