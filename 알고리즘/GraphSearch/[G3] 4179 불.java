import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179_불 {

    static BufferedReader br;
    static StringTokenizer st;

    static final int[] ADD_ROW = {-1, 0, 1, 0};
    static final int[] ADD_COL = {0, 1, 0, -1};
    static final int USER = 0;
    static final int FIRE = 1;

    static class Move {
        int row, col;
        int type;
        int distance;

        public Move(int row, int col, int type, int distance) {
            this.row = row;
            this.col = col;
            this.type = type;
            this.distance = distance;
        }
    }

    static int rowSize, colSize;
    static boolean[][] visited;
    static Queue<Move> toVisit;

    public static void main(String[] args) throws IOException {
        init();
        int count = findCount();
        System.out.print(count == -1 ? "IMPOSSIBLE" : count);
    }

    static int findCount() {
        int remainUserCount = 1;
        while (!toVisit.isEmpty()) {
            Move now = toVisit.poll();

            if (remainUserCount == 0) {
                break;
            }
            if (now.type == USER) {
                remainUserCount--;
            }

            for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
                int newRow = now.row + ADD_ROW[deltaIndex];
                int newCol = now.col + ADD_COL[deltaIndex];

                if (isOutOfBounds(newRow, newCol)) {
                    if (now.type == USER) {
                        return now.distance + 1;
                    }
                    continue;
                }

                if (visited[newRow][newCol]) {
                    continue;
                }

                toVisit.add(new Move(newRow, newCol, now.type, now.distance + 1));
                visited[newRow][newCol] = true;
                if (now.type == USER) {
                    remainUserCount++;
                }
            }

        }

        return -1;
    }

    static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= rowSize || col < 0 || col >= colSize;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        visited = new boolean[rowSize][colSize];
        toVisit = new LinkedList<>();

        Move user = null;
        for (int row = 0; row < rowSize; row++) {
            String line = br.readLine();
            for (int col = 0; col < colSize; col++) {
                char mark = line.charAt(col);
                switch (mark) {
                    case '#':
                        visited[row][col] = true;
                        break;
                    case 'F':
                        visited[row][col] = true;
                        toVisit.add(new Move(row, col, FIRE, 0));
                        break;
                    case 'J':
                        user = new Move(row, col, USER, 0);
                        visited[row][col] = true;
                        break;
                }
            }
        }

        toVisit.add(user);
    }
}
