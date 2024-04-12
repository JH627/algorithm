import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
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

    static int n;
    static int m;
    static int[] parent;

    static ArrayList<Edge> arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new ArrayList<>();
        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            arr.add(new Edge(a, b, c));
        }

        Collections.sort(arr);

        int sum = 0;
        int edgeCount = 0;
        for (Edge edge : arr) {
            if (find(edge.start) != find(edge.end)) {
                sum += edge.cost;
                union(edge.start, edge.end);
                edgeCount++;
            }
            if (edgeCount == n - 1) {
                break;
            }
        }
        System.out.println(sum);
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }
}