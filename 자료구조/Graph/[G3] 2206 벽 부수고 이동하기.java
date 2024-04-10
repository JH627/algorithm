import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int map[][];
    static int dist[][];
    static boolean visited[][][];

    static final int add_x[] = {-1, 1, 0, 0};
    static final int add_y[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dist = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println((dist[n-1][m-1] == 0) ? -1 : dist[n - 1][m - 1]);
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0, 0});
        dist[0][0] = 1;
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int removed = now[2];
            for (int i = 0; i < 4; i++) {
                int new_x = x + add_x[i];
                int new_y = y + add_y[i];

                if (new_x < 0 || new_y < 0 || new_x >= n || new_y >= m) {
                    continue;
                }
                if (map[new_x][new_y] == 1 && removed == 0) {
                    q.add(new int[]{new_x, new_y, 1});
                    dist[new_x][new_y] = dist[x][y] + 1;
                    visited[new_x][new_y][1] = true;
                } else {
                    if (map[new_x][new_y] == 0) {
                        if (!visited[new_x][new_y][removed]) {
                            q.add(new int[]{new_x, new_y, removed});
                            if (new_x == n - 1 && new_y == m - 1 && dist[new_x][new_y] != 0) {
                                dist[new_x][new_y] = Math.min(dist[new_x][new_y], dist[x][y] + 1);
                            }
                            else {
                                dist[new_x][new_y] = dist[x][y] + 1;
                            }
                            visited[new_x][new_y][removed] = true;
                        }
                    }
                }
            }
        }
    }
}