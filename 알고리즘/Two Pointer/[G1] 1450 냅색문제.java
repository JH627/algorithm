import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static int[] arr;
    static ArrayList<Integer> a = new ArrayList<>();
    static ArrayList<Integer> b = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        comb(a, 0, N / 2, 0);
        comb(b, N / 2, N, 0);

        Collections.sort(a);
        Collections.sort(b);

        int sum = 0;
        for (Integer now : a) {
            if (now > C) {
                break;
            }
            sum += search(C - now) + 1;
        }

        System.out.println(sum);
    }

    static void comb(ArrayList<Integer> list, int start, int end, int sum) {
        if (sum > C) {
            return;
        }
        if (start == end) {
            list.add(sum);
            return;
        }
        comb(list, start + 1, end, sum + arr[start]);
        comb(list, start + 1, end, sum);
    }

    static int search(int bound) {
        int l = 0;
        int r = b.size() - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (b.get(m) > bound) {
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }

        return r;
    }
}
