//冗余连接
class _684_findRedundantConnection {
    private static class UnionFind{
        private final int[] parent;
        private final int[] rank;
        public UnionFind(int num){
            parent = new int[num];
            rank = new int[num];
            for(int i = 0; i < num; i++){
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return false;
            else if(rank[rootX] == rank[rootY]){
                parent[rootX] = rootY;
                rank[rootY]++;
            }
            else if(rank[rootX] < rank[rootY])
                parent[rootX] = rootY;
            else
                parent[rootY] = rootX;
            return true;
        }
        public int find(int x){
            if(parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length + 1);
        for(int[] edge : edges)
            if(!unionFind.union(edge[0], edge[1]))
                return edge;
        return new int[0];
    }
}