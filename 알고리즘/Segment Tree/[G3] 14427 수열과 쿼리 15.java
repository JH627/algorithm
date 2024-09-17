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
        arr = new int[N + 1];
        segTree = new int[4 * (N + 1)];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N + 1; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        makeSegTree(1, 1, N);

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "1":
                    int i = Integer.parseInt(st.nextToken());
                    int x = Integer.parseInt(st.nextToken());
                    update(1, 1, N, i, x);
                    break;
                case "2":
                    sb.append(find()).append("\n");
                    break;
            }
        }

        System.out.print(sb);
    }

    static void makeSegTree(int idx, int s, int e) {
        if (s == e) {
            segTree[idx] = s;
            return;
        }

        int mid = (s + e) / 2;
        makeSegTree(idx * 2, s, mid);
        makeSegTree(idx * 2 + 1, mid + 1, e);

        segTree[idx] = findMin(segTree[idx * 2], segTree[idx * 2 + 1]);
    }

    static int find() {
        return segTree[1];
    }

    static void update(int idx, int s, int e, int i, int x) {
        if (s > i || e < i) {
            return;
        }

        if (s == e) {
            arr[i] = x;
            segTree[idx] = i;
            return;
        }

        int mid = (s + e) / 2;
        update(idx * 2, s, mid, i, x);
        update(idx * 2 + 1, mid + 1, e, i, x);

        segTree[idx] = findMin(segTree[idx * 2], segTree[idx * 2 + 1]);
    }

    static int findMin(int a, int b) {
        if (arr[a] == arr[b]) {
            return Math.min(a, b);
        }
        return (arr[a] < arr[b]) ? a : b;
    }
}
