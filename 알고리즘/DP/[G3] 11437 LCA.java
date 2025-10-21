import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11437_LCA {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int nodeCount, edgeCount, queryCount;
    static int height;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] depth;
    static int[][] parent;

    public static void main(String[] args) throws IOException {
        init();
        fillParent();
        findLCA();
        System.out.print(sb);
    }

    static void fillParent() {
        dfs(1, 1, 0);
        fill();
    }

    static void dfs(int now, int h, int prev) {
        depth[now] = h;
        for (int next : graph.get(now)) {
            if (next != prev) {
                dfs(next, h + 1, now);
                parent[next][0] = now;
            }
        }
    }

    static void fill() {
        for (int h = 1; h < height; h++) {
            for (int node = 1; node <= nodeCount; node++) {
                parent[node][h] = parent[parent[node][h - 1]][h - 1];
            }
        }
    }

    static void findLCA() throws IOException {
        queryCount = Integer.parseInt(br.readLine());
        for (int query = 0; query < queryCount; query++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(find(a, b)).append("\n");
        }
    }

    static int find(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int h = height - 1; h >= 0; h--) {
            if (Math.pow(2, h) <= depth[a] - depth[b]) {
                a = parent[a][h];
            }
        }

        if (a == b) {
            return a;
        }

        for (int h = height - 1; h >= 0; h--) {
            if (parent[a][h] != parent[b][h]) {
                a = parent[a][h];
                b = parent[b][h];
            }
        }

        return parent[a][0];
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        nodeCount = Integer.parseInt(br.readLine());
        edgeCount = nodeCount - 1;

        graph = new ArrayList<>();
        for (int node = 0; node <= nodeCount; node++) {
            graph.add(new ArrayList<>());
        }

        for (int edge = 0; edge < edgeCount; edge++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        height = (int)Math.ceil(Math.log(nodeCount) / Math.log(2)) + 1;
        depth = new int[nodeCount + 1];
        parent = new int[nodeCount + 1][height];
    }
}
