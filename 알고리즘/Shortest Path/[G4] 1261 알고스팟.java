import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    static int n, m;
    static int[][] map;
    static int[][] cntMap;

    static final int[] add_x= {-1, 1, 0, 0};
    static final int[] add_y= {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        initMap();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(cntMap[n - 1][m - 1]);
    }

    private static void bfs() {
        Queue<Node> q = new PriorityQueue<>();
        cntMap[0][0] = 0;
        q.add(new Node(0, 0, 0));

        Node now;
        int new_x, new_y, nextCnt;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now.cnt > cntMap[now.x][now.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                new_x = now.x + add_x[i];
                new_y = now.y + add_y[i];

                if (new_x < 0 || new_y < 0 || new_x >= n || new_y >= m) {
                    continue;
                }

                nextCnt = (map[new_x][new_y] == 1) ? now.cnt + 1 : now.cnt;
                if (cntMap[new_x][new_y] > nextCnt) {
                    cntMap[new_x][new_y] = nextCnt;
                    q.add(new Node(new_x, new_y, nextCnt));
                }
            }
        }
    }

    private static void initMap() {
        cntMap = new int[n][m];
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cntMap[i], 300);
        }
    }
}