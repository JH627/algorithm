import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static long[] arr;
    static long[] segTree;

    static final long DIVIDE = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        segTree = new long[4 * N];

        init(1, 0, N - 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            switch (Integer.parseInt(st.nextToken())) {
                case 1:
                    int idx = Integer.parseInt(st.nextToken());
                    int x = Integer.parseInt(st.nextToken());
                    update(1, 0, N - 1, idx - 1, x);
                    break;
                case 2:
                    int l = Integer.parseInt(st.nextToken());
                    int r = Integer.parseInt(st.nextToken());
                    sb.append(find(1, 0, N - 1, l - 1, r - 1)).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }

    static long init(int idx, long s, long e) {
        if (s == e) {
            return segTree[idx] = arr[(int) s];
        }

        long mid = (s + e) / 2;
        long a = init(idx * 2, s, mid);
        long b = init(idx * 2 + 1, mid + 1, e);
        return segTree[idx] = (a * b) % DIVIDE;
    }

    static long find(int idx, int ts, int te, int l, int r) {
        if (ts >= l && te <= r) {
            return segTree[idx];
            }

        if (ts > r || te < l) {
            return 1;
        }

        int mid = (ts + te) / 2;
        long a = find(idx * 2, ts, mid, l, r);
        long b = find(idx * 2 + 1, mid + 1, te, l, r);
        return (a * b) % DIVIDE;
    }

    static long update(int idx, int s, int e, int i, int x) {
        if (s > i || e < i) {
            return segTree[idx];
        }
        if (s == e) {
            return (segTree[idx] = x);
        }

        int mid = (s + e) / 2;
        long a = update(idx * 2, s, mid, i, x);
        long b = update(idx * 2 + 1, mid + 1, e, i, x);
        return (segTree[idx] = (a * b) % DIVIDE);
    }
}
