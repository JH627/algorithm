import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 주어진 연산자의 개수로 만들 수 있는 모든 경우의 수를 계산
 * 1-1. 4개의 연산자를 확인해보며 만약 사용가능한 연산자가 있는 경우
 *      사용 처리후 각각 새로 분기
 * 1-2. 만약 모든 연산자를 사용했다면 최대, 최소 값 갱신
 * 2. 각 경우의 수를 통해 얻을 수 있는 실제 값을 계산하고 
 *    최대값, 최소값 갱신
 *    
 */
public class SWEA_4008_숫자만들기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int elementCount; // 숫자의 개수
	static int minResult, maxResult; // 최소, 최대 값
	static int[] operatorLimit; // 연산자 제한개수
	static int[] baseNumbers; // 게임판

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();

			getNextResult(1, baseNumbers[0]);

			int gap = maxResult - minResult; // 최대값 - 최소값

			sb.append("#").append(testCase).append(" ").append(gap).append("\n");
		}

		System.out.print(sb);
	}

	static void getNextResult(int elementIndex, int currentValue) {
		// 만약 요소를 끝까지 탐색한 경우 (= 연산자를 모두 사용한 경우)
		// 최대, 최소 값 갱신
		if (elementIndex == elementCount) {
			minResult = Math.min(minResult, currentValue);
			maxResult = Math.max(maxResult, currentValue);
			return;
		}
		
		// 연산자를 하나씩 확인
		for (int operatorIndex = 0; operatorIndex < 4; operatorIndex++) {
			// 만약 사용할 수 있는 연산자가 남아있는 경우
			if (operatorLimit[operatorIndex] > 0) {
				// 사용 처리 후 연산 결과를 가지고 분기
				operatorLimit[operatorIndex]--;
				getNextResult(elementIndex + 1, getValue(currentValue, baseNumbers[elementIndex], operatorIndex));
				operatorLimit[operatorIndex]++;
			}
		}
	}

	static int getValue(int leftValue, int rightValue, int operatorIndex) {
		if (operatorIndex == 0) {
			return leftValue + rightValue;
		}
		else if (operatorIndex == 1) {
			return leftValue - rightValue;
		}
		else if (operatorIndex == 2) {
			return leftValue * rightValue;
		}
		else {
			return leftValue / rightValue;
		}
	}

	static void init() throws IOException {
		minResult = Integer.MAX_VALUE;
		maxResult = Integer.MIN_VALUE;
		elementCount = Integer.parseInt(br.readLine());

		operatorLimit = new int[4];
		baseNumbers = new int[elementCount];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int index = 0; index < 4; index++) {
			operatorLimit[index] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < elementCount; index++) {
			baseNumbers[index] = Integer.parseInt(st.nextToken());
		}
	}
}
