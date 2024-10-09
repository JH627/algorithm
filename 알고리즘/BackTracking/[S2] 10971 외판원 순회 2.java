import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long min;
    static boolean[] visited;
    static long[][] w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        w = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, i, 0, 0);
        }

        System.out.print(min);
    }

    static void dfs(int start, int now, long cost, int depth) {
        if (depth == N - 1) {
            if (w[now][start] != 0) {
                min = Math.min(min, cost + w[now][start]);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && w[now][i] != 0) {
                if (cost + w[now][i] < min) {
                    visited[i] = true;
                    dfs(start, i, cost + w[now][i], depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
