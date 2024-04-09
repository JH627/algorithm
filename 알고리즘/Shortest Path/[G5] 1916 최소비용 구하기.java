import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Node>> cost;
    static Integer[] dist;
    static boolean[] visited;
    static int n, m;

    static class Node implements Comparable<Node> {
        int num, weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        cost = new ArrayList<>();
        dist = new Integer[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            cost.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }

        m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            cost.get(sc.nextInt()).add(new Node(sc.nextInt(), sc.nextInt()));
        }

        int s = sc.nextInt();
        dijkstra(s);

        int e = sc.nextInt();
        System.out.println(dist[e]);
    }

    static private void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[s] = 0;
        pq.add(new Node(s, 0));

        Node now;
        while(!pq.isEmpty()) {
            now = pq.poll();

            if (!visited[now.num]) {
                visited[now.num] = true;
                for (Node next : cost.get(now.num)) {
                    if (dist[next.num] > dist[now.num] + next.weight){
                        dist[next.num] = dist[now.num] + next.weight;
                        pq.add(new Node(next.num, dist[next.num]));
                    }
                }
            }
        }
    }
}