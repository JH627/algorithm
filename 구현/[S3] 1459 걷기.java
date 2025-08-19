import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1459_걷기 {

    static BufferedReader br;
    static StringTokenizer st;

    static int row, col, straightTime, diagonalTime;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(getTime());
    }

    static long getTime() {
        long time = 0;
        if (straightTime * 2 >= diagonalTime) {
            time += (long) diagonalTime * Math.min(row, col);
        }
        else {
            time += 2 * ((long) straightTime * Math.min(row, col));
        }
        int minus = Math.min(row, col);
        row -= minus;
        col -= minus;

        if (straightTime * 2 >= diagonalTime * 2) {
            int remainDistance = Math.max(row, col);
            time += (long) (remainDistance / 2) * diagonalTime * 2;
            time += (remainDistance % 2) * straightTime;
        }
        else {
            time += (long) straightTime * Math.max(row, col);
        }

        return time;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        straightTime = Integer.parseInt(st.nextToken());
        diagonalTime = Integer.parseInt(st.nextToken());
    }
}
