import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1043_거짓말 {

	static BufferedReader br;
	static StringTokenizer st;

	static int manCount, partyCount;
	static int[][] participant;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		init();
		unionRealMan();
		checkParticipants();
		System.out.println(getPartyCount());
	}

	static void unionRealMan() throws IOException {
		st = new StringTokenizer(br.readLine());
		int realManCount = Integer.parseInt(st.nextToken());
		for (int index = 0; index < realManCount; index++) {
			int number = Integer.parseInt(st.nextToken());
			union(0, number);
		}
	}

	static void checkParticipants() throws IOException {
		participant = new int[partyCount][];

		for (int party = 0; party < partyCount; party++) {
			st = new StringTokenizer(br.readLine());

			int participantCount = Integer.parseInt(st.nextToken());
			participant[party] = new int[participantCount];

			participant[party][0] = Integer.parseInt(st.nextToken());
			for (int man = 1; man < participantCount; man++) {
				participant[party][man] = Integer.parseInt(st.nextToken());
				union(participant[party][man], participant[party][man - 1]);
			}
		}
	}

	static int getPartyCount() {
		int count = 0;
		for (int party = 0; party < partyCount; party++) {
			int participantCount = participant[party].length;

			boolean possible = true;
			for (int man = 0; man < participantCount; man++) {
				if (find(participant[party][man]) == 0) {
					possible = false;
					break;
				}
			}
			if (possible) {
				count++;
			}
		}

		return count;
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

		if (px < py) {
			parent[py] = px;
		}
		else {
			parent[px] = py;
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		manCount = Integer.parseInt(st.nextToken()) + 1;
		partyCount = Integer.parseInt(st.nextToken());

		parent = new int[manCount];
		for (int index = 0; index < manCount; index++) {
			parent[index] = index;
		}
	}

}
