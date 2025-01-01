import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, max;
    static boolean[][] map, visited;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<int[]> points = new ArrayList<>();
        map = new boolean[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            map[n][m] = true;
            points.add(new int[]{n, m});
        }

        max = Integer.MIN_VALUE;
        for (int[] point : points) {
            if (visited[point[0]][point[1]]) {
                continue;
            }
            max = Math.max(bfs(point[0], point[1]), max);
        }

        System.out.print(max);
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        int area = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            area++;
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx <= 0 || ny <= 0 || nx >= N + 1 || ny >= M + 1 || visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return area;
    }
}
