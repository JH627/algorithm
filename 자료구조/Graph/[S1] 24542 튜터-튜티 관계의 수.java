import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static final long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		visited = new boolean[N];
		for (int n = 0; n < N; n++) {
			graph.add(new ArrayList<>());
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		long ans = 1;
		for (int n = 0; n < N; n++) {
			if (!visited[n]) {
				visited[n] = true;
				ans = (ans * dfs(n)) % MOD;
			}

		}

		System.out.print(ans);
	}

	static long dfs(int s) {
		long ans = 1;

		for (Integer next : graph.get(s)) {
			if (!visited[next]) {
				visited[next] = true;
				ans += dfs(next);
			}
		}

		return ans;
	}
}
