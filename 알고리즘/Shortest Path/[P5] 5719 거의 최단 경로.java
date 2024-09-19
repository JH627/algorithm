import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int node;
        int cost;
        boolean removed;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
            removed = false;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, M, S, D;
    static int[] distance;
    static ArrayList<ArrayList<Integer>> dp;
    static ArrayList<ArrayList<Node>> cost;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            init();

            Arrays.fill(distance, INF);
            dijkstra();

            removePath();

            Arrays.fill(distance, INF);
            dijkstra();

            sb.append((distance[D] == INF) ? -1 : distance[D]).append("\n");
        }

        System.out.print(sb);
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));

        distance[S] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (distance[now.node] < now.cost) {
                continue;
            }

            for (Node next : cost.get(now.node)) {
                if (next.removed) {
                    continue;
                }

                if (distance[now.node] + next.cost > distance[next.node]) {
                    continue;
                }
                if (distance[now.node] + next.cost < distance[next.node]) {
                    dp.get(next.node).clear();
                    pq.add(new Node(next.node, distance[now.node] + next.cost));
                }
                distance[next.node] = distance[now.node] + next.cost;
                dp.get(next.node).add(now.node);
            }
        }
    }

    static void removePath() {
        boolean[] visited = new boolean[N];
        Queue<Integer> q = new LinkedList<>();
        q.add(D);
        while (!q.isEmpty()) {
            int now = q.poll();

            if (visited[now]) {
                continue;
            }
            visited[now] = true;

            for (Integer prev : dp.get(now)) {
                int size = cost.get(prev).size();
                for (int i = 0; i < size; i++) {
                    Node nowNode = cost.get(prev).get(i);
                    if (nowNode.node == now) {
                        cost.get(prev).get(i).removed = true;
                        break;
                    }
                }
                if (!visited[prev]) {
                    q.add(prev);
                }
            }
        }
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        distance = new int[N];
        dp = new ArrayList<>();
        cost = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            dp.add(new ArrayList<>());
            cost.add(new ArrayList<>());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            cost.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }
}
