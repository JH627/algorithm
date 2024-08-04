import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, S, P;
    static int clock, startGroup, nowGroup;
    static int[] visited, cost, groupCost, sccGroup, money;
    static boolean[] finished;
    static HashSet<Integer> restaurant, restaurantGroup;
    static ArrayList<ArrayList<Integer>> graph, scc, dag;
    static Stack<Integer> s;

    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        nowGroup = 0;
        clock = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i] == -1) {
                dfs(i);
            }
        }

        createDAG();
        bfs_cost();

        System.out.println(MAX);
    }

    static int dfs(int v) {
        visited[v] = clock++;
        s.push(v);

        int parent = visited[v];
        for (Integer next : graph.get(v)) {
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
                scc.get(nowGroup).add(now);
                groupCost[nowGroup] += cost[now];
                sccGroup[now] = nowGroup;

                if (restaurant.contains(now)) {
                    restaurantGroup.add(nowGroup);
                }
                if (now == S) { startGroup = nowGroup; }
                if (now == v) { break; }
            }
            nowGroup++;
        }

        return parent;
    }

    static void createDAG() {
        dag = new ArrayList<>();
        for (int i = 0; i < nowGroup; i++) {
            dag.add(new ArrayList<>());
        }
        
        for (int v = 1; v <= N; v++) {
            for (int next : graph.get(v)) {
                if (sccGroup[v] != sccGroup[next]) {
                    dag.get(sccGroup[v]).add(sccGroup[next]);
                }
            }
        }
    }

    static void bfs_cost() {
        money = new int[nowGroup];
        Queue<Integer> q = new LinkedList<>();
        money[startGroup] = groupCost[startGroup];
        q.add(startGroup);

        while (!q.isEmpty()) {
            int now = q.poll();
            if (restaurantGroup.contains(now)) {
                MAX = Math.max(MAX, money[now]);
            }
            for (int next : dag.get(now)) {
                if (money[next] < money[now] + groupCost[next]) {
                    money[next] = money[now] + groupCost[next];
                    q.add(next);
                }
            }
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }

        cost = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            cost[i] = Integer.parseInt(br.readLine());
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        restaurant = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; i++) {
            restaurant.add(Integer.parseInt(st.nextToken()));
        }

        groupCost = new int[N + 1];
        scc = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            scc.add(new ArrayList<>());
        }

        sccGroup = new int[N + 1];
        s = new Stack<>();
        visited = new int[N + 1];
        Arrays.fill(visited, -1);
        finished = new boolean[N + 1];
        restaurantGroup = new HashSet<>();
    }
}
