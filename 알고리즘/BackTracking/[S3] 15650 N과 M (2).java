import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Combination 실습
 * 조합(Combination): 서로 다른 n개의 원소 중 r개를 순서 없이 골라낸 것
 * 
 * combination 알고리즘
 * 1. N(elementCount)개의 요소 중에서 R(selectedCount)개를 선택하여 나열하는 모든 경우를 생성한다.
 * 2. 요소를 선택할 때, 중복을 허용함
 * 3. 요소를 고르고 넘어가거나
 * 3-1 요소를 고르지 않고 넘어간다
 * 4. 만약 R(selectedCount)개를 모두 고른경우 (selectIndex == selectedCount) 요소 출력
 * 4-1. 만약 N개의 요소를 모두 돌았는데 발견하지 못한 경우(elementIndex == elementCount) 함수 종료
 *
 */
public class BOJ_15650_N과M2 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int elementCount, selectedCount; // elementCount: 활용할 요소의 개수, selectedCount: 선택할 요소의 개수
	static int[] selectedNumbers; // 선택된 요소를 저장할 배열
	
	public static void main(String[] args) throws Exception {
		init();
		
		combination(0, 0);
		
		System.out.print(sb);
	}
	
	static void combination(int elementIndex, int selectIndex) {
		// 요소를 모두 고른 경우
		if (selectIndex == selectedCount) {
			for (int index = 0; index < selectedCount; index++) {
				// 하나씩 출력
				sb.append(selectedNumbers[index]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 만약 요소 끝까지 탐색 했는데 전부 고르지 못한 경우 종료
		if (elementIndex == elementCount) {
			return;
		}
		
		// 이번 요소를 고르는 경우
		selectedNumbers[selectIndex] = elementIndex + 1; // 출력할 요소 배열에 저장
		combination(elementIndex + 1, selectIndex + 1);
		
		// 이번 요소를 고르지 않는 경우
		combination(elementIndex + 1, selectIndex);
	}
	
	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		selectedCount = Integer.parseInt(st.nextToken());
		
		selectedNumbers = new int[selectedCount];
	}

}
