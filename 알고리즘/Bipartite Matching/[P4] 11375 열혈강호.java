import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Integer>> jobs;
    static int[] manager;
    static boolean[] matched;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        jobs = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            jobs.add(new ArrayList<>());
        }

        manager = new int[M + 1];
        matched = new boolean[M + 1];

        for (int n = 1; n < N + 1; n++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int c = 0; c < cnt; c++) {
                jobs.get(n).add(Integer.parseInt(st.nextToken()));
            }
        }

        int min = Math.min(N, M);
        int ans = 0;
        for (int i = 1; i < N + 1 && ans != min; i++) {
            Arrays.fill(matched, false);
            if (dfs(i)) {
                ans++;
            }
        }

        System.out.print(ans);
    }


    static boolean dfs(int n) {
        for (Integer next : jobs.get(n)) {
            if (matched[next]) {
                continue;
            }
            matched[next] = true;

            if (manager[next] == 0 || dfs(manager[next])) {
                manager[next] = n;
                return true;
            }
        }
        return false;
    }
}
