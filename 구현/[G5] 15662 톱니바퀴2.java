import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 실제로 회전시키지 않고 톱니바퀴별 현재 왼쪽 오른쪽에 위치한 인덱스를 기억해 사용
 *
 * 돌아가는 경우 돌아가게 만든 톱니와 반대 방향으로 회전
 * 동시에 돌아가므로 먼저 회전시켜면 안됨
 * 회전을 기억해 두었다가 반영
 *
 * 1. 돌리기 시뮬레이션
 * 	1-1. 돌린 톱니 기준 왼쪽, 오른쪽 따로 확인
 * 		1-1-1. 만약 자성이 같다면 확인 중지
 * 		1-2-1. 자성이 다르다면
 * 			1-2-1-1. 만약 처음 돌린 톱니와 거리가 홀수인경우
 * 				1-2-1-1-1. 해당 톱니의 회전 방향을 -1 * (처음톱니방향)으로 기록
 * 			1-2-1-2. 만약 처음 돌린 톱니와 거리가 짝수인경우
 * 				1-2-1-2-1. 해당 톱니의 회전 방향을 처음톱니방향으로 기록
 *
 * 2. 기록된 방향에 따라 회전 반영
 *
 * 3. 점수 계산
 */
public class BOJ_15662_톱니바퀴2 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int TEETH_COUNT = 8;

	static int queryCount, gearCount;
	static boolean[][] magnatic;
	static int[] moveDirection;
	static int[] left, right;

	public static void main(String[] args) throws Exception {
		init();

		useQuery();
		System.out.print(getScore());
	}

	static void useQuery() throws Exception {
		queryCount = Integer.parseInt(br.readLine());
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int gearNumber = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());

			moveDirection = new int[gearCount];
			moveDirection[gearNumber] = direction;

			// 돌리기 시뮬레이션
			// 왼쪽
			for (int gear = gearNumber - 1; gear >= 0; gear--) {
				// 자성 비교
				if (magnatic[gear][right[gear]] != magnatic[gear + 1][left[gear + 1]]) {
					// 처음 회전한 톱니와 홀수개 떨어져있으면 방향 반대
					moveDirection[gear] = (((gearNumber - gear) & 1) == 0) ? direction : -direction;
				}
				// 만약 같다면 회전 중지
				else {
					break;
				}
			}

			// 오른쪽
			for (int gear = gearNumber + 1; gear < gearCount; gear++) {
				// 자성 비교
				if (magnatic[gear - 1][right[gear - 1]] != magnatic[gear][left[gear]]) {
					// 처음 회전한 톱니와 홀수개 떨어져있으면 방향 반대
					moveDirection[gear] = (((gear - gearNumber) & 1) == 0) ? direction : -direction;
				}
				// 만약 같다면 회전 중지
				else {
					break;
				}
			}

			// 움직임 반영
			for (int gear = 0; gear < gearCount; gear++) {
				rotateGear(gear, moveDirection[gear]);
			}
		}
	}

	static void rotateGear(int gearNumber, int direction) {
		if (direction == 0) {
			return;
		}

		// 반시계방향 회전
		if (direction == -1) {
			left[gearNumber] = (left[gearNumber] + 1) % TEETH_COUNT;
			right[gearNumber] = (right[gearNumber] + 1) % TEETH_COUNT;
		}
		// 시계방향 회전
		else if (direction == 1) {
			left[gearNumber] = (left[gearNumber] - 1 + TEETH_COUNT) % TEETH_COUNT;
			right[gearNumber] = (right[gearNumber] - 1 + TEETH_COUNT) % TEETH_COUNT;
		}
	}

	static int getScore() {
		int score = 0;
		for (int gear = 0; gear < gearCount; gear++) {
			int topIndex = (left[gear] + 2) % TEETH_COUNT;
			// 만약 위쪽이 S극 이라면 점수 올림
			if (magnatic[gear][topIndex]) {
				score++;
			}
		}

		return score;
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		gearCount = Integer.parseInt(br.readLine());

		magnatic = new boolean[gearCount][TEETH_COUNT];
		for (int gear = 0; gear < gearCount; gear++) {
			String line = br.readLine();
			for (int teeth = 0; teeth < TEETH_COUNT; teeth++) {
				if (line.charAt(teeth) == '1') {
					magnatic[gear][teeth] = true;
				}
			}
		}

		left = new int[gearCount];
		right = new int[gearCount];
		Arrays.fill(left, 6);
		Arrays.fill(right, 2);
	}
}
