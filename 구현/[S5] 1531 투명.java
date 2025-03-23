import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1531_투명 {

	static int queryCount, limit;
	static int[][] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		queryCount = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());

		count = new int[100][100];

		int ans = 0;
		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());
			int startRow = Integer.parseInt(st.nextToken()) - 1;
			int startCol = Integer.parseInt(st.nextToken()) - 1;
			int endRow = Integer.parseInt(st.nextToken()) - 1;
			int endCol = Integer.parseInt(st.nextToken()) - 1;

			for (int row = startRow; row <= endRow; row++) {
				for (int col = startCol; col <= endCol; col++) {
					if (++count[row][col] == limit + 1) {
						ans++;
					}
				}
			}
		}

		System.out.print(ans);
	}
}
