import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6087_레이저통신 {

    static BufferedReader br;
    static StringTokenizer st;

    // 오, 왼, 위, 아래
    static final int[] ADD_ROW = {0, 0, -1, 1};
    static final int[] ARR_COL = {1, -1, 0, 0};
    static final int INF = 100000;

    static class Point implements Comparable<Point> {
        int row, col;
        int count;
        int prevDirection;

        public Point(int row, int col, int count, int prevDirection) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.prevDirection = prevDirection;
        }

        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }

    static int rowSize, colSize;
    static boolean[][] map;
    static int[][][] dist;
    static int startRow, startCol;
    static int endRow, endCol;

    public static void main(String[] args) throws IOException {
        init();

        int minPathLength = findMinPathLength(startRow, startCol);

        System.out.print(minPathLength);
    }

    static int findMinPathLength(int startRow, int startCol) {
        dist[startRow][startCol][0] = 0;
        dist[startRow][startCol][1] = 0;
        dist[startRow][startCol][2] = 0;
        dist[startRow][startCol][3] = 0;

        PriorityQueue<Point> toVisit = new PriorityQueue<>();
        toVisit.add(new Point(startRow, startCol, 0, 0));
        toVisit.add(new Point(startRow, startCol, 0, 1));
        toVisit.add(new Point(startRow, startCol, 0, 2));
        toVisit.add(new Point(startRow, startCol, 0, 3));

        while (!toVisit.isEmpty()) {
            Point now = toVisit.poll();

            if (now.count > dist[now.row][now.col][now.prevDirection]) {
                continue;
            }

            if (now.row == endRow && now.col == endCol) {
                return now.count;
            }

            for (int direction = 0; direction < 4; direction++) {
                int newRow = now.row + ADD_ROW[direction];
                int newCol = now.col + ARR_COL[direction];

                if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                    continue;
                }

                if (map[newRow][newCol]) {
                    continue;
                }

                if (now.prevDirection == direction) {
                    if (dist[newRow][newCol][direction] > dist[now.row][now.col][direction]) {
                        dist[newRow][newCol][direction] = dist[now.row][now.col][direction];
                        toVisit.add(new Point(newRow, newCol, dist[now.row][now.col][direction], direction));
                    }
                }
                else {
                    if (now.prevDirection / 2 != direction / 2) {
                        if (dist[newRow][newCol][direction] > dist[now.row][now.col][now.prevDirection] + 1) {
                            dist[newRow][newCol][direction] = dist[now.row][now.col][now.prevDirection] + 1;
                            toVisit.add(new Point(newRow, newCol, dist[newRow][newCol][direction], direction));
                        }
                    }
                }
            }
        }

        return 0;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        startRow = -1;
        startCol = -1;

        map = new boolean[rowSize][colSize];
        dist = new int[rowSize][colSize][4];
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                Arrays.fill(dist[row][col], INF);
            }
        }

        for (int row = 0; row < rowSize; row++) {
            String rowStr = br.readLine();
            for (int col = 0; col < colSize; col++) {
                if (rowStr.charAt(col) == '*') {
                    map[row][col] = true;
                }
                else if (rowStr.charAt(col) == 'C'){
                    if (startRow == -1) {
                        startRow = row;
                        startCol = col;
                    }
                    else {
                        endRow = row;
                        endCol = col;
                    }
                }
            }
        }
    }
}
