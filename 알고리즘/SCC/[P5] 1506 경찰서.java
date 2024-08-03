import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, clock;
    static int[] cost, visited;
    static boolean[] finished;
    static HashMap<Integer, Integer> minSccCost;
    static ArrayList<ArrayList<Integer>> graph;
    static Stack<Integer> s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            cost[n] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '1') {
                    graph.get(i).add(j);
                }
            }
        }

        s = new Stack<>();
        visited = new int[N];
        finished = new boolean[N];
        Arrays.fill(visited, -1);
        minSccCost = new HashMap<>();

        clock = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i] == -1) {
                dfs(i);
            }
        }

        int sum = 0;
        for (Integer value : minSccCost.values()) {
            sum += value;
        }
        System.out.println(sum);
    }

    static int dfs(int v) {
        visited[v] = clock++;
        s.push(v);

        int parent = visited[v];
        for (Integer next: graph.get(v)) {
            if (visited[next] == -1) {
                parent = Math.min(parent, dfs(next));
            }
            else if (!finished[next]) {
                parent = Math.min(parent, visited[next]);
            }
        }

        if (parent == visited[v]) {
            while (true) {
                int now = s.pop();
                finished[now] = true;

                minSccCost.put(v, Math.min(minSccCost.getOrDefault(v, Integer.MAX_VALUE), cost[now]));

                if (now == v) {
                    break;
                }
            }
        }

        return parent;
    }
}
