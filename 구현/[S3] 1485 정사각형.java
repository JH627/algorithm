import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1485_정사각형 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int[][] points;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(isSquare() ? 1 : 0).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		points = new int[4][2];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				points[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static boolean isSquare() {
		int[] dist = new int[6];
		int index = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 4; j++) {
				int dx = points[i][0] - points[j][0];
				int dy = points[i][1] - points[j][1];
				dist[index++] = dx * dx + dy * dy;
			}
		}

		Arrays.sort(dist);

		return dist[0] > 0 && dist[0] == dist[1] && dist[1] == dist[2] && dist[2] == dist[3] &&	dist[4] == dist[5];
	}
}
