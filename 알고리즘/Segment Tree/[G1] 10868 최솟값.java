import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr, segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        segTree = new int[4 * N];

        for (int n = 1; n <= N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        init(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(find(1,1, N, a, b)).append("\n");
        }

        System.out.print(sb);
    }

    static void init(int idx, int s, int e) {
        if (s == e) {
            segTree[idx] = arr[s];
            return;
        }

        int mid = (s + e) / 2;
        init(2 * idx, s, mid);
        init(2 * idx + 1, mid + 1, e);

        segTree[idx] = Math.min(segTree[2 * idx], segTree[2 * idx + 1]);
    }

    static int find(int idx, int ts, int te, int l, int r) {
        if (te < l || ts > r) {
            return Integer.MAX_VALUE;
        }
        if (ts >= l && te <= r) {
            return segTree[idx];
        }

        int mid = (ts + te) / 2;
        int left = find(2 * idx, ts, mid, l, r);
        int right = find(2 * idx + 1, mid + 1, te, l, r);

        return Math.min(left, right);
    }
}
