import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] weight;
    static boolean[][] possible;
    static final int MAX = 40000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        possible = new boolean[N + 1][MAX + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        ns(0, 0);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(possible[N][Integer.parseInt(st.nextToken())] ? "Y" : "N").append(" ");
        }

        System.out.println(sb);
    }

    static void ns(int cnt, int w) {
        if (possible[cnt][w]) {
            return;
        }
        possible[cnt][w] = true;
        if (cnt == N) {
            return;
        }

        ns(cnt + 1, w);
        ns(cnt + 1, w + weight[cnt]);
        ns(cnt + 1, Math.abs(w - weight[cnt]));
    }
}