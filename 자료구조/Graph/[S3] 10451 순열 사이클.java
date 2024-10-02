import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N, count;
    static int[] next;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            count = 0;
            next = new int[N + 1];
            visited = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int n = 1; n < N + 1; n++) {
                next[n] = Integer.parseInt(st.nextToken());
            }

            for (int n = 1; n < N + 1; n++) {
                if (!visited[n]) {
                    count++;
                    dfs(n);
                }
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int v) {
        visited[v] = true;

        if (!visited[next[v]]) {
            dfs(next[v]);
        }
    }
}
