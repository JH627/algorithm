import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] board;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N];

        nQueen(0);

        System.out.println(cnt);
    }

    static void nQueen(int n) {
        if (n == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[n] = i;
            if (validate(n)) {
                nQueen(n + 1);
            }
        }
    }

    static boolean validate(int n) {
        for (int i = 0; i < n; i++) {
            if (Math.abs(board[n] - board[i]) == n - i || board[n] == board[i]) {
                return false;
            }
        }
        return true;
    }
}