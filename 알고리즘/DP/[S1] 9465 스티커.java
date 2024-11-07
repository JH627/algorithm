import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][N + 2];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 2; j < N + 2; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = Integer.MIN_VALUE;
            for (int n = 2; n < N + 2; n++) {
                arr[0][n] = Math.max(arr[1][n - 2] + arr[0][n], arr[1][n - 1] + arr[0][n]);
                arr[1][n] = Math.max(arr[0][n - 2] + arr[1][n], arr[0][n - 1] + arr[1][n]);
                max = Math.max(max, arr[0][n]);
                max = Math.max(max, arr[1][n]);
            }

            sb.append(max).append("\n");
        }

        System.out.print(sb);
    }
}
