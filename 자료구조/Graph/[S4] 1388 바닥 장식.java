import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        String s;
        for (int n = 0; n < N; n++) {
            s = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = s.charAt(m);
            }
        }

        int cnt = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (!visited[n][m]) {
                    cnt++;
                    dfs(n, m, map[n][m] == '-');
                }
            }
        }

        System.out.print(cnt);
    }

    static void dfs(int x, int y, boolean flag) {
        visited[x][y] = true;

        if (flag) {
            if (y + 1 < M && map[x][y + 1] == '-') {
                dfs(x, y + 1, true);
            }
        }
        else {
            if (x + 1 < N && map[x + 1][y] != '-') {
                dfs(x + 1, y, false);
            }
        }
    }
}
