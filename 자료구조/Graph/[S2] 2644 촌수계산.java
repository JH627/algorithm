import java.io.*;
import java.util.*;

public class Main {
	
	static int n, s, e;
	static int[] dist;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		dist = new int[n];
		
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine());
		int x, y;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		
		dist = new int[n + 1];
		Arrays.fill(dist, -1);
		
		bfs(s);
		
		System.out.print(dist[e]);
	}
	
	static void bfs(int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		dist[s] = 0;
		q.add(s);
		
		while (!q.isEmpty()) {
			Integer now = q.poll();
			
			for (Integer next : graph.get(now)) {
				if (dist[next] == -1) {
					dist[next] = dist[now] + 1;
					if (next == e) {
						return;
					}
					q.add(next);
				}
			}
		}
	}
}
