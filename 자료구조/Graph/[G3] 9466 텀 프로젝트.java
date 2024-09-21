import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N;
    static int[] arr;
    static boolean[] visited, checked;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            checked = new boolean[N + 1];
            for (int n = 1; n < N + 1; n++) {
                arr[n] = Integer.parseInt(st.nextToken());
            }

            count = 0;
            for (int i = 1; i < N + 1; i++) {
                if (!checked[i]) {
                    dfs(i);
                }
            }

            sb.append(N - count).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int n) {
        if (visited[n]) {
            checked[n] = true;
            count++;
        }
        else {
            visited[n] = true;
        }

        if (!checked[arr[n]]) {
            dfs(arr[n]);
        }

        visited[n] = false;
        checked[n] = true;
    }
}
