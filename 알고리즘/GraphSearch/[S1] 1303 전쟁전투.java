import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1303_전쟁전투 {

    static BufferedReader br;
    static StringTokenizer st;

    static final int[] ADD_ROW = {-1, 0, 1, 0};
    static final int[] ADD_COL = {0, 1, 0, -1};

    static int rowSize, colSize;
    static int whiteScore, blueScore;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        checkScore();
        System.out.printf("%d %d", whiteScore, blueScore);
    }

    static void checkScore() {
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col] != -1) {
                    boolean isWhite = map[row][col] == 1;
                    int power = getPower(row, col);
                    if (isWhite) {
                        whiteScore += power;
                    } else {
                        blueScore += power;
                    }
                }
            }
        }
    }

    static int getPower(int row, int col) {
        int power = 0;

        Queue<int[]> toVisit = new LinkedList<>();
        int startColor = map[row][col];
        map[row][col] = -1;

        toVisit.add(new int[]{row, col});
        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            power++;

            for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
                int newRow = now[0] + ADD_ROW[deltaIndex];
                int newCol = now[1] + ADD_COL[deltaIndex];

                if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                    continue;
                }

                if (map[newRow][newCol] != startColor) {
                    continue;
                }

                map[newRow][newCol] = -1;
                toVisit.add(new int[]{newRow, newCol});
            }
        }

        return power * power;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            String line = br.readLine();
            for (int col = 0; col < colSize; col++) {
                if (line.charAt(col) == 'W') {
                    map[row][col] = 1;
                }
                else {
                    map[row][col] = 2;
                }
            }
        }

        whiteScore = 0;
        blueScore = 0;
    }
}
