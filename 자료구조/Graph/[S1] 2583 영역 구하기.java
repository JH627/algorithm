import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int M, N, K;
    static int[][] map;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            fillMap(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
                    ,Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 1) {
                    arr.add(bfs(i, j));
                }
            }
        }

        Collections.sort(arr);

        sb.append(arr.size()).append('\n');
        for (Integer i : arr) {
            sb.append(i).append(" ");
        }
        System.out.print(sb);
    }

    static void fillMap(int x1, int y1, int x2, int y2) {
        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) {
                map[i][j] = 1;
            }
        }
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        int cnt = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];

            if (map[nowX][nowY] > 0) {
                continue;
            }
            map[nowX][nowY] = 1;
            cnt++;

            for (int i = 0; i < 4; i++) {
                int newX = nowX + dx[i];
                int newY = nowY + dy[i];

                if (newX >= M || newX < 0 || newY >= N || newY < 0) {
                    continue;
                }
                q.add(new int[]{newX, newY});
            }
        }

        return cnt;
    }
}
