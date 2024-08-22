import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int T, N, M;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                A[n] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                B[m] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B);

            sb.append(find()).append("\n");
        }

        System.out.println(sb);
    }

    static int find() {
        int ret = 0;
        for (int n = 0; n < N; n++) {
            ret += bound(A[n] - 1);
        }
        return ret;
    }

    static int bound(int x) {
        int l = 0;
        int r = M - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            int mid = B[m];
            if (x < mid) {
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }

        return l;
    }
}
