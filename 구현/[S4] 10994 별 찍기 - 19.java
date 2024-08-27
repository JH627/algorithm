import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.print("*");
            return;
        }

        int len = 1 + 4 * (N - 1);
        arr = new char[len][len];

        for (char[] chars : arr) {
            Arrays.fill(chars, ' ');
        }

        fill(0, len);

        StringBuilder sb = new StringBuilder();
        for (char[] chars : arr) {
            for (char c : chars) {
                sb.append(c);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void fill(int start, int len) {
        if (len < 0) {
            return;
        }

        for (int i = start; i < len; i++) {
            arr[start][i] = '*';
            arr[len - 1][i] = '*';
            arr[i][start] = '*';
            arr[i][len - 1] = '*';
        }

        fill(start + 2, len - 2);
    }
}
