import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[] arr;
    static final int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[MAX];
        Arrays.fill(arr, -1);

        bfs(N);

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(K);
        int now = K;
        while (arr[now] != -1) {
            now = arr[now];
            list.add(now);
        }
        sb.append(list.size() - 1).append("\n");
        Collections.reverse(list);
        for (Integer i : list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static void bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == K) {
                break;
            }

            Integer[] list = {now + 1, now - 1, now * 2};
            for (Integer next : list) {
                if (next < 0 || next >= MAX) {
                    continue;
                }
                if (arr[next] == -1 && next != N) {
                    q.add(next);
                    arr[next] = now;
                }
            }
        }
    }
}
