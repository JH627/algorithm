import java.io.IOException;
import java.util.Scanner;

/**
 * 풀이
 * 
 * 1. 높이를 입력받는다.
 * 2. 배열 요소를 앞부터 하나씩 확인하며 다음요소(오른쪽 요소)에 대하여 증가하는지 감소하는지 기록
 * 3. 최초에 증가 상태를 가지고 시작
 * 4. 감소하는 모양이 나올때까지 증가 수열의 길이를 1씩 늘림
 * 5. 감소 수열이 나오면 증가하는 모양이 나올 때까지 감소 수열의 길이를 1씩 늘림
 * 6. 감소 수열상태에서 증가하는 모양이 나온 경우나 또는 요소를 모두 탐색 하였는데 감소하는 상태에서 끝난 경우 구간의 개수 계산 및 합산
 * - 구간의 개수
 * => (증가 수열의 길이 - 1) * (감소 수열의 길이 - 1)
 *
 */
public class SWEA_4796_의석이의우뚝선산 {

	static Scanner sc = new Scanner(System.in);

	static int elementCount; // 요소의 개수
	static boolean[] isDown, isUp; // 각 요소의 증가, 감소 상태
	static int[] heights; // 높이
	static int sectionCount; // 정답 구간의 개수

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int TC = sc.nextInt();
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();

			getSectionCount();
			
			sb.append("#").append(testCase).append(" ").append(sectionCount).append("\n");
		}

		System.out.print(sb);
	}
	
	static void getSectionCount() {
		boolean isUpStatus = true;
		
		int continuousUpCount = 0; // 증가 수열의 길이
		int continuousDownCount = 0; // 감소 수열의 길이
		
		int nowIndex = 0;
		while (true) {
			// 요소를 끝까지 탐색한 경우
			if (nowIndex == elementCount) {
				// 만약 감소중에 끝났다면 최종적으로 구간의 개수를 계산 후 합산
				if (!isUpStatus) {
					sectionCount += (continuousUpCount - 1) * (continuousDownCount - 1);
				}
				break;
			}
			
			// 올라가는 중인 경우
			if (isUpStatus) {
				continuousUpCount++;
				// 만약 다음 수가 감소한다면
				if (isDown[nowIndex]) {
					// 감소 수열 길이 초기화
					continuousDownCount = 1;
					// 감소 상태로 변경
					isUpStatus = false;
				}	
			}
			// 내려가는 중인 경우
			else {
				continuousDownCount++;
				// 다시 올라가는 경우 구간 개수 구함
				if (isUp[nowIndex]) {
					sectionCount += (continuousUpCount - 1) * (continuousDownCount - 1);
					// 증가 수열 길이 초기화
					continuousUpCount = 1;
					// 증가상태로 변경
					isUpStatus = true;
				}
			}

			nowIndex++;
		}
	}

	static void init() throws IOException {
		elementCount = sc.nextInt();

		heights = new int[elementCount];
		for (int index = 0; index < elementCount; index++) {
			heights[index] = sc.nextInt();
		}
		
		isDown = new boolean[elementCount];
		isUp = new boolean[elementCount];
		
		for (int index = 0; index < elementCount - 1; index++) {
			// 다음 수가 더 큰 경우
			if (heights[index] < heights[index + 1]) {
				isUp[index] = true;
			}
			// 다음 수가 더 작은 경우
			else {
				isDown[index] = true;
			}
		}
		
		sectionCount = 0;
	}
	
}
