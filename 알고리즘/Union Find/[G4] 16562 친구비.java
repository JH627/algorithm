import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[] cost, parent;
    static long sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cost = new int[N];
        parent = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            cost[n] = Integer.parseInt(st.nextToken());
            parent[n] = n;
            sum += cost[n];
        }

        int v, w;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken()) - 1;
            w = Integer.parseInt(st.nextToken()) - 1;
            union(v, w);
        }

        System.out.print((sum <= K) ? sum : "Oh no");
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int v, int w) {
        int pv = find(v);
        int pw = find(w);
        if (pv == pw) {
            return;
        }
        if (cost[pv] > cost[pw]) {
            parent[pv] = pw;
            sum -= cost[pv];
        }
        else {
            parent[pw] = pv;
            sum -= cost[pw];
        }
    }
}
