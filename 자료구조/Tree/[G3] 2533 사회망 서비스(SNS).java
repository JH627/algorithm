import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean[] visited;
    static int[][] dp;
    static int[] stack;
    static ArrayList<ArrayList<Integer>> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        dp = new int[N][2];
        stack = new int[2];
        edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        dfs(0, false);

        System.out.println(N - Math.max(dp[0][0], dp[0][1]));
    }

    static void dfs(int now, boolean flag) {
        visited[now] = true;

        dp[now][0] = 0;
        dp[now][1] = 1;

        for (Integer next : edges.get(now)) {
            if (!visited[next]) {
                dfs(next, !flag);
                dp[now][0] += Math.max(dp[next][0], dp[next][1]);
                dp[now][1] += dp[next][0];
            }
        }
    }
}
