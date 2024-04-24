import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int v;
        int c;

        public Pair(int v, int c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Pair o) {
            return 0;
        }
    }

    static int T;
    static int n, m, t;
    static int s, g, h;

    static final int MAX = 2000 * 1000 * 2 + 4;

    static int[] dist;
    static ArrayList<ArrayList<Pair>> arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            n = sc.nextInt();
            m = sc.nextInt();
            t = sc.nextInt();

            initArr();

            s = sc.nextInt();
            g = sc.nextInt();
            h = sc.nextInt();

            int a, b, d;
            for (int j = 0; j < m; j++) {
                a = sc.nextInt();
                b = sc.nextInt();
                d = sc.nextInt();

                d = (a == g && b == h) || (a == h && b == g) ? 2 * d - 1 : 2 * d;
                arr.get(a).add(new Pair(b, d));
                arr.get(b).add(new Pair(a, d));
            }

            dijkstra();

            ArrayList<Integer> can = new ArrayList<>();
            for (int j = 0; j < t; j++) {
                can.add(sc.nextInt());
            }
            Collections.sort(can);

            for (Integer x : can) {
                if (dist[x] % 2 == 1) {
                    System.out.printf("%d ", x);
                }
            }

            System.out.println();

            arr.clear();
        }
    }

    private static void initArr() {
        dist = new int[n + 1];
        Arrays.fill(dist, MAX);

        arr = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }
    }

    private static void dijkstra() {
        Queue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(s, 0));
        dist[s] = 0;
        Pair now, next;
        while (!q.isEmpty()) {
            now = q.poll();
            if (dist[now.v] < now.c) {
                continue;
            }

            for (int i = 0; i < arr.get(now.v).size(); i++) {
                next = arr.get(now.v).get(i);
                if (dist[next.v] > now.c + next.c) {
                    dist[next.v] = now.c + next.c;
                    q.add(new Pair(next.v, now.c + next.c));
                }
            }
        }
    }
}