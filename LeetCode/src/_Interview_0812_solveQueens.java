import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _Interview_0812_solveQueens {
    private int[] colIndex;
    private boolean[] visited;
    private List<List<String>> res;
    private int n;
    public List<List<String>> solveNQueens(int n) {
        colIndex = new int[n];
        visited = new boolean[n];
        res = new LinkedList<>();
        this.n = n;
        dfs(0);
        return res;
    }
    private void dfs(int row){
        if(row == n){
            addValid();
            return;
        }
        for(int i = 0; i < n; i++){
            if(visited[i])
                continue;
            boolean valid = true;
            for(int j = 0; j < row; j++)
                if (j + colIndex[j] == row + i || j - colIndex[j] == row - i) {
                    valid = false;
                    break;
                }
            if(!valid)
                continue;
            visited[i] = true;
            colIndex[row] = i;
            dfs(row + 1);
            visited[i] = false;
        }
    }
    private void addValid(){
        List<String> valid = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            StringBuilder line = new StringBuilder();
            line.append(".".repeat(Math.max(0, colIndex[i])));
            line.append("Q");
            line.append(".".repeat(Math.max(0, n - (colIndex[i] + 1))));
            valid.add(line.toString());
        }
        res.add(valid);
    }
    @Test
    public void test(){
        for(List<String> valid : solveNQueens(8)) {
            valid.forEach(System.out::println);
            System.out.println();
        }
    }
}
