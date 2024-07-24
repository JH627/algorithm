import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T = 0;
    static int[] parent, rank;
    static HashSet<Integer> cycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            T++;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            parent = new int[N + 1];
            rank = new int[N + 1];
            for (int i = 0; i < N + 1; i++) {
                parent[i] = i;
            }

            cycle = new HashSet<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (union(a, b)) {
                    cycle.add(a);
                    cycle.add(b);
                }
            }

            for (int i = 1; i < N + 1; i++) {
                if (cycle.contains(i)) {
                    parent[i] = find(i);
                    cycle.add(parent[i]);
                }
            }

            int unionSet = 0;
            for (int i = 1; i < N + 1; i++) {
                if (!cycle.contains(parent[i]) && parent[i] == i) {
                    unionSet++;
                }
            }
            sb.append("Case ").append(T).append(": ");
            if (unionSet == 0) {
                sb.append("No trees.\n");
            }
            else if (unionSet == 1) {
                sb.append("There is one tree.\n");
            }
            else {
                sb.append("A forest of ").append(unionSet).append(" trees.\n");
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

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return true;
        }

        if (rank[pa] == rank[pb]) {
            parent[pb] = pa;
            rank[pa]++;
        }
        else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        }
        else {
            parent[pa] = pb;
        }
        return false;
    }
}
