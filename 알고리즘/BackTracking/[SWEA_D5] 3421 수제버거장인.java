import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 *
 * 1. 햄버거 재료를 하나씩 추가해보며 조합을 구함
 * 
 * 재료를 추가할 때 주의할 점
 * 2. 이미 포함된 재료인 경우 건너뛰기
 * 2-1. 만약 새로운 재료를 추가해서 만든 햄버거가 불가능한 햄버거일 경우 더 이상 진행하지 않기
 *
 * 불가능한 햄버거인 경우
 * (현재 햄버거 + 새로 추가할 버거재료) => 새로운 버거
 * 새로운 버거에 (새로 추가한 버거재료와 어울리지 않는 재료)가 하나라도 포함된 경우
 *
 */
public class SWEA_3421_수제버거장인 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int elementCount, queryCount; // elementCount: 재료의 개수, queryCount: 궁합이 맞지 않는 재료
	static boolean[] visited; // 재료가 선택된 상태인지 관리할 배열
	static int[] disconnected; // 각 재료와 어울리지 않는 재료를 저장할 배열, 한 비트가 한 재료를 의미
	static int count = 0;

	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();

			findPossibleCase(0, 0);

			sb.append("#").append(testCase).append(" ").append(count).append("\n");
		}

		System.out.print(sb);
	}

	// elementIndex: 현재 탐색중인 배열의 인덱스, currentStatus: 현재 그룹에 묶여있는 노드들
	static void findPossibleCase(int elementIndex, int currentStatus) {
		count++; // 함수가 실행된건 가능한 조합이 하나 있는 것을 의미

		// 현재 인덱스부터 탐색
		for (int index = elementIndex; index < elementCount; index++) {
			// 만약 이미 포함이 된 상태가 아니라면
			if (visited[index]) {
				continue;
			}

			// 새로운 햄버거를 만들어 본다.
			currentStatus |= 1 << index;
			// 만약 올바르지 않은 햄버거 조합이 아닌 경우
			if (checkIsPossibleGroup(index, currentStatus)) {
				// 재료를 고르고
				visited[index] = true;
				// 현재 조합을 가지고 새로운 탐색 시작
				findPossibleCase(index, currentStatus);
				// 후처리
				visited[index] = false;
			}
			// 원래 햄버거 상태로 돌리기
			currentStatus &= ~(1 << index);
		}
	}
	
	// 새로운 방문 그룹상태와 새로 추가된 노드가 어울리는지 확인
	// 방문상태에 있는 노드들 중에 새로운 노드와 있을 수 없는 노드가 있는 경우 -> 불가능한 상태
	// 하나씩 검사 O(N) -> 비트연산을 통해 한번에 연산 O(1)
	static boolean checkIsPossibleGroup(int elementIndex, int currentStatus) {
		return (disconnected[elementIndex] & currentStatus) == 0;
	}

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());

		visited = new boolean[elementCount];
		disconnected = new int[elementCount];
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int aIngredient = Integer.parseInt(st.nextToken()) - 1;
			int bIngredient = Integer.parseInt(st.nextToken()) - 1;
			// 비트로 연결 상태를 저장 | 연산의 경우 둘이 a와 b가 함께 있을 수 없음을 의미
			disconnected[aIngredient] |= (1 << bIngredient);
			disconnected[bIngredient] |= (1 << aIngredient);
		}

		count = 0;
	}
}
