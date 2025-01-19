import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] wheel;
	static final int WHEEL_COUNT = 4;
	static final int NUM_MAX = 8;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		wheel = new boolean[WHEEL_COUNT][NUM_MAX];
		
		for (int i = 0; i < WHEEL_COUNT; i++) {
			String s = br.readLine();
			for (int j = 0; j < NUM_MAX; j++) {
				if (s.charAt(j) == '1') {
					wheel[i][j] = true;
				}
			}
		}
		
		int[] top = new int[WHEEL_COUNT];
		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());
			
			int[] directions = new int[WHEEL_COUNT];
			directions[number] = direction;
			
			for (int i = number - 1; i >= 0; i--) {	
				if (directions[i + 1] == 0) {
					break;
				}
				if (wheel[i + 1][getLeft(top[i + 1])] != wheel[i][getRight(top[i])]) {
					directions[i] = directions[i + 1] * -1;
				}
			}
			for (int i = number + 1; i < WHEEL_COUNT; i++) {
				if (directions[i - 1] == 0) {
					break;
				}
				if (wheel[i - 1][getRight(top[i - 1])] != wheel[i][getLeft(top[i])]) {
					directions[i] = directions[i - 1] * -1;
				}
			}
			
			for (int i = 0; i < WHEEL_COUNT; i++) {
				top[i] = (top[i] - directions[i] + NUM_MAX) % NUM_MAX;
			}
		}
		
		int point = 0;
		for (int i = 0; i < WHEEL_COUNT; i++) {
			if (wheel[i][top[i]]) {
				point += Math.pow(2, i);
			}
		}
		
		System.out.print(point);
	}
	
	static int getLeft(int topIndex) {
		return (topIndex - 2 + NUM_MAX) % NUM_MAX;
	}
	
	static int getRight(int topIndex) {
		return (topIndex + 2) % NUM_MAX;
	}

}
