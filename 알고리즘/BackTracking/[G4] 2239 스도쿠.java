import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] map;
    static boolean[][] row, col, block;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();

        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = s.charAt(j) - '0';
                if (map[i][j] != 0) {
                    fill(i, j, map[i][j]);
                }
            }
        }

        bt(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void bt(int x, int y) {
        if (x >= 9) {
            flag = true;
            return;
        }

        if (map[x][y] != 0) {
            bt(x + (y + 1) / 9, (y + 1) % 9);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (isValid(x, y, i)) {
                fill(x, y, i);
                bt(x + (y + 1) / 9, (y + 1) % 9);
                if (flag) {
                    return;
                }
                remove(x, y, i);
            }
        }
    }

    static void init() {
        map = new int[9][9];
        row = new boolean[9][10];
        col = new boolean[9][10];
        block = new boolean[9][10];
        flag = false;
    }

    static boolean isValid(int x, int y, int val) {
        return (row[x][val] || col[y][val] || block[(x / 3) * 3 + y / 3][val]) ? false : true;
    }

    static void fill(int x, int y, int val) {
        map[x][y] = val;
        row[x][val] = true;
        col[y][val] = true;
        block[(x / 3) * 3 + y / 3][val] = true;
    }

    static void remove(int x, int y, int val) {
        map[x][y] = 0;
        row[x][val] = false;
        col[y][val] = false;
        block[(x / 3) * 3 + y / 3][val] = false;
    }
}
