import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			graph.add(new ArrayList<>());
		}
		
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		int ans = 1;
		for (int i = 1; i <= N; i++) {
			if (find(i)) {
				ans = i;
			}
		}

		System.out.print(ans);
	}
	
	static boolean find(int start) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		q.add(new int[] {start, 0});
		
		int sum = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			int nowIdx = now[0];
			int nowDist = now[1];
			
			for (int next : graph.get(nowIdx)) {
				if (visited[next]) {
					continue;
				}
				
				sum += nowDist + 1;
				q.add(new int[] {next, nowDist + 1});
				visited[next] = true;

				if (sum >= min) {
					return false;
				}
			}
		}
		
		min = sum;
		return true;
	}

}
