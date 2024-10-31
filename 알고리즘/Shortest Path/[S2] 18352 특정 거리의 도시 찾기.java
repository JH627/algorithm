import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

    static int N, M, K, X;
    static ArrayList<ArrayList<Node>> graph;
    static ArrayList<Integer> result;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        result = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, 1));
        }

        dist = new int[N + 1];
        Arrays.fill(dist, 400000);
        dijkstra(X);

        if (result.isEmpty()) {
            System.out.print(-1);
            return;
        }

        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        for (Integer i : result) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
    }

    static void dijkstra(int x) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(x, 0));
        dist[x] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cost > K) {
                return;
            }
            if (dist[now.node] < now.cost) {
                continue;
            }
            if (now.cost == K) {
                result.add(now.node);
            }

            for (Node next : graph.get(now.node)) {
                if (dist[next.node] > dist[now.node] + 1) {
                    dist[next.node] = dist[now.node] + 1;
                    pq.add(new Node(next.node, dist[next.node]));
                }
            }
        }
    }
}
