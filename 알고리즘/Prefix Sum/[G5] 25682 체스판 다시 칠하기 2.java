import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static Character[][] arr;
    static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new Character[N + 1][M + 1];
        sum = new int[N + 1][M + 1];

        for (int n = 1; n < N + 1; n++) {
            String s = br.readLine();
            for (int m = 1; m < M + 1; m++) {
                arr[n][m] = s.charAt(m - 1);
            }
        }

        getSum();

        int MIN = Integer.MAX_VALUE;
        for (int i = K; i < N + 1; i++) {
            for (int j = K; j < M + 1; j++) {
                int toChange = sum[i][j] - sum[i][j - K] - sum[i - K][j] + sum[i - K][j - K];
                MIN = Math.min(MIN, toChange);
                MIN = Math.min(MIN, K * K - toChange);
            }
        }

        System.out.print(MIN);
    }

    static void getSum() {
        for (int i = 1; i < N + 1; i++) {
            boolean color = i % 2 != 0;
            for (int j = 1; j < M + 1; j++) {
                sum[i][j] = sum[i][j - 1];
                if (arr[i][j] == 'W' && color) {
                    sum[i][j]++;
                }
                else if (arr[i][j] == 'B' && !color) {
                    sum[i][j]++;
                }
                color = !color;
            }
        }

        for (int i = 1; i < M + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                sum[j][i] += sum[j - 1][i];
            }
        }
    }
}
