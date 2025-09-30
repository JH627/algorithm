import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16398_행성연결 {

    static BufferedReader br;
    static StringTokenizer st;

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

    static int nodeCount;
    static PriorityQueue<Edge> edges;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findMinCost());
    }

    static long findMinCost() {
        int addEdges = 0;
        long sum = 0;
        while (!edges.isEmpty()) {
            Edge now = edges.poll();
            if (union(now.start, now.end)) {
                sum += now.cost;
                if (++addEdges == nodeCount - 1) {
                    return sum;
                }
            }
        }

        return sum;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) {
            return false;
        }

        if (rank[px] > rank[py]) {
            parent[py] = px;
        }
        else if (rank[px] < rank[py]) {
            parent[px] = py;
        }
        else {
            parent[py] = px;
            rank[px]++;
        }

        return true;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        edges = new PriorityQueue<>();
        nodeCount = Integer.parseInt(br.readLine());
        parent = new int[nodeCount];
        rank = new int[nodeCount];

        for (int node = 0; node < nodeCount; node++) {
            parent[node] = node;
        }

        for (int node = 0; node < nodeCount; node++) {
            st = new StringTokenizer(br.readLine());
            for (int next = 0; next < nodeCount; next++) {
                if (node <= next) {
                    continue;
                }
                edges.add(new Edge(node, next, Integer.parseInt(st.nextToken())));
            }
        }
    }
}
