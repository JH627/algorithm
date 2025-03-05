import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1, 2, 3, 4분면 중 어느 곳인지 확인해서 값을 구해보자
 * 
 * (기저조건)
 * 만약 공간의 길이가 2라면
 * (현재까지 가지고 내려온 값) + row * 2 + col 를 출력
 * 
 * 1. 만약 값이 2사분면에 있는 경우 (size / 2 <= col && size / 2 > row)
 *    => 현재 공간의 사이즈 / 4 의 값을 추가로 가지고 분할함
 * 2. 만약 값이 1사분면에 있는 경우 (size / 2 > col && size / 2 > row)
 *    => 추가로 값을 가지지 않고 분할함
 * 3. 만약 값이 3사분면에 있는 경우 (size / 2 > col && size / 2 <= row)
 *    => (현재 공간의 사이즈 / 4) * 2의 값을 추가로 가지고 분할함
 * 4. 만약 값이 4사분면에 있는 경우 (size / 2 <= col && size / 2 <= row)
 *    => (현재 공간의 사이즈 / 4) * 3 의 값을 추가로 가지고 분할함
 * 
 *
 */
public class BOJ_1074_Z {

	static BufferedReader br;
	
	static int mapSize, row, col;

	public static void main(String[] args) throws IOException {
		init();

		findClock(row, col, (int) Math.pow(2, mapSize), 0);
	}
	
	static void findClock(int row, int col, int size, int base) {
		// 만약 공간의 길이가 2라면
		if (size == 2) {
			// (현재까지 가지고 내려온 값) + row * 2 + col 를 출력
			System.out.print(base + row * 2 + col);
			return;
		}
		
		// 2사분면 
		if (size / 2 <= col && size / 2 > row) {
			// 현재 공간의 사이즈 / 4 의 값을 추가로 가지고 분할함
			findClock(row, col - size / 2, size / 2, base + (size * size) / 4);
		}
		// 1사분면
		else if (size / 2 > col && size / 2 > row) {
			// 추가로 값을 가지지 않고 분할함
			findClock(row, col, size / 2, base);
		}
		// 3사분면
		else if (size / 2 > col && size / 2 <= row) {
			// (현재 공간의 사이즈 / 4) * 2의 값을 추가로 가지고 분할함
			findClock(row - size / 2, col, size / 2, base + ((size * size) / 4) * 2);
		}
		// 4사분면
		else {
			// (현재 공간의 사이즈 / 4) * 3 의 값을 추가로 가지고 분할함
			findClock(row - size / 2, col - size / 2, size / 2, base + ((size * size) / 4) * 3);
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
	}
}
