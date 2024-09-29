import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, clock;
    static int MIN = Integer.MAX_VALUE;
    static int[][] map, group;
    static boolean[][] visited;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    static class Dist {
        int x, y, dist;

        public Dist(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        group = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clock = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    clock++;
                    dfs(i, j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    bfs(i, j, group[i][j]);
                }
            }
        }

        System.out.print(MIN);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        group[x][y] = clock;

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            if (!visited[nx][ny] && map[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }

    static void bfs(int x, int y, int g) {
        Queue<Dist> q = new LinkedList<>();
        visited = new boolean[N][N];
        visited[x][y] = true;
        q.add(new Dist(x, y, 0));

        while (!q.isEmpty()) {
            Dist now = q.poll();

            if (group[now.x][now.y] != g && map[now.x][now.y] == 1) {
                MIN = Math.min(MIN, now.dist - 1);
            }

            if (now.dist > MIN) {
                return;
            }

            int nx, ny;
            for (int i = 0; i < 4; i++) {
                nx = now.x + dx[i];
                ny = now.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (group[nx][ny] == g) {
                    continue;
                }
                if (!visited[nx][ny]) {
                    q.add(new Dist(nx, ny, now.dist + 1));
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
