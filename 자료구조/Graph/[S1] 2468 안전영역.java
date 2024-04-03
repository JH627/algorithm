import java.util.Scanner;

public class Main {

    static int n;

    static boolean[][] visited;
    static int[][] map;
    static int[] add_x = {1, -1, 0, 0};
    static int[] add_y = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                max = Math.max(max, map[i][j]);
            }
        }

        int cnt;
        int maxCnt = 0;
        for (int k = 0; k <= max; k++) {
            cnt = 0;
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && map[i][j] > k) {
                        cnt++;
                        dfs(i, j, k);
                    }
                }
            }
            maxCnt = Math.max(cnt, maxCnt);
        }
        System.out.println(maxCnt);
    }

    static void dfs(int x, int y, int k) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int new_x = x + add_x[i];
            int new_y = y + add_y[i];

            if (new_x < 0 || new_y < 0 || new_x >= n || new_y >= n || visited[new_x][new_y] || map[new_x][new_y] <= k) {
                continue;
            }

            dfs(new_x, new_y, k);
        }
    }
}