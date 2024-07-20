import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] depth;
    static int[][] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        H = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
        depth = new int[N + 1];
        parent = new int[N + 1][H];

        dfs(1,1,0);
        fill();

        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(find(a, b)).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int now, int h, int pre) {
        depth[now] = h;
        for(int next : graph.get(now)) {
            if(next != pre) {
                dfs(next, h + 1, now);
                parent[next][0] = now;
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

    static int find(int a, int b) {
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
