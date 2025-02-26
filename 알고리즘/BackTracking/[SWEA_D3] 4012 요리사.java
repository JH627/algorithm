import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. N개의 식재료 중 절반을 선택하여 A음식과 B음식으로 나눈다.
 * 2. 조합 방식을 이용하여 가능한 경우의 수를 탐색한다.
 * 3. 각 음식의 시너지 값을 계산한 후 차이를 구한다.
 * 4. 최솟값을 갱신하며 탐색을 진행한다.
 * 5. 모든 경우를 고려한 후 최솟값을 출력한다.
 *
 */
public class SWEA_4012_요리사 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int elementCount; // 식재료의 개수
	static int[][] synergy; // 식재료들의 시너지
	static boolean[] isSelected; // 해당재료가 a음식인지, b음식인지 확인할 배열 
	static int minGap; // 맛의 최소 차이

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();

			makeFood(0, 0);

			sb.append("#").append(testCase).append(" ").append(minGap).append("\n");
		}

		System.out.print(sb);
	}

	static void makeFood(int elementIndex, int selectedCount) {
		// 재료를 절반씩 나누어 가진 경우 두 음식의 시너지 차이를 계산
		if (selectedCount == elementCount / 2) {
			minGap = Math.min(minGap, compareTaste());
			return;
		}

		// 만약 다 골라도 재료를 절반씩 나누어 가질 수 없는 경우 조기 종료
		if (elementCount - elementIndex < elementCount / 2 - selectedCount) {
			return;
		}

		// 재료를 하나씩 확인
		for (int index = elementIndex; index < elementCount; index++) {
			// 만약 a음식에 아직 들어가지 않은 재료인 경우
			if (!isSelected[index]) {
				// 재료를 넣고 분기
				isSelected[index] = true;

				makeFood(index, selectedCount + 1);

				// 최솟값 0이 발견된경우 분기 조기 종료
				if (minGap == 0) {
					return;
				}
				
				// 재료 빼기
				isSelected[index] = false;
			}
		}

	}

	// 시너지를 계산하고 차이를 반환하는 함수
	static int compareTaste() {
		int aFoodTaste = 0; // a음식 시너지
		int bFoodTaste = 0; // b음식 시너지
		for (int index = 0; index < elementCount; index++) {
			// 모든 재료를 확인해보며
			for (int compareIndex = index + 1; compareIndex < elementCount; compareIndex++) {
				// 해당 재료와 같은 음식에 들어가있는 경우 시너지를 계산해서 더함
				// A음식에 같이 들어가 있는 경우
				if (isSelected[index] && isSelected[compareIndex]) {
					aFoodTaste += synergy[index][compareIndex] + synergy[compareIndex][index];
				}
				// B음식에 같이 들어가 있는 경우
				else if (!isSelected[index] && !isSelected[compareIndex]) {
					bFoodTaste += synergy[index][compareIndex] + synergy[compareIndex][index];
				}
			}
		}
		
		// 맛 차이 반환
		return Math.abs(aFoodTaste - bFoodTaste);
	}

	static void init() throws IOException {
		elementCount = Integer.parseInt(br.readLine());

		isSelected = new boolean[elementCount];
		synergy = new int[elementCount][elementCount];

		StringTokenizer st;
		for (int row = 0; row < elementCount; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < elementCount; col++) {
				synergy[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		minGap = Integer.MAX_VALUE;
	}
}
