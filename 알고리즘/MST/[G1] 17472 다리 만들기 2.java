import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, clock;
    static int[][] map;
    static int[] parent;
    static PriorityQueue<Bridge> q = new PriorityQueue<>();
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    static class Bridge implements Comparable<Bridge> {
        int start, end;
        int length;

        public Bridge(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        @Override
        public int compareTo(Bridge o) {
            return this.length - o.length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clock = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    makeIsland(i, j);
                    clock++;
                }
            }
        }

        makeBridge();

        parent = new int[100];
        for (int i = 0; i < 100; i++) {
            parent[i] = i;
        }

        System.out.print(mst());
    }

    static void makeIsland(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (map[cur[0]][cur[1]] > 1) {
                continue;
            }
            map[cur[0]][cur[1]] = clock;

            for (int i = 0; i < 4; i++) {
                int newX = cur[0] + dx[i];
                int newY = cur[1] + dy[i];
                if (newX < 0 || newX >= N || newY < 0 || newY >= M) {
                    continue;
                }
                if (map[newX][newY] == 1) {
                    q.add(new int[]{newX, newY});
                }
            }
        }
    }

    static void makeBridge() {
        for (int i = 0; i < N; i++) {
            int len = 0;
            for (int j = 1; j < M; j++) {
                if (map[i][j] == 0) {
                    len++;
                }
                else {
                    if (len >= 2) {
                        if (map[i][j - len - 1] != 0) {
                            q.add(new Bridge(map[i][j - len - 1], map[i][j], len));
                            q.add(new Bridge(map[i][j], map[i][j - len - 1], len));
                        }
                    }
                    len = 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int len = 0;
            for (int j = 1; j < N; j++) {
                if (map[j][i] == 0) {
                    len++;
                }
                else {
                    if (len >= 2) {
                        if (map[j - len - 1][i] != 0) {
                            q.add(new Bridge(map[j - len - 1][i], map[j][i], len));
                            q.add(new Bridge(map[j][i], map[j - len - 1][i], len));
                        }
                    }
                    len = 0;
                }
            }
        }
    }

    static int mst() {
        int ret = 0;
        int reached = 0;
        while (!q.isEmpty()) {
            Bridge now = q.poll();
            if (find(now.start) == find(now.end)) {
                continue;
            }
            union(now.start, now.end);
            ret += now.length;
            reached++;
        }
        return (ret == 0 || reached < clock - 3) ? -1 : ret;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        parent[py] = px;
    }
}
