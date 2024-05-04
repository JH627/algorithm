import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] board;
    static ArrayList<Pair> zeros;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];
        zeros = new ArrayList<>();
        sb = new StringBuilder();

        StringTokenizer st;
        int n;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                n = Integer.parseInt(st.nextToken());
                if (n == 0) {
                    zeros.add(new Pair(i, j));
                }
                board[i][j] = n;
            }
        }

        if (zeros.size() != 0) {
            bt(0);
        }

        for (int[] nums : board) {
            for (int num : nums) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static boolean bt(int s) {
        if (s == zeros.size()) {
            return true;
        }

        int x = zeros.get(s).x;
        int y = zeros.get(s).y;
        boolean possible = false;
        for (int i = 1; i <= 9; i++) {
            if (validate(x, y, i)) {
                possible = true;
                board[x][y] = i;

                // 성공
                if(bt(s + 1)) {
                    break;
                }

                //실패
                possible = false;
                board[x][y] = 0;
            }
        }
        return possible;
    }


    static boolean validate(int x, int y, int c) {
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == c) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == c) {
                return false;
            }
        }

        x = (x / 3) * 3;
        y = (y / 3) * 3;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (board[i][j] == c) {
                    return false;
                }
            }
        }

        return true;
    }
}