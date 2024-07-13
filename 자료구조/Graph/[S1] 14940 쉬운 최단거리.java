import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] distance, map;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        distance = new int[n][m];
        map = new int[n][m];

        Queue<Pos> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    q.add(new Pos(i, j));
                }
                if (map[i][j] == 1) {
                    distance[i][j] = -1;
                }
            }
        }

        int new_x, new_y;
        while(!q.isEmpty()) {
            Pos p = q.poll();
            for (int i = 0; i < 4; i++) {
                new_x = p.x + dx[i];
                new_y = p.y + dy[i];
                if (new_x < 0 || new_x >= n || new_y < 0 || new_y >= m) {
                    continue;
                }
                if (distance[new_x][new_y] == -1) {
                    distance[new_x][new_y] = distance[p.x][p.y] + 1;
                    q.add(new Pos(new_x, new_y));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(distance[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
