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

    static int N, M;
    static ArrayList<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int n = 0; n < N + 1; n++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, 50000001);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Edge(e, c));
            graph.get(e).add(new Edge(s, c));
        }

        dijkstra();

        System.out.print(dist[N]);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));

        dist[1] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (now.end == N) {
                return;
            }
            if (visited[now.end]) {
                continue;
            }
            visited[now.end] = true;

            for (Edge next : graph.get(now.end)) {
                if (dist[next.end] > dist[now.end] + next.cost) {
                    dist[next.end] = dist[now.end] + next.cost;
                    pq.add(new Edge(next.end, dist[next.end]));
                }
            }
        }
    }
}
