import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_2665_미로만들기 {

    static BufferedReader br;

    static final int INF = Integer.MAX_VALUE;
    static final int[] ADD_ROW = {-1, 0, 1, 0};
    static final int[] ADD_COL = {0, 1, 0, -1};

    static class Info implements Comparable<Info> {
        int row, col, count;

        public Info(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        @Override
        public int compareTo(Info o) {
            return this.count - o.count;
        }
    }

    static int rowSize, colSize;
    static boolean[][] map;
    static int[][] breakCount;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findMinChangeCount());
    }

    static int findMinChangeCount() {
        PriorityQueue<Info> toVisit = new PriorityQueue<>();
        toVisit.add(new Info(0, 0, 0));

        while (!toVisit.isEmpty()) {
            Info now = toVisit.poll();

            if (isFinishPoint(now.row, now.col)) {
                return now.count;
            }

            int newRow, newCol;
            for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
                newRow = now.row + ADD_ROW[deltaIndex];
                newCol = now.col + ADD_COL[deltaIndex];

                if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                    continue;
                }

                boolean isDarkRoom = !map[newRow][newCol];
                int nextCount = now.count + (isDarkRoom ? 1 : 0);
                if (breakCount[newRow][newCol] > nextCount) {
                    toVisit.add(new Info(newRow, newCol, nextCount));
                    breakCount[newRow][newCol] = nextCount;
                }
            }
        }

        return -1;
    }

    static boolean isFinishPoint(int row, int col) {
        return row == rowSize - 1 && col == colSize - 1;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        rowSize = Integer.parseInt(br.readLine());
        colSize = rowSize;
        map = new boolean[rowSize][colSize];
        breakCount = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            String line = br.readLine();
            for (int col = 0; col < colSize; col++) {
                map[row][col] = line.charAt(col) == '1';
            }

            Arrays.fill(breakCount[row], INF);
        }

    }
}
