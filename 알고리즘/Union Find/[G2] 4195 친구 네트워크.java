import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int T, F;
    static Map<String, Integer> m;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            F = Integer.parseInt(br.readLine());
            String a, b;
            int clock = 0;
            init();
            for (int f = 0; f < F; f++) {
                st = new StringTokenizer(br.readLine());
                a = st.nextToken();
                b = st.nextToken();
                m.put(a, m.getOrDefault(a, clock++));
                m.put(b, m.getOrDefault(b, clock++));
                int an = m.get(a);
                int bn = m.get(b);
                sb.append(union(Math.min(an, bn), Math.max(an, bn))).append("\n");
            }
        }

        System.out.println(sb);
    }

    static int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    static int union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pb] = pa;
            rank[pa] += rank[pb];
        }
        return rank[pa];
    }

    static void init() {
        m = new HashMap<>();
        parent = new int[2 * F];
        rank = new int[2 * F];
        for (int i = 0; i < 2 * F; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
}
