import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17484_진우의달여행(Small) {

    static BufferedReader br;
    static StringTokenizer st;

    static final int[] ADD_ROW = {1, 1, 1};
    static final int[] ADD_COL = {-1, 0, 1};

    static int min = Integer.MAX_VALUE;

    static int rowSize, colSize;
    static int[][] map;
    static int[][][] minPrice;

    public static void main(String[] args) throws IOException {
        init();
        for (int col = 0; col < colSize; col++) {
            findMinPrice(3, map[0][col], 0, col);
        }
        System.out.print(min);
    }

    static void findMinPrice(int prevMove, int sum, int row, int col) {
        if (sum >= minPrice[row][col][prevMove]) {
            return;
        }

        minPrice[row][col][prevMove] = sum;
        if (row == rowSize - 1) {
            min = Math.min(sum, min);
            return;
        }

        for (int deltaIndex = 0; deltaIndex < 3; deltaIndex++) {
            if (deltaIndex == prevMove) {
                continue;
            }

            int newRow = row + ADD_ROW[deltaIndex];
            int newCol = col + ADD_COL[deltaIndex];

            if (newCol < 0 || newCol >= colSize) {
                continue;
            }

            int nextSum = sum + map[newRow][newCol];
            if (nextSum >= min) {
                continue;
            }

            findMinPrice(deltaIndex, nextSum, newRow, newCol);
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        minPrice = new int[rowSize][colSize][4];
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                Arrays.fill(minPrice[row][col], Integer.MAX_VALUE);
            }
        }
    }
}
