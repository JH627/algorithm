import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593_상범빌딩 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int[] ADD_ROW = {-1, 0, 1, 0};
    static final int[] ADD_COL = {0, 1, 0, -1};
    static final int[] ADD_H = {-1, 1};

    static int height, rowSize, colSize;
    static char[][][] map;

    static class Node {
        int h, row, col;

        public Node(int h, int row, int col) {
            this.h = h;
            this.row = row;
            this.col = col;
        }
    }

    static Node start, end;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (init()) {
            int time = findEscapeTime();
            if (time == -1) {
                sb.append("Trapped!\n");
            }
            else {
                sb.append(String.format("Escaped in %d minute(s).\n", time));
            }
        }

        System.out.print(sb);
    }

    static int findEscapeTime() {
        int[][][] distance = new int[height][rowSize][colSize];
        Queue<Node> toVisit = new LinkedList<>();
        toVisit.add(start);
        distance[start.h][start.row][start.col] = 1;
        while (!toVisit.isEmpty()) {
            Node now = toVisit.poll();

            for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
                int newRow = now.row + ADD_ROW[deltaIndex];
                int newCol = now.col + ADD_COL[deltaIndex];

                if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                    continue;
                }
                if (distance[now.h][newRow][newCol] != 0) {
                    continue;
                }

                if (map[now.h][newRow][newCol] == '#') {
                    continue;
                }

                distance[now.h][newRow][newCol] = distance[now.h][now.row][now.col] + 1;

                if (now.h == end.h && newRow == end.row && newCol == end.col) {
                    return distance[now.h][newRow][newCol] - 1;
                }

                toVisit.add(new Node(now.h, newRow, newCol));
            }

            for (int index = 0; index < 2; index++) {
                int newH = now.h + ADD_H[index];
                if (newH < 0 || newH >= height) {
                    continue;
                }

                if (distance[newH][now.row][now.col] != 0) {
                    continue;
                }

                if (map[newH][now.row][now.col] == '#') {
                    continue;
                }

                distance[newH][now.row][now.col] = distance[now.h][now.row][now.col] + 1;

                if (newH == end.h && now.row == end.row && now.col == end.col) {
                    return distance[newH][now.row][now.col] - 1;
                }

                toVisit.add(new Node(newH, now.row, now.col));
            }
        }

        return -1;
    }

    static boolean init() throws IOException {
        st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        if (height == 0 && rowSize == 0 && colSize == 0) {
            return false;
        }

        map = new char[height][rowSize][colSize];
        for (int h = 0; h < height; h++) {
            for (int row = 0; row < rowSize; row++) {
                String line = br.readLine();
                for (int col = 0; col < colSize; col++) {
                    char type = line.charAt(col);
                    map[h][row][col] = type;
                    if (type == 'S') {
                        start = new Node(h, row, col);
                    }
                    if (type == 'E') {
                        end = new Node(h, row, col);
                    }
                }
            }
            br.readLine();
        }

        return true;
    }
}
