import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9626_크로스워드퍼즐 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int rowSize, colSize;
    static int up, left, right, down;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        init();
        fillBoard();
        printBoard();
    }

    static void fillBoard() throws IOException {
        for (int row = up; row < up + rowSize; row++) {
            String line = br.readLine();
            for (int col = left; col < left + colSize; col++) {
                board[row][col] = line.charAt(col - left);
            }
        }
    }

    static void printBoard() {
        sb = new StringBuilder();
        for (int row = 0; row < rowSize + up + down; row++) {
            for (int col = 0; col < colSize + left + right; col++) {
                sb.append(board[row][col]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        up = Integer.parseInt(st.nextToken());
        left = Integer.parseInt(st.nextToken());
        right = Integer.parseInt(st.nextToken());
        down = Integer.parseInt(st.nextToken());

        board = new char[rowSize + up + down][colSize + left + right];
        for (int row = 0; row < rowSize + up + down; row++) {
            char plag = (row % 2 == 0) ? '#' : '.';
            for (int col = 0; col < colSize + left + right; col++) {
                board[row][col] = plag;
                plag = (plag == '#') ? '.' : '#';
            }
        }
    }
}
