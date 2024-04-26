import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int n, m, del;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> tree;

    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        visited = new boolean[n];
        tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        int s = 0;
        for (int i = 0; i < n; i++) {
            m = sc.nextInt();
            if (m != -1) {
                tree.get(m).add(i);
            }
            else {
                s = i;
            }
        }

        del = sc.nextInt();
        tree.get(del).clear();

        visited[s] = true;
        if (s != del) {
            dfs(s);
        }

        System.out.println(ans);
    }

    static void dfs(int v) {
        visited[v] = true;
        int valid = 0;
        for (int node : tree.get(v)) {
            if (!visited[node] && node != del) {
                valid++;
                dfs(node);
            }
        }
        if (valid == 0) {
            ans++;
        }
    }
}