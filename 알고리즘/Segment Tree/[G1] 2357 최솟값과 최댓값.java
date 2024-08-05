import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr, minTree, maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        minTree = new int[4 * N];
        maxTree = new int[4 * N];

        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        init(1, 0, N - 1, true);
        init(1, 0, N - 1, false);

        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            sb.append(find(1, 0, N - 1, l - 1, r - 1, false)).append(" ");
            sb.append(find(1, 0, N - 1, l - 1, r - 1, true)).append("\n");
        }

        System.out.print(sb);
    }


    static int init(int idx, int s, int e, boolean isMax) {
        if (s == e) {
            return isMax ? (maxTree[idx] = arr[s]) : (minTree[idx] = arr[s]);
        }

        int mid = (s + e) / 2;
        int a = init(idx * 2, s, mid, isMax);
        int b = init(idx * 2 + 1, mid + 1, e, isMax);
        return isMax ? (maxTree[idx] = Math.max(a, b)) : (minTree[idx] = Math.min(a, b));
    }

    static int find(int idx, int ts, int te, int l, int r, boolean isMax) {
        if (ts >= l && te <= r) {
            return isMax ? maxTree[idx] : minTree[idx];
        }

        if (ts > r || te < l) {
            return isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        int mid = (ts + te) / 2;
        int a = find(idx * 2, ts, mid, l, r, isMax);
        int b = find(idx * 2 + 1, mid + 1, te, l, r, isMax);
        return isMax ? Math.max(a, b) : Math.min(a, b);
    }

//    static int update(int idx, int s, int e, int i, int x, boolean isMax) {
//        if (s > i || e < i) {
//            return isMax ? maxTree[idx] : minTree[idx];
//        }
//        if (s == e) {
//            return isMax ? (maxTree[idx] = x) : (minTree[idx] = x);
//        }
//
//        int mid = (s + e) / 2;
//        int a = init(idx * 2, s, mid, isMax);
//        int b = init(idx * 2 + 1, mid + 1, e, isMax);
//        return isMax ? (maxTree[idx] = Math.max(a, b)) : (minTree[idx] = Math.min(a, b));
//    }
}
