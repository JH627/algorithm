import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2240_자두나무 {

	static BufferedReader br;
	static StringTokenizer st;

	static int timeLimit, moveLimit;
	static boolean[] tree;
	static int[][][] maxScore;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxScore());
	}

	static int findMaxScore() {
		for (int time = 1; time < timeLimit; time++) {
			if (!tree[time]) {
				maxScore[time][0][0] = maxScore[time - 1][0][0] + 1;
				maxScore[time][0][1] = maxScore[time - 1][0][1];
			}
			else {
				maxScore[time][0][0] = maxScore[time - 1][0][0];
				maxScore[time][0][1] = maxScore[time - 1][0][1] + 1;
			}
			
			for (int move = 1; move <= moveLimit; move++) {
				if (!tree[time]) {
					maxScore[time][move][0] = Math.max(maxScore[time - 1][move][0], maxScore[time - 1][move - 1][1]) + 1;
					maxScore[time][move][1] = Math.max(maxScore[time - 1][move - 1][0], maxScore[time - 1][move][1]);
				}
				else {
					maxScore[time][move][0] = Math.max(maxScore[time - 1][move][0], maxScore[time - 1][move - 1][1]);
					maxScore[time][move][1] = Math.max(maxScore[time - 1][move - 1][0], maxScore[time - 1][move][1]) + 1;
				}
			}
		}

		int ans = 0;
		for (int w = 0; w <= moveLimit; w++) {
			ans = Math.max(ans, Math.max(maxScore[timeLimit - 1][w][0], maxScore[timeLimit - 1][w][1]));
		}
		return ans;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		timeLimit = Integer.parseInt(st.nextToken());
		moveLimit = Integer.parseInt(st.nextToken());
		maxScore = new int[timeLimit][moveLimit + 1][2];

		tree = new boolean[timeLimit];
		for (int time = 0; time < timeLimit; time++) {
			int treePosition = Integer.parseInt(br.readLine());
			tree[time] = (treePosition == 2);
		}

		if (tree[0]) {
			maxScore[0][0][0] = 0;
			maxScore[0][1][1] = 1;
		}
		else {
			maxScore[0][0][0] = 1;
			maxScore[0][1][1] = 0;
		}
	}
}
