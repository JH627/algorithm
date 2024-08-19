import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    static int N;
    static int[] arr, sorted, segTree;
    static HashMap<Integer, Integer> comp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        sorted = new int[N];
        segTree = new int[4 * N];
        comp = new HashMap<>();

        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
            sorted[n] = arr[n];
        }

        Arrays.sort(sorted);
        compression();

        StringBuilder sb = new StringBuilder();

        for (int n = 0; n < N; n++) {
            Integer coordinate = comp.get(arr[n]);
            update(1, 1, N, coordinate);
            sb.append(1 + query(1, coordinate + 1, N, 1, N)).append("\n");
        }

        System.out.println(sb);
    }

    static void compression() {
        int clock = 1;
        for (int n = 0; n < N; n++) {
            comp.put(sorted[n], clock++);
        }
    }

    static int update(int idx, int s, int e, int i) {
        if (i < s || i > e) {
            return segTree[idx];
        }
        if (s == e) {
            segTree[idx] += 1;
            return segTree[idx];
        }

        int mid = (s + e) / 2;
        int left = update(idx * 2, s, mid, i);
        int right = update(idx * 2 + 1, mid + 1, e, i);
        return segTree[idx] = left + right;
    }

    static int query(int idx, int s, int e, int qs, int qe) {
        if (qe < s || e < qs) {
            return 0;
        }
        if (s <= qs && qe <= e) {
            return segTree[idx];
        }

        int mid = (qs + qe) / 2;
        int left = query(idx * 2, s, e, qs, mid);
        int right = query(idx * 2 + 1, s, e, mid + 1, qe);

        return left + right;
    }
}
