import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_2210_숫자판점프 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int SIZE = 5;
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int[][] arr;
	static HashSet<String> set;

	public static void main(String[] args) throws Exception {
		init();

		getCount();

		System.out.print(set.size());
	}

	static void getCount(){
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				simulation(row, col, String.valueOf(arr[row][col]), 1);
			}
		}
	}

	static void simulation(int row, int col, String s, int depth) {
		if (depth == 6) {
			set.add(s);
			return;
		}

		int newRow, newCol;
		for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
			newRow = row + ADD_ROW[deltaIndex];
			newCol = col + ADD_COL[deltaIndex];

			if (newRow < 0 || newRow >= SIZE || newCol < 0 || newCol >= SIZE) {
				continue;
			}
			simulation(newRow, newCol, s + arr[newRow][newCol], depth + 1);
		}
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[SIZE][SIZE];
		set = new HashSet<>();

		for (int row = 0; row < SIZE; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < SIZE; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
