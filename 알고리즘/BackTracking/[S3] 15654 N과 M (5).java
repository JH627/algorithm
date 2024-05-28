import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static Integer[] arr;
    static Integer[] ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Set<Integer> s = new HashSet<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            s.add(Integer.parseInt(st.nextToken()));
        }
        arr = s.toArray(new Integer[0]);
        visited = new boolean[arr.length];
        ans = new Integer[m];
        Arrays.sort(arr);

        bt(0);

        System.out.println(sb);
    }

    static void bt(int clock) {
        if (clock == m) {
            for (int i = 0; i < m; i++) {
                sb.append(ans[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans[clock] = arr[i];
                bt(clock + 1);
                visited[i] = false;
            }
        }
    }
}