import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E;
    static ArrayList<ArrayList<Integer>> arr;
    static ArrayList<ArrayList<Integer>> arr_r;
    static ArrayList<Integer> visited;
    static ArrayList<Integer> scc;
    static ArrayList<ArrayList<Integer>> answer;
    static int[] pre, post;
    static int clock;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        arr = new ArrayList<>();
        arr_r = new ArrayList<>();
        visited = new ArrayList<>();
        scc = new ArrayList<>();
        answer = new ArrayList<>();
        sb = new StringBuilder();

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= V; i++) {
            arr.add(new ArrayList<>());
            arr_r.add(new ArrayList<>());
        }

        int a, b;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            arr.get(a).add(b);
            arr_r.get(b).add(a);
        }

        for (int i = 0; i <= V; i++) {
            Collections.sort(arr.get(i));
            Collections.sort(arr_r.get(i));
        }

        pre = new int[V + 1];
        post = new int[V + 1];
        clock = 1;

        for (int i = 1; i <= V; i++) {
            if (pre[i] == 0) {
                dfs_r(i);
            }
        }

        Collections.reverse(visited);

        pre = new int[V + 1];
        post = new int[V + 1];
        clock = 1;

        for (int i = 0; i < V; i++) {
            if (pre[visited.get(i)] == 0) {
                scc = new ArrayList<>();
                dfs(visited.get(i));

                Collections.sort(scc);
                scc.add(-1);
                answer.add(scc);
            }
        }

        Collections.sort(answer, Comparator.comparingInt((ArrayList<Integer> o) -> o.get(0)));

        sb.append(answer.size()).append("\n");
        for (ArrayList<Integer> integers : answer) {
            for (Integer integer : integers) {
                sb.append(integer).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static private void dfs_r(int v) {
        pre[v] = clock++;
        for (int i = 0; i < arr_r.get(v).size(); i++) {
            if (pre[arr_r.get(v).get(i)] == 0) {
                dfs_r(arr_r.get(v).get(i));
            }
        }
        post[v] = clock++;
        visited.add(v);
    }

    static private void dfs(int v) {
        pre[v] = clock++;
        scc.add(v);
        for (int i = 0; i < arr.get(v).size(); i++) {
            if (pre[arr.get(v).get(i)] == 0) {
                dfs(arr.get(v).get(i));
            }
        }
    }
}