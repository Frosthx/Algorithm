import java.util.Arrays;
import java.util.PriorityQueue;

public class _1631_minimumEffortPath {      //Dijkstra
    public int minimumEffortPath(int[][] heights) {
        final int[][] DIRECTIONS = {{1,0},{-1,0},{0,-1},{0,1}};
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[] visited = new boolean[rows * cols];
        int[] dist = new int[rows * cols];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2)->n1[2]-n2[2]);
        queue.offer(new int[]{0, 0, 0});
        while(!queue.isEmpty()){
            int[] block = queue.poll();
            int x = block[0];
            int y = block[1];
            int d = block[2];
            int pos = x * cols + y;
            if(visited[pos])
                continue;
            visited[pos] = true;
            if(x == rows - 1 && y == cols - 1)
                break;
            for(int[] direction : DIRECTIONS){
                int newX = x + direction[0];
                int newY = y + direction[1];
                int newPos = newX * cols + newY;
                if(newX >= 0 && newX < rows && newY >= 0 && newY < cols && Math.max(d, Math.abs(heights[newX][newY] - heights[x][y])) < dist[newPos]){
                    dist[newPos] = Math.max(d, Math.abs(heights[newX][newY] - heights[x][y]));
                    queue.offer(new int[]{newX, newY, dist[newPos]});
                }
            }
        }
        return dist[rows * cols - 1];
    }
}
