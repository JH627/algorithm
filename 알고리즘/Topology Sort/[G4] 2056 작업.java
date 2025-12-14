import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2056_작업 {

	static BufferedReader br;
	static StringTokenizer st;

	static int jobCount;
	static int[] degree, time;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxTime());
	}

	static int findMaxTime() {
		Queue<Integer> jobQueue = new LinkedList<>();

		int[] resultTime = new int[jobCount + 1];
		for (int job = 1; job < jobCount + 1; job++) {
			resultTime[job] = time[job];

			if (degree[job] == 0) {
				jobQueue.add(job);
			}
		}

		int maxTime = 0;
		while (!jobQueue.isEmpty()) {
			int now = jobQueue.poll();

			maxTime = Math.max(maxTime, resultTime[now]);
			
			for (int next : graph.get(now)) {
				degree[next]--;

				resultTime[next] = Math.max(resultTime[next], resultTime[now] + time[next]);

				if (degree[next] == 0) {
					jobQueue.add(next);
				}
			}

		}

		return maxTime;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		jobCount = Integer.parseInt(br.readLine());

		degree = new int[jobCount + 1];
		time = new int[jobCount + 1];
		graph = new ArrayList<>();
		for (int node = 0; node < jobCount + 1; node++) {
			graph.add(new ArrayList<>());
		}

		for (int now = 1; now < jobCount + 1; now++) {
			st = new StringTokenizer(br.readLine());

			time[now] = Integer.parseInt(st.nextToken());
			int prevNodeCount = Integer.parseInt(st.nextToken());
			for (int prevIndex = 0; prevIndex < prevNodeCount; prevIndex++) {
				int prev = Integer.parseInt(st.nextToken());
				graph.get(prev).add(now);

				degree[now]++;
			}
		}

	}
}
