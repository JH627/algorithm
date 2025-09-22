import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14732_행사장대여(Small) {

	static BufferedReader br;
	static StringTokenizer st;

	static int squareCount;
	static int size;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(size);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[501][501];
		size = 0;

		squareCount = Integer.parseInt(br.readLine());
		for (int square = 0; square < squareCount; square++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int x = x1; x < x2; x++) {
				for (int y = y1; y < y2; y++) {
					if (!map[x][y]) {
						size++;
						map[x][y] = true;
					}
				}
			}
		}
	}

}
