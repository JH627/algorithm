import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] member;
    static int[][] dp;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        member = new int[N];
        dp = new int[N][2];
        visited = new boolean[N];
        edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            member[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        dfs(0);

        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }

    static void dfs(int now) {
        visited[now] = true;

        dp[now][0] = 0;
        dp[now][1] = member[now];

        for (Integer next: edges.get(now)) {
            if (!visited[next]) {
                dfs(next);
                dp[now][0] += Math.max(dp[next][0], dp[next][1]);
                dp[now][1] += dp[next][0];
            }
        }
    }
}
