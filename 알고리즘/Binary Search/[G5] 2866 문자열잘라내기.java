import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_2866_문자열잘라내기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int rowSize, colSize;
	static char[][] words;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		words = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			char[] line = br.readLine().toCharArray();
			for (int col = 0; col < colSize; col++) {
				words[row][col] = line[col];
			}
		}
	}

	static int getCount() {
		int left = 0;
		int right = rowSize - 1;
		int answer = 0;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (checkPossible(mid)) {
				answer = mid;
				left = mid + 1;
			} 
			else {
				right = mid - 1;
			}
		}

		return answer;
	}

	static boolean checkPossible(int row) {
		HashSet<String> set = new HashSet<>();

		for (int col = 0; col < colSize; col++) {
			StringBuilder sb = new StringBuilder();

			for (int r = row; r < rowSize; r++) {
				sb.append(words[r][col]);
			}

			String str = sb.toString();
			if (set.contains(str)) {
				return false;
			}
			set.add(str);
		}

		return true;
	}
}
