import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] cost, happy;
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N];
        happy = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            cost[n] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            happy[n] = Integer.parseInt(st.nextToken());
        }

        ns(0, 100, 0);

        System.out.println(MAX);
    }

    static void ns(int cnt, int c, int h) {
        if (c <= 0) {
            return;
        }
        MAX = Math.max(MAX, h);
        if (cnt == N) {
            return;
        }

        ns(cnt + 1, c - cost[cnt], h + happy[cnt]);
        ns(cnt + 1, c, h);
    }
}