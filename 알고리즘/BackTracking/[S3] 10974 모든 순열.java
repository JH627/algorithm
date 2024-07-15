import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        visited = new boolean[N + 1];
        sb = new StringBuilder();

        bt(0);

        System.out.println(sb);
    }

    static void bt(int cnt) {
        if (cnt == N) {
            for (int i = 0; i < cnt; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[cnt] = i;
                bt(cnt + 1);
                visited[i] = false;
            }
        }
    }
}
