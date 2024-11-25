import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static int[] arr, ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        visited = new boolean[N];
        ans = new int[M];

        dfs(0,0);

        System.out.print(sb);
    }

    static void dfs(int depth, int start) {
        if (depth == M) {
            for (int n : ans) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans[depth] = arr[i];

                dfs(depth + 1,i);

                visited[i] = false;
            }
        }
    }
}
