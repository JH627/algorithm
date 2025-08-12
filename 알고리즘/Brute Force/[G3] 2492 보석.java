import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2492_보석 {

    static BufferedReader br;
    static StringTokenizer st;

    static class Point {
        int row, col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int rowSize, colSize;
    static int goldCount, areaSize;
    static Point[] golds;
    static int maxGoldCount, maxRow, maxCol;

    public static void main(String[] args) throws IOException {
        init();
        getMaxGoldCount();
        System.out.printf("%d %d\n%d", maxCol, maxRow + areaSize, maxGoldCount);
    }

    static void getMaxGoldCount() {
        for (Point gx : golds) {
            for (Point gy : golds) {
                int startCol = gx.col;
                if (startCol < 0) {
                    startCol = 0;
                }
                if (startCol + areaSize > colSize) {
                    startCol = colSize - areaSize;
                }

                int startRow = gy.row;
                if (startRow < 0) {
                    startRow = 0;
                }
                if (startRow + areaSize > rowSize) {
                    startRow = rowSize - areaSize;
                }

                int endRow = startRow + areaSize;
                int endCol = startCol + areaSize;

                int count = getNearGoldCount(startRow, startCol, endRow, endCol);
                if (count > maxGoldCount) {
                    maxGoldCount = count;
                    maxRow = startRow;
                    maxCol = startCol;
                }
            }
        }
    }

    static int getNearGoldCount(int startRow, int startCol, int endRow, int endCol) {
        int count = 0;
        for (Point gold : golds) {
            if (gold.row >= startRow && gold.col >= startCol && gold.row <= endRow && gold.col <= endCol) {
                count++;
            }
        }
        return count;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        goldCount = Integer.parseInt(st.nextToken());
        areaSize = Integer.parseInt(st.nextToken());

        golds = new Point[goldCount];
        for (int gold = 0; gold < goldCount; gold++) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            golds[gold] = new Point(row, col);
        }

        maxGoldCount = 0;
        maxRow = 0;
        maxCol = 0;
    }
}
