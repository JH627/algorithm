import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int F, S, G, U, D;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        distance = new int[F + 1];
        Arrays.fill(distance, -1);
        bfs();
        System.out.print(distance[G] == -1 ? "use the stairs" : distance[G]);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        distance[S] = 0;
        q.add(S);
        while (!q.isEmpty()) {
            int now = q.poll();
            int[] nextN = { now + U, now - D};
            for (int next : nextN) {
                if (next > 0 && next <= F) {
                    if (distance[next] == -1) {
                        q.add(next);
                        distance[next] = distance[now] + 1;
                        if (next == G) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
