import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int N, M, H;
    static int[] depth;
    static int[] dis;
    static int[][] parent;
    static ArrayList<ArrayList<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        H = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
        depth = new int[N + 1];
        dis = new int[N + 1];
        parent = new int[N + 1][H];

        dis[1] = 0;
        dfs(1, 1, 0);
        fill();

        M = Integer.parseInt(br.readLine());
        int a, b, lca, distance;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            lca = findLca(a, b);
            if (lca == a || lca == b) {
                distance = Math.abs(dis[a] - dis[b]);
            }
            else {
                distance = Math.abs(dis[lca] - dis[a]) + Math.abs(dis[lca] - dis[b]);
            }
            sb.append(distance).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int now, int h, int pre){
        depth[now] = h;
        for (Edge next : graph.get(now)) {
            if (next.node != pre) {
                dis[next.node] = dis[now] + next.weight;
                dfs(next.node, h + 1, now);
                parent[next.node][0] = now;
            }
        }
    }

    static void fill() {
        for(int i = 1; i < H; i++) {
            for(int j = 1; j < N + 1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    static int findLca(int a, int b) {
        if (a == b) {
            return a;
        }

        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = H - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[a][i];
            }
        }

        if (a == b) {
            return a;
        }

        for (int i = H - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
}
