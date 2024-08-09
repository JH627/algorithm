import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge> {
        int end, cost;

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int N, M, X;
    static int[] dist, reverseDist;
    static ArrayList<ArrayList<Edge>> graph, reverseGraph;
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        reverseDist = new int[N + 1];
        Arrays.fill(dist, -1);
        Arrays.fill(reverseDist, -1);
        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, cost));
            reverseGraph.get(v).add(new Edge(u, cost));
        }

        dijkstra(X, dist, graph);
        dijkstra(X, reverseDist, reverseGraph);


        for (int i = 0; i < N + 1; i++) {
            MAX = Math.max(dist[i] + reverseDist[i], MAX);
        }

        System.out.println(MAX);
    }

    static void dijkstra(int start, int[] dist, ArrayList<ArrayList<Edge>> graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (dist[now.end] != -1) {
                continue;
            }
            dist[now.end] = now.cost;

            for (Edge next : graph.get(now.end)) {
                pq.add(new Edge(next.end, dist[now.end] + next.cost));
            }
        }
    }

}
