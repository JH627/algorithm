import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int start, end;
        double cost;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
            cost = Math.sqrt(Math.pow(x[start] - x[end], 2) + Math.pow(y[start] - y[end], 2));
        }
    }

    static int N;
    static double[] x, y;
    static int[] parent, rank;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        x = new double[N];
        y = new double[N];
        edges = new ArrayList<>();
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        rank = new int[N];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            x[n] = Double.parseDouble(st.nextToken());
            y[n] = Double.parseDouble(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                edges.add(new Edge(i, j));
            }
        }

        edges.sort(Comparator.comparingDouble(o -> o.cost));

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
            int ps = find(edge.start);
            int pe = find(edge.end);

            if (ps == pe) {
                continue;
            }

            union(edge.start, edge.end);
            sum += edge.cost;

            if (++clock == N - 1) {
                break;
            }
        }

        return sum;
    }
}
