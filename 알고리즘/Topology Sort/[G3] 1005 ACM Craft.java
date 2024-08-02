import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T, N, K, W;
    static int[] cost, degree, maxCost;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }
            cost = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }

            degree = new int[N + 1];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                degree[v]++;
            }

            W = Integer.parseInt(br.readLine());

            maxCost = new int[N + 1];
            topologySort();
            sb.append(maxCost[W]).append("\n");
        }
        System.out.println(sb);
    }

    static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (degree[i] == 0) {
                maxCost[i] = cost[i];
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            int len = graph.get(now).size();
            for (int i = 0; i < len; i++) {
                int next = graph.get(now).get(i);
                maxCost[next] = Math.max(maxCost[next], maxCost[now] + cost[next]);
                if (--degree[next] == 0) {
                    q.add(next);
                }
            }
        }
    }
}
