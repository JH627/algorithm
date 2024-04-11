import java.util.*;

public class Main {
    static class Edge {
        int end;
        int cost;

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static int n, m, w;
    static int dist[];
    static ArrayList<ArrayList<Edge>> arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for (int c = 0; c < tc; c++) {
            n = sc.nextInt();
            m = sc.nextInt();
            w = sc.nextInt();
            arr = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                arr.add(new ArrayList<>());
            }

            int s, e, t;
            for (int i = 0; i < m + w; i++) {
                s = sc.nextInt();
                e = sc.nextInt();
                t = sc.nextInt();
                if (i < m) {
                    arr.get(s).add(new Edge(e, t));
                    arr.get(e).add(new Edge(s, t));
                }
                else {
                    arr.get(s).add(new Edge(e, -t));
                }
            }

            boolean isPossible = bellmanFord();

            System.out.println((isPossible) ? "YES": "NO");
        }
    }

    private static boolean bellmanFord() {
        dist = new int[n + 1];

        boolean updated = false;

        for (int i = 1; i <= n; i++) {
            updated = false;
            for (int j = 1; j <= n; j++) {
                for (Edge edge : arr.get(j)) {
                    if (dist[edge.end] > dist[j] + edge.cost) {
                        dist[edge.end] = dist[j] + edge.cost;
                        updated = true;
                    }
                }
            }
            if (i == n) {
                return updated;
            }
            if (!updated) {
                break;
            }
        }
        return false;
    }
}