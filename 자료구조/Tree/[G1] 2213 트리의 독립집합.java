import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] weight;
    static int[][] dp;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> edges;
    static ArrayList<Integer> sets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        weight = new int[n];
        dp = new int[n][2];
        visited = new boolean[n];
        edges = new ArrayList<>();
        sets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        dfs(0);

        Arrays.fill(visited, false);

        trace(0, false);

        StringBuilder sb = new StringBuilder();
        sb.append(Math.max(dp[0][0], dp[0][1])).append("\n");
        Collections.sort(sets);
        for (Integer set : sets) {
            sb.append(set + 1).append(" ");
        }
        System.out.println(sb);
    }

    static void dfs(int now) {
        visited[now] = true;

        dp[now][0] = 0;
        dp[now][1] = weight[now];

        if (!edges.get(now).isEmpty()) {
            for (Integer next : edges.get(now)) {
                if (!visited[next]) {
                    dfs(next);
                    dp[now][0] += Math.max(dp[next][0], dp[next][1]);
                    dp[now][1] += dp[next][0];
                }
            }
        }
    }

    static void trace(int now, boolean flag) {
        visited[now] = true;

        if (!flag) {
            boolean selected = dp[now][1] > dp[now][0];
            if (selected) {
                sets.add(now);
            }
            for (Integer next : edges.get(now)) {
                if (!visited[next]) {
                    trace(next, selected);
                }
            }
        }
        else {
            for (Integer next : edges.get(now)) {
                if (!visited[next]) {
                    trace(next, false);
                }
            }
        }
    }
}