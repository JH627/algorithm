import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7511_소셜네트워킹어플리케이션 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int peopleCount, friendsCount, queryCount;
	static int[] parent, rank;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			sb.append("Scenario ").append(testCase + 1).append(":\n");
			init();
			useQuery();
			sb.append("\n");
		}
		System.out.print(sb);
	}

	static void useQuery() throws IOException {
		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int px = find(x);
			int py = find(y);
			sb.append(px == py ? "1" : "0").append("\n");
		}
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);

		if (px == py) {
			return;
		}
		if (rank[px] < rank[py]) {
			parent[py] = px;
		}
		else if (rank[px] > rank[py]) {
			parent[px] = py;
		}
		else {
			parent[px] = py;
			rank[px]++;
		}
	}

	static void init() throws IOException {
		peopleCount = Integer.parseInt(br.readLine());
		friendsCount = Integer.parseInt(br.readLine());

		parent = new int[peopleCount];
		rank = new int[peopleCount];
		for (int person = 0; person < peopleCount; person++) {
			parent[person] = person;
		}


		for (int friend = 0; friend < friendsCount; friend++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			union(x, y);
		}

		queryCount = Integer.parseInt(br.readLine());
	}
}
