import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    static int N, K;
    static HashSet<String> set = new HashSet<>();
    static boolean[] visited;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        arr = new String[N];
        visited = new boolean[N];
        for (int n = 0; n < N; n++) {
            arr[n] = br.readLine();
        }

        dfs(0, "");

        System.out.print(set.size());
    }

    static void dfs(int depth, String s) {
        if (depth == K) {
            set.add(s);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, s + arr[i]);
                visited[i] = false;
            }
        }
    }
}
