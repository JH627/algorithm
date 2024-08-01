import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int count;
    static int[] distance;
    static int MAX = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        distance = new int[MAX + 1];
        Arrays.fill(distance, 0);

        bfs(N);

        StringBuilder sb = new StringBuilder();
        sb.append(distance[K]).append("\n");
        sb.append(count);

        System.out.println(sb);

    }

    static void bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == K) {
                count++;
                continue;
            }

            Integer[] list = {now + 1, now - 1, now * 2};
            for (Integer next : list) {
                if (next < 0 || next >= MAX) {
                    continue;
                }
                if (distance[next] == 0 || distance[next] == distance[now] + 1) {
                    q.add(next);
                    distance[next] = distance[now] + 1;
                }

            }
        }
    }
}
