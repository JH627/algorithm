import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

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


    static int m, n;
    static int x, y, z;
    static int sum;
    static ArrayList<Edge> arr;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            sum = 0;
            arr = new ArrayList<>();
            parent = new int[m + 1];
            for (int i = 0; i < m + 1; i++) {
                parent[i] = i;
            }

            if (m == 0 && n == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                z = Integer.parseInt(st.nextToken());

                sum += z;
                arr.add(new Edge(x, y, z));
                arr.add(new Edge(y, x, z));
            }

            Collections.sort(arr);

            int cnt = 0;
            int size = arr.size();
            Edge edge;
            for (int i = 0; i < size; i++) {
                if (cnt == m - 1) {
                    break;
                }
                edge = arr.get(i);
                if (find(edge.start) != find(edge.end)) {
                    union(edge.start, edge.end);
                    cnt++;
                    sum -= edge.cost;
                }
            }

            System.out.println(sum);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[b] = a;
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }

        return find(parent[a]);
    }
}