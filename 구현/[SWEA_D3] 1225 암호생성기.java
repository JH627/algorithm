import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 감소량
 * 1 2 3 4 5 1 2 3
 * 4 5 1 2 3 4 5 1
 * 2 3 4 5 1 2 3 4
 * 5 1 2 3 4 5 1 2
 * 3 4 5 1 2 3 4 5
 *
 * 각 사이클당 15씩 줄어든다
 *
 * 1. 요소들중 최소값이 15이상이라면 사이클을 미리 돌린다.
 * 2. (최소값 / 15) - 1 번의 사이클을 돌림 => 각 요소에서 (사이클횟수 * 15) 만큼의 값을 뺌
 * 2-1. (최소값 / 15) - 1 인 이유 => -1을 하지 않으면 15의 배수인 경우 빼는 과정에서 0이 될수있음
 * 3. 값을 뺀 리스트에서 실제로 사이클을 돌리며 값을 뺀다
 * 3-1. 만약 0이하가 되는경우 사이클 돌리기 종료 후 현재 배열 출력
 * 
 * 실제로 배열을 돌리는 것이 아닌
 * 인덱스 번호를 +1씩 하며 배열을 돌리는 효과를 냄
 * 
 */
public class SWEA_1225_암호생성기 {

	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static final int TEST_CASE = 10; // 테스트 케이스 개수
	static final int ELEMENT_COUNT = 8; // 요소의 개수
	static final int MINUS_LIMIT = 6; // 한 사이클에서 최대 마이너스 값

	static int minValue;
	static int[] numbers = new int[ELEMENT_COUNT];

	public static void main(String[] args) throws IOException {
		for (int testCase = 1; testCase <= TEST_CASE; testCase++) {
			init();
			getSecretKey();
		}

		System.out.print(sb);
	}

	static void getSecretKey() {

		// 제일 작은 요소가 15 이상인 경우
		if (minValue > 15) {
			// (사이클의 개수 - 1) * 15 만큼 미리 숫자를 뺀다
			int cycleCount = minValue / 15 - 1;
			for (int index = 0; index < ELEMENT_COUNT; index++) {
				numbers[index] -= cycleCount * 15;
			}
		}

		// 직접 배열에서 숫자들을 빼보기
		int currentIndex = 0; // 현재 인덱스
		int minusClock = 1; // 이번에 빼야하는 값
		while (true) {
			// 숫자를 뺌
			numbers[currentIndex] -= minusClock;
			// 뺀 값이 0 이하인 경우 루프 종료
			if (numbers[currentIndex] <= 0) {
				numbers[currentIndex] = 0;
				break;
			}
			// 다음에 빼야하는 값과 현재 인덱스 갱신
			minusClock = getNextMinusClock(minusClock);
			currentIndex = (currentIndex + 1) % ELEMENT_COUNT; // 배열을 돌리는 효과
		}

		for (int index = 0; index < ELEMENT_COUNT; index++) {
			sb.append(numbers[(currentIndex + 1 + index) % ELEMENT_COUNT]).append(" ");
		}
		sb.append("\n");
	}

	// 다음번에 빼야하는 값을 계산하는 함수
	static int getNextMinusClock(int currentMinusClock) {
		// 0을 빼지는 않으므로 최대 감소량에 온 경우 1로 초기화
		if (currentMinusClock + 1 == MINUS_LIMIT) {
			return 1;
		}
		// 최대 감소량이 아닌 경우 현재 감소량 + 1이 다음 감소량
		return currentMinusClock + 1;
	}

	static void init() throws IOException {
		int caseNumber = Integer.parseInt(br.readLine());
		sb.append("#").append(caseNumber).append(" ");

		minValue = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < ELEMENT_COUNT; index++) {
			numbers[index] = Integer.parseInt(st.nextToken());
			minValue = Math.min(minValue, numbers[index]);
		}
	}
}
