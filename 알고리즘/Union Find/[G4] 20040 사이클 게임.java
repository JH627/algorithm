import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int a, b;
        int count = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            if (count == 0) {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (!union(Math.min(a, b), Math.max(a, b))) {
                    count = i + 1;
                }
            }
        }

        System.out.println(count);
    }

    static int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) {
            return false;
        }
        parent[py] = px;
        return true;
    }
}
