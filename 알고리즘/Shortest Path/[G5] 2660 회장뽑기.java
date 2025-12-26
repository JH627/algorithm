import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2660_회장뽑기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int INF = 100000000;

	static int nodeCount;
	static int[][] dist;

	static int readerScore;
	static int[] scores;

	public static void main(String[] args) throws IOException {
		init();
		findDist();
		findReader();
		printAnswer();
	}

	static void findDist() {
		for (int k = 1; k < nodeCount + 1; k++) {
			for (int i = 1; i < nodeCount + 1; i++) {
				for (int j = 1; j < nodeCount + 1; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
	}

	static void findReader() {
		readerScore = INF;
		scores = new int[nodeCount + 1];

		for (int me = 1; me < nodeCount + 1; me++) {
			int score = 0;
			for (int other = 1; other < nodeCount + 1; other++) {
				if (dist[me][other] != INF) {
					score = Math.max(score, dist[me][other]);
				}
			}

			scores[me] = score;
			readerScore = Math.min(readerScore, scores[me]);
		}
	}

	static void printAnswer() {
		sb = new StringBuilder();
		sb.append(readerScore).append(" ");

		ArrayList<Integer> list = new ArrayList<>();
		for (int user = 1; user < nodeCount + 1; user++) {
			if (readerScore == scores[user]) {
				list.add(user);
			}
		}

		sb.append(list.size()).append("\n");
		for (int num : list) {
			sb.append(num).append(" ");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		nodeCount = Integer.parseInt(br.readLine());

		dist = new int[nodeCount + 1][nodeCount + 1];

		for (int i = 1; i < nodeCount + 1; i++) {
			for (int j = 1; j < nodeCount + 1; j++) {
				if (i == j) {
					continue;
				}
				dist[i][j] = INF;
			}
		}

		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1) {
				break;
			}

			dist[a][b] = 1;
			dist[b][a] = 1;
		}
	}
}
