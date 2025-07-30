import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_25418_정수a를k로만들기 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int INF = 1000001;

	static int start, end;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinCount());
	}

	static int getMinCount() {
		Queue<Integer> toVisit = new LinkedList<>();
		toVisit.add(start);
		dist[start] = 0;
		while (!toVisit.isEmpty()) {
			int now = toVisit.poll();

			int next = now + 1;
			if (next <= end && dist[next] == INF) {
				dist[next] = dist[now] + 1;
				if (next == end) {
					return dist[next];
				}
				toVisit.add(next);
			}
			next = now * 2;
			if (next <= end && dist[next] == INF) {
				dist[next] = dist[now] + 1;
				if (next == end) {
					return dist[next];
				}
				toVisit.add(next);
			}
		}
		return 0;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dist = new int[end + 1];
		Arrays.fill(dist, INF);
	}
}
