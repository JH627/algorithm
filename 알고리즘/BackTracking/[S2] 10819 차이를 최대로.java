import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, seq;
    static boolean[] visited;
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        seq = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bt(0);

        System.out.print(MAX);
    }

    static void bt(int cnt) {
        if (cnt == N) {
            int ret = 0;
            for (int i = 0; i < N - 1; i++) {
                ret += Math.abs(seq[i] - seq[i + 1]);
            }
            MAX = Math.max(MAX, ret);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                seq[cnt] = arr[i];
                bt(cnt + 1);
                visited[i] = false;
            }
        }
    }
}
