import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] day, page;
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        day = new int[M];
        page = new int[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st.nextToken());
            page[i] = Integer.parseInt(st.nextToken());
        }

        ns(0, 0, 0);

        System.out.println(MAX);
    }

    static void ns(int cnt, int d, int p) {
        if (d > N) {
            return;
        }
        MAX = Math.max(MAX, p);
        if (cnt == M) {
            return;
        }
        ns(cnt + 1, d + day[cnt], p + page[cnt]);
        ns(cnt + 1, d, p);
    }
}