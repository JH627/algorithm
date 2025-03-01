import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 *
 * 1. 맨 왼쪽 계란에서 시작한다.
 * 2. 계란을 들고 하나씩 쳐본다.
 * 2-1. 만약 부서진 계란이라면 치지않음
 * 2-2. 자기 자신을 칠 수 없음
 * 2-3. 들고 있는 계란이 이미 부서진 경우 다음 (오른쪽) 계란 확인
 * 2-4. 만약 칠 수 있는 상태라면
 * 2-4-1. 계란을 서로 쳐보고 값 갱신 
 * 2-4-2. 부서졌다면 부서진 개수를 갱신 후 오른쪽 달걀로 분기
 * 2-4-3. 복귀 후 다시 내구도 복구
 * 
 */
public class BOJ_16987_계란으로계란치기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int elementCount; // 계란 수
	static int maxCount; // 최대로 부서진 계란의 수
	static int[] durability, weight; // durability: 내구도, weight: 무게

	public static void main(String[] args) throws IOException {
		init();

		getMaxCount(0, 0);

		System.out.print(maxCount);
	}

	static void getMaxCount(int elementIndex, int breakCount) {
		// 만약 오른쪽 끝 계란까지 다 확인한 경우 최대값 갱신
		if (elementIndex == elementCount) {
			maxCount = Math.max(maxCount, breakCount);
			return;
		}
		
		// 계란이 하나 빼고 다 부서진 경우에는 어차피 최대값을 갱신 불가
		if (breakCount == elementCount - 1) {
			maxCount = Math.max(maxCount, breakCount);
			return;
		}

		// 들고 있는 계란이 부서진 경우
		if (durability[elementIndex] <= 0) {
			// 다음 계란 확인
			getMaxCount(elementIndex + 1, breakCount);
			return;
		}

		// 계란을 하나씩 쳐본다
		for (int index = 0; index < elementCount; index++) {
			// 자기 자신은 칠 수 없음
			if (index == elementIndex) {
				continue;
			}
			
			// 치려는 계란이 부서진 경우 제외
			if (durability[index] <= 0) {
				continue;
			}

			// 쳐보기
			durability[elementIndex] -= weight[index];
			durability[index] -= weight[elementIndex];

			// 부서졌다면 부서진 개수를 셈
			int tempCount = 0;
			if (durability[index] <= 0) {
				tempCount++;
			}
			if (durability[elementIndex] <= 0) {
				tempCount++;
			}
			
			// 부서진 개수를 갱신하고 오른쪽 계란 확인
			getMaxCount(elementIndex + 1, breakCount + tempCount);

			// 쳐보기 전 상태로 복구
			durability[elementIndex] += weight[index];
			durability[index] += weight[elementIndex];
		}
	}

	static void init() throws IOException {
		elementCount = Integer.parseInt(br.readLine());
		durability = new int[elementCount];
		weight = new int[elementCount];

		StringTokenizer st;
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			st = new StringTokenizer(br.readLine());
			durability[elementIndex] = Integer.parseInt(st.nextToken());
			weight[elementIndex] = Integer.parseInt(st.nextToken());
		}

		maxCount = 0;
	}
}
