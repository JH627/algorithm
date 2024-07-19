import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static HashSet<Integer> set;
    static int T, N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            set = new HashSet<>();
            parent = new int[N + 1];

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                parent[b] = a;
            }

            st = new StringTokenizer(br.readLine());
            int lca = find(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(lca).append("\n");
        }

        System.out.println(sb);
    }

    static int find(int a, int b) {
        while (true) {
            set.add(a);
            if (parent[a] == 0) {
                break;
            }
            a = parent[a];
        }
        while (!set.contains(b)) {
            b = parent[b];
        }
        return b;
    }
}
