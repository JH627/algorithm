import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int M;
    static int[] n, a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        n = new int[M];
        a = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            n[m] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            a[m] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            sb.append(binSearch(m)).append(" ");
        }

        System.out.print(sb);
    }

    static long binSearch(int idx) {
        if (a[idx] == 0) {
            return a[idx];
        }
        if (n[idx] == 1) {
            return 1;
        }
        if (n[idx] >= a[idx]) {
            return a[idx];
        }

        long l = n[idx];
        long r = a[idx];
        while (l <= r) {
            long m = (l + r) / 2;

            if ((n[idx] + 1) + (m - n[idx]) + ((m - n[idx]) / (n[idx] - 1)) >= a[idx]) {
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }

        return l;
    }
}
