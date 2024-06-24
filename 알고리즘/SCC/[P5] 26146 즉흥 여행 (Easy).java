import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static Stack<Integer> s;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        s = new Stack<>();
        visited = new boolean[N + 1];
        ArrayList<ArrayList<Integer>> arr, arr_r;
        arr = new ArrayList<>();
        arr_r = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
            arr_r.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr_r.get(b).add(a);
        }

        for (int v = 1; v <= N; v++) {
            if (!visited[v]) {
                dfs(v, arr, false);
            }
        }

        Arrays.fill(visited, false);

        dfs(s.pop(), arr_r, true);

        boolean possible = true;
        while (!s.empty()) {
            if (!visited[s.pop()]) {
                possible = false;
                break;
            }
        }

        System.out.println(possible ? "Yes" : "No");
    }

    static void dfs(int v, ArrayList<ArrayList<Integer>> g, boolean reverse) {
        visited[v] = true;
        int size = g.get(v).size();
        int next;
        for (int j = 0; j < size; j++) {
            next = g.get(v).get(j);
            if (!visited[next]) {
                dfs(next, g, reverse);
            }
        }

        if (!reverse) {
            s.add(v);
        }
    }
}