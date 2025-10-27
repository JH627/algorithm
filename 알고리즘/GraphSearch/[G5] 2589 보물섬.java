import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2589_보물섬 {

    static BufferedReader br;
    static StringTokenizer st;

    static final int[] ADD_ROW = {-1, 0, 1, 0};
    static final int[] ADD_COL = {0, -1, 0, 1};

    static class Node {
        int row, col;
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int rowSize, colSize;
    static char[][] map;
    static ArrayList<Node> lNodes;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findMaxLength());
    }

    static int findMaxLength() {
        int max = 0;
        for (Node node : lNodes) {
            max = Math.max(max, getMaxDistance(node));
        }
        return max;
    }

    static int getMaxDistance(Node node) {
        Queue<Node> toVisit = new LinkedList<>();
        int[][] dist = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            Arrays.fill(dist[row], Integer.MAX_VALUE);
        }

        toVisit.add(node);
        dist[node.row][node.col] = 0;
        int max = 0;
        while (!toVisit.isEmpty()) {
            Node now = toVisit.poll();

            int newRow, newCol;
            for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
                newRow = now.row + ADD_ROW[deltaIndex];
                newCol = now.col + ADD_COL[deltaIndex];

                if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                    continue;
                }
                if (map[newRow][newCol] == 'W') {
                    continue;
                }
                if (dist[newRow][newCol] != Integer.MAX_VALUE) {
                    continue;
                }

                dist[newRow][newCol] = dist[now.row][now.col] + 1;
                if (dist[newRow][newCol] > max) {
                    max = dist[newRow][newCol];
                }
                toVisit.add(new Node(newRow, newCol));
            }

        }

        return max;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new char[rowSize][colSize];
        lNodes = new ArrayList<>();
        for (int row = 0; row < rowSize; row++) {
            String line = br.readLine();
            for (int col = 0; col < colSize; col++) {
                map[row][col] = line.charAt(col);
                if (map[row][col] == 'L') {
                    lNodes.add(new Node(row, col));
                }
            }
        }
    }
}
