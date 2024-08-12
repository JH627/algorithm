import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[][] arr;
    static final int DIV = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][10];

        Arrays.fill(arr[0], 1);

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    arr[i][j] += arr[i - 1][k];
                    arr[i][j] %= DIV;
                }
            }
        }

        System.out.println(arr[N][0] % DIV);
    }
}
