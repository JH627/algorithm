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

    static int N, M;
    static ArrayList<ArrayList<Node>> cost;
    static int[] distance, dp;
    static boolean[] visited;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        cost = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            cost.add(new ArrayList<>());
        }
        distance = new int[N + 1];
        dp = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(distance, INF);

        StringTokenizer st;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cost.get(a).add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        ArrayList<Integer> trace = new ArrayList<>();
        int traceNode = end;
        while (traceNode != 0) {
            trace.add(traceNode);
            traceNode = dp[traceNode];
        }
        Collections.reverse(trace);

        StringBuilder sb = new StringBuilder();
        sb.append(distance[end]).append("\n");
        sb.append(trace.size()).append("\n");
        for (Integer n : trace) {
            sb.append(n).append(" ");
        }

        System.out.println(sb);
    }

    static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        distance[s] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (visited[now.node]) {
                continue;
            }
            visited[now.node] = true;
            for (Node next : cost.get(now.node)) {
                if (distance[next.node] > distance[now.node] + next.cost) {
                    distance[next.node] = distance[now.node] + next.cost;
                    dp[next.node] = now.node;
                    pq.add(new Node(next.node, distance[next.node]));
                }
            }
        }
    }
}
