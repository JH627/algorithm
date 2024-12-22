import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {1, 0, -1, 0};

    static int N, M;
    static int cnt = 0;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int sn = 0;
        int sm = 0;
        for (int n = 0; n < N; n++) {
            String s = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = s.charAt(m);
                if (map[n][m] == 'I') {
                    sn = n;
                    sm = m;
                }
            }
        }

        bfs(sn, sm);

        System.out.print(cnt == 0 ? "TT" : cnt);
    }

    static void bfs(int sn, int sm) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new int[]{sn, sm});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0], y = poll[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny] || map[nx][ny] == 'X') {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                if (map[nx][ny] == 'P') {
                    cnt++;
                }
            }
        }
    }
}
