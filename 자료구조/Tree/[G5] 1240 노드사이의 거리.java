import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static int N, M;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, cost));
            graph.get(v).add(new Node(u, cost));
        }

        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(find(a, b)).append("\n");
        }

        System.out.print(sb);
    }

    static int find(int a, int b) {
        boolean[] visited = new boolean[N + 1];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(a, 0));
        visited[a] = true;
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.node == b) {
                return now.cost;
            }

            for (Node next : graph.get(now.node)) {
                if (visited[next.node]) {
                    continue;
                }
                visited[next.node] = true;
                q.add(new Node(next.node, now.cost + next.cost));
            }
        }

        return 0;
    }
}
