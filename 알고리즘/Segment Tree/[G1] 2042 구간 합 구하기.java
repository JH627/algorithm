import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static long[] arr, seg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        seg = new long[4 * N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        setTree(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        int a, b;
        long c;
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Long.parseLong(st.nextToken());
            switch (a) {
                case 1:
                    updateTree(0, N - 1, 1, b - 1, c - arr[b - 1]);
                    break;
                case 2:
                    sb.append(getSum(0, N - 1, 1, b - 1, c - 1)).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }

    static long setTree(int start, int end, int node) {
        if (start == end) {
            return seg[node] = arr[end];
        }
        int mid = (start + end) / 2;
        return seg[node] = setTree(start, mid, 2 * node) + setTree(mid + 1, end, 2 * node + 1);
    }

    static void updateTree(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) {
            return;
        }

        seg[node] += diff;
        if (start == end) {
            arr[index] = seg[node];
            return;
        }
        int mid = (start + end) / 2;
        updateTree(start, mid, node * 2, index, diff);
        updateTree(mid + 1, end, node * 2 + 1, index, diff);
    }

    static long getSum(int start, int end, int node, int left, long right) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && right >= end) {
            return seg[node];
        }
        int mid = (start + end) / 2;
        return getSum(start, mid, node * 2, left, right) + getSum(mid + 1, end, node * 2 + 1, left, right);
    }
}