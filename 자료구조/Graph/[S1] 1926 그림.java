import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] arr;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int area = 0;
        int max = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    area++;
                    max = Math.max(bfs(i, j), max);
                }
            }
        }
        System.out.println(area);
        System.out.print(max);

    }

    static int bfs(int i, int j) {
        int ret = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        arr[i][j] = 2;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            ret++;

            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dx[k];
                int ny = cur[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N || arr[nx][ny] == 0 || arr[nx][ny] == 2) {
                    continue;
                }

                arr[nx][ny] = 2;
                q.add(new int[]{nx, ny});
            }
        }

        return ret;
    }
}
