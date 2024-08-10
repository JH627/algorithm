import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int start, end;
        double distance;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
            distance = Math.sqrt(Math.pow(x[start] - x[end], 2) + Math.pow(y[start] - y[end], 2));
        }
    }

    static int N, M, connect;
    static int[] parent, rank, connected;
    static int[] x, y;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        x = new int[N + 1];
        y = new int[N + 1];
        connected = new int[N + 1];
        connect = 0;
        rank = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
        edges = new ArrayList<>();

        for (int n = 1; n < N + 1; n++) {
            st = new StringTokenizer(br.readLine());
            x[n] = Integer.parseInt(st.nextToken());
            y[n] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = i + 1; j < N + 1; j++) {
                edges.add(new Edge(i, j));
            }
        }

        edges.sort(Comparator.comparingDouble(o -> o.distance));

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
            if (connected[u] == 0) {
                connect++;
                connected[u] = 1;
            }
            if (connected[v] == 0) {
                connect++;
                connected[v] = 1;
            }
        }

        System.out.printf("%.2f", mst());
    }

    static int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return;
        }

        if (rank[pa] == rank[pb]) {
            parent[pb] = pa;
            rank[pa]++;
        }
        else if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        }
        else {
            parent[pb] = pa;
        }
    }

    static double mst() {
        double sum = 0;

        int clock = 0;
        for (Edge edge : edges) {
            int pa = find(edge.start);
            int pb = find(edge.end);

            if (pa == pb) {
                continue;
            }

            union(edge.start, edge.end);
            sum += edge.distance;

            if (++clock == N - connect / 2 - 1) {
                break;
            }
        }

        return sum;
    }
}
