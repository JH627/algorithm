import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int[][] alpha;
    static int r, c;
    static int max = Integer.MIN_VALUE;

    static final int[] add_x = {1, -1, 0, 0};
    static final int[] add_y = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        alpha = new int[r][c];
        visited = new boolean[26];

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                alpha[i][j] = s.charAt(j) - 'A';
            }
        }

        visited[alpha[0][0]] = true;
        dfs(0, 0, 1);

        System.out.println(max);
    }

    static void dfs(int x, int y, int cnt) {
        max = Math.max(cnt, max);

        for (int k = 0; k < 4; k++) {
            int new_x = x + add_x[k];
            int new_y = y + add_y[k];

            if (new_x < 0 || new_y < 0 || new_x >= r || new_y >= c) {
                continue;
            }

            int next_alpha = alpha[new_x][new_y];
            if (!visited[next_alpha]) {
                visited[next_alpha] = true;
                dfs(new_x, new_y, cnt + 1);
                visited[next_alpha] = false;
            }
        }
    }
}