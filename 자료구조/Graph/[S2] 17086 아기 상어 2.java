import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static final int[] dy = {1, 1, 1, 0, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Queue<int[]> q = new LinkedList<>();
        boolean[][] map = new boolean[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                if (st.nextToken().equals("1")) {
                    map[n][m] = true;
                    q.add(new int[]{n, m});
                }
            }
        }

        int[][] dist = new int[N][M];

        int max = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (map[nx][ny] || dist[nx][ny] != 0) {
                    continue;
                }
                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                max = Math.max(max, dist[nx][ny]);
                q.add(new int[]{nx, ny});
            }
        }

        System.out.print(max);
    }
}
