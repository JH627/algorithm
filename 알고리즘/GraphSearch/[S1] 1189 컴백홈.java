import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1189_컴백홈 {

    static BufferedReader br;
    static StringTokenizer st;

    static final int[] ADD_ROW = {-1, 0, 1, 0};
    static final int[] ADD_COL = {0, -1, 0, 1};

    static int rowSize, colSize, distanceLimit;
    static boolean[][] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        init();
        findWay(rowSize - 1, 0, 1);
        System.out.print(count);
    }

    static void findWay(int row, int col, int distance) {
        for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
            int newRow = row + ADD_ROW[deltaIndex];
            int newCol = col + ADD_COL[deltaIndex];

            if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                continue;
            }
            if (visited[newRow][newCol]) {
                continue;
            }

            if (newRow == 0 && newCol == colSize - 1) {
                if (distance == distanceLimit - 1) {
                    count++;
                }
                continue;
            }

            // 목적지까지 남은 경로 길이 > 남은 거리제한
            if (Math.abs(newRow) + Math.abs(colSize - 1 - newCol) > distanceLimit - distance) {
                continue;
            }

            visited[newRow][newCol] = true;
            findWay(newRow, newCol, distance + 1);
            visited[newRow][newCol] = false;
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        distanceLimit = Integer.parseInt(st.nextToken());

        visited = new boolean[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            String line = br.readLine();
            for (int col = 0; col < colSize; col++) {
                if (line.charAt(col) == 'T') {
                    visited[row][col] = true;
                }
            }
        }

        visited[rowSize - 1][0] = true;
        count = 0;
    }

}
