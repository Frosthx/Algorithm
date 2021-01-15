import java.util.HashMap;
import java.util.Map;

public class _947_removeStones {
    static class UnionFind{
        private final Map<Integer,Integer> parent;
        private int branch;
        UnionFind(){
            parent = new HashMap<>();
            branch = 0;
        }
        void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return;
            parent.put(rootX, rootY);
            branch--;
        }
        int find(int x){
            parent.computeIfAbsent(x, k -> {
                branch++;
                return k;
            });
            if(x != parent.get(x))
                parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }
        int getBranch(){
            return branch;
        }
    }
    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind();
        for(int[] stone : stones){
            unionFind.union(stone[0], stone[1] + 20000);
        }
        return stones.length - unionFind.getBranch();
    }
}
