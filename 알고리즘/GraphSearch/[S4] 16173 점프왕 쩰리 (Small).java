import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16173_점프왕쩰리(Small) {

    static BufferedReader br;
    static StringTokenizer st;

    static final int INF = 1000;
    static final int[] ADD_ROW = {1, 0};
    static final int[] ADD_COL = {0, 1};

    static int mapSize;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(checkPossible() ? "HaruHaru" : "Hing");
    }

    static boolean checkPossible() {
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{0, 0});
        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowRow = now[0];
            int nowCol = now[1];

            int power = map[nowRow][nowCol];
            for (int delta = 0; delta < 2; delta++) {
                int nextRow = nowRow + power * ADD_ROW[delta];
                int nextCol = nowCol + power * ADD_COL[delta];

                if (nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize) {
                    continue;
                }
                if (map[nextRow][nextCol] == INF) {
                    continue;
                }

                if (nextRow == mapSize - 1 && nextCol == mapSize - 1) {
                    return true;
                }

                toVisit.add(new int[]{nextRow, nextCol});
            }
            map[nowRow][nowCol] = INF;
        }

        return false;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        mapSize = Integer.parseInt(br.readLine());
        map = new int[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
