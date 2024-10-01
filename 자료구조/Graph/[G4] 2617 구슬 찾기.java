import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph, reverseGraph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();

        for (int n = 0; n <= N; n++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            reverseGraph.get(v).add(u);
        }

        int cnt = 0;
        int mid = N / 2;
        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N + 1];
            if (dfs(graph, i, 0) > mid) {
                cnt++;
                continue;
            }
            if (dfs(reverseGraph, i, 0) > mid) {
                cnt++;
            }
        }
        System.out.print(cnt);
    }

    static int dfs(ArrayList<ArrayList<Integer>> g, int v, int cnt) {
        visited[v] = true;
        for (Integer next : g.get(v)) {
            if (!visited[next]) {
                cnt += dfs(g, next, 1);
            }
        }
        return cnt;
    }
}
