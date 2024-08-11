import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.util.Collections.swap;

public class Main {

    static class Info {
        int min;
        int max;

        public Info(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    static int T, N, K;
    static Info[] segTree;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            segTree = new Info[4 * N];
            arr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                arr.add(i);
            }

            makeSegTree(1, 0, N - 1);

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                switch (op) {
                    case 0:
                        swap(arr, start, end);
                        update(1, 0, N - 1, start, arr.get(start));
                        update(1, 0, N - 1, end, arr.get(end));
                        break;
                    case 1:
                        Info checked = check(1, 0, N - 1, start, end);
                        boolean isNice = checked.min == start && checked.max == end;
                        sb.append(isNice ? "YES\n" : "NO\n");
                        break;
                }
            }
        }

        System.out.print(sb);
    }

    static Info makeSegTree(int idx, int s, int e) {
        if (s == e) {
            return (segTree[idx] = new Info(arr.get(s), arr.get(s)));
        }

        int mid = (s + e) / 2;
        Info left = makeSegTree(idx * 2, s, mid);
        Info right = makeSegTree(idx * 2 + 1, mid + 1, e);
        return (segTree[idx] = merge(left, right));
    }

    static Info update(int idx, int s, int e, int i, int x) {
        if (s > i || e < i) {
            return segTree[idx];
        }

        if (s == e) {
            segTree[idx].min = x;
            segTree[idx].max = x;
            return segTree[idx];
        }

        int mid = (s + e) / 2;
        Info left = update(idx * 2, s, mid, i, x);
        Info right = update(idx * 2 + 1, mid + 1, e, i, x);
        return (segTree[idx] = merge(left, right));
    }

    static Info check(int idx, int ts, int te, int l, int r) {
        if (ts >= l && te <= r) {
            return segTree[idx];
        }

        if (ts > r || te < l) {
            return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        int mid = (ts + te) / 2;
        Info left = check(idx * 2, ts, mid, l, r);
        Info right = check(idx * 2 + 1, mid + 1, te, l, r);
        return merge(left, right);
    }

    static Info merge(Info a, Info b) {
        return new Info(Math.min(a.min, b.min), Math.max(a.max, b.max));
    }
}
