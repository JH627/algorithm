import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge> {
        int start, end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int N, M;
    static int[] parent, rank;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
        rank = new int[N + 1];
        edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(edges);

        System.out.println((N == 2) ? 0 : mst());

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

    static int mst() {
        int sum = 0;
        int clock = 0;
        for (Edge edge : edges) {
            int ps = find(edge.start);
            int pe = find(edge.end);

            if (ps == pe) {
                continue;
            }

            union(edge.start, edge.end);
            sum += edge.cost;

            if (++clock == N - 2) {
                break;
            }
        }

        return sum;
    }
}
