import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] power;
    static boolean[] participated;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        power = new int[N + 1][N + 1];
        participated = new boolean[N + 1];

        for (int n = 1; n < N + 1; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m < N + 1; m++) {
                power[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        for (int n = 1; n < N + 1; n++) {
            for (int m = n + 1; m < N + 1; m++) {
                power[n][m] += power[m][n];
            }
        }

        bt(1, 0);

        System.out.println(MIN);
    }

    static void bt(int n, int depth) {
        if (depth == N / 2) {
            MIN = Math.min(comparePower(), MIN);
            return;
        }
        if (N - n < N / 2 - depth) {
            return;
        }
        for (int i = n; i < N + 1; i++) {
            if (!participated[i]) {
                participated[i] = true;

                bt(i, depth + 1);

                if (MIN == 0) {
                    return;
                }
                participated[i] = false;
            }
        }
    }

    static int comparePower() {
        int teamA = 0;
        int teamB = 0;
        for (int i = 1; i < N + 1; i++) {
            for (int j = i; j < N + 1; j++) {
                if (participated[i] && participated[j]) {
                    teamA += power[i][j];
                }
                else if (!participated[i] && !participated[j]) {
                    teamB += power[i][j];
                }
            }
        }
        return Math.abs(teamA - teamB);
    }
}
