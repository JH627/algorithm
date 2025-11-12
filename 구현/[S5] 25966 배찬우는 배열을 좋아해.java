import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25966_배찬우는배열을좋아해 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int rowSize, colSize, queryCount;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        useQuery();
        printMap();
    }

    static void useQuery() throws IOException {
        for (int query = 0; query < queryCount; query++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());

            int row, col;
            switch (operation) {
                case 0:
                    row = Integer.parseInt(st.nextToken());
                    col = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    map[row][col] = value;
                    break;
                case 1:
                    row = Integer.parseInt(st.nextToken());
                    int newRow = Integer.parseInt(st.nextToken());
                    for (int c = 0; c < colSize; c++) {
                        int temp = map[newRow][c];
                        map[newRow][c] = map[row][c];
                        map[row][c] = temp;
                    }
                    break;
            }
        }
    }

    static void printMap() {
        sb = new StringBuilder();
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                sb.append(map[row][col]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        queryCount = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
