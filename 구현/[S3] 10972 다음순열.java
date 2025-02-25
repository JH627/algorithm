import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이
 *
 * 1. 끝부터 요소를 탐색한다.
 * 2. 하나씩 확인하며 만약 3번 조건에 걸리지 않는다면 해당 수를 현재 사용하지 않는 다고 변경
 * 3. 만약 오른쪽에 있는 수가 현재 수(왼쪽에 있는 수)보다 큰 경우 더 큰 순열이 있다고 판단
 * 3-1. 현재 수보다 큰 수 중 가장 작은 수를 가져와서 현재 수를 변경한다.
 * 3-1. 1부터 사용하지 않은 수를 가져와서 차례대로 정답 배열에 저장한다.
 * 4. 만약 요소를 모두 탐색했는데 3번 조건에 걸리는 것이 없었다면 마지막에 오는 순열이므로 -1 출력
 *
 */
public class BOJ_10972_다음순열 {

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int elementCount; // 요소의 개수
	static int[] element; // 정답 배열 (최초에는 문제에서 주어진 순열)
	static boolean[] isSelected; // 1 ~ N까지의 수를 사용했는지 저장할 배열

	public static void main(String[] args) throws IOException {
		init();

		findNextPermutation();

		System.out.print(sb);
	}

	static void findNextPermutation() {
		// 맨 뒤의 숫자는 비교 대상이 없으므로 일단 사용할 수 있는 수에 저장
		isSelected[element[elementCount - 1]] = false;

		// 끝부터 요소를 탐색한다
		for (int elementIndex = elementCount - 2; elementIndex >= 0; elementIndex--) {
			// 해당 수를 현재 사용하지 않는 다고 변경
			isSelected[element[elementIndex]] = false;

			// 만약 오른쪽에 있는 수가 현재 수(왼쪽에 있는 수)보다 큰 경우 더 큰 순열이 있다고 판단
			if (element[elementIndex] > element[elementIndex + 1]) {
				continue;
			}

			// 더 큰 순열이 있는 경우
			// 현재 수보다 큰 수 중 가장 작은 수를 가져와서 현재 수를 변경한다.
			for (int number = element[elementIndex] + 1; number <= elementCount; number++) {
				if (!isSelected[number]) {
					isSelected[number] = true;
					element[elementIndex] = number;
					break;
				}
			}

			// 1부터 사용하지 않은 수를 가져와서 차례대로 정답 배열에 저장한다.
			for (int number = 1; number <= elementCount; number++) {
				if (!isSelected[number]) {
					element[++elementIndex] = number;
				}
				if (elementIndex == elementCount - 1) {
					break;
				}
			}

			// 정답 출력
			for (int number : element) {
				sb.append(number).append(" ");
			}
			return;
		}

		// 만약 요소를 모두 탐색했는데 3번 조건에 걸리는 것이 없었다면 마지막에 오는 순열이므로 -1 출력
		sb.append(-1);
	}

	static void init() throws IOException {
		elementCount = Integer.parseInt(br.readLine());
		element = new int[elementCount];
		isSelected = new boolean[elementCount + 1];
		Arrays.fill(isSelected, true);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int index = 0; index < elementCount; index++) {
			element[index] = Integer.parseInt(st.nextToken());
		}
	}
}
