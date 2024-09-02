import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int T, MAX;
    static int[] dist;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(v).add(new Node(u, s));
            }

            dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            MAX = Integer.MIN_VALUE;

            sb.append(bfs(c)).append(" ").append(MAX).append("\n");
        }

        System.out.print(sb);
    }

    static int bfs(int s) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(s, 0));

        int cnt = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (dist[now.node] < now.cost) {
                continue;
            }
            dist[now.node] = now.cost;
            MAX = Math.max(MAX, dist[now.node]);
            cnt++;

            for (Node next : graph.get(now.node)) {
                if (dist[next.node] > dist[now.node] + next.cost) {
                    dist[next.node] = dist[now.node] + next.cost;
                    q.add(new Node(next.node, dist[next.node]));
                }
            }
        }

        return cnt;
    }
}
