public class _765_minSwapsCouples {
    public int minSwapsCouples(int[] row) {
        int N = row.length >> 1;
        UnionFind unionFind = new UnionFind(N);
        for(int i = 0; i < row.length; i += 2)
            unionFind.union(row[i] >> 1, row[i + 1] >> 1);
        return N - unionFind.getSets();
    }
    static class UnionFind{
        int[] parent;
        int sets;
        public UnionFind(int n){
            parent = new int[n];
            sets = n;
            for(int i = 0; i < n; i++)
                parent[i] = i;
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return;
            parent[rootX] = rootY;
            sets--;
        }
        public int find(int x){
            if(parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
        public int getSets(){
            return sets;
        }
    }
    public int minSwapCouplesSimulate(int[] row){
        int res = 0;
        for(int i = 0; i < row.length; i += 2){
            int a = row[i];
            int b = a ^ 1;
            if(row[i + 1] == b)
                continue;
            for(int j = i + 2; j < row.length; j++){
                if(row[j] == b){
                    swap(row, i + 1, j);
                    res++;
                    break;
                }
            }
        }
        return res;
    }
    private void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
