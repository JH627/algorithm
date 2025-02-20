import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 누적합 활용
 * 
 * 풀이
 * 사전 조건: 배열은 (요소크기 + 1) 크기로 생성하고, 
 * 인덱스는 1부터 활용한다 => 이전 요소의 값을 활용하기 위함  
 * 1. 요소를 하나씩 입력받는다.
 * 2. 입력받은 요소의 값과 이전 요소들의 합을 활용하여 배열에 저장한다.
 * 			(elementSum[elementIndex] = elementSum[elementIndex - 1] + value)
 * 3. 쿼리를 입력받고 누적합[끝 인덱스] - 누적합[시작인덱스 - 1] => 시작인덱스 값~끝 인덱스 값의 합 을 계산하여 출력한다. 
 *
 */
public class BOJ_11659_구간합구하기4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int elementCount, queryCount; // elementCount: 요소의 개수, queryCount: 구간합을 구할 쿼리의 개수
	static int[] elementSum; // 누적합을 저장할 배열
	
	public static void main(String[] args) throws Exception {
		init();

		findQuries();
		
		System.out.print(sb);
	}
	
	static void findQuries() throws Exception {
		st = new StringTokenizer(br.readLine());
		int value;
		for (int elementIndex = 1; elementIndex < elementCount + 1; elementIndex++) {
			// 값을 하나 입력 받고
			value = Integer.parseInt(st.nextToken());
			// 이전까지 누적된 값과 더하여 저장
			elementSum[elementIndex] = elementSum[elementIndex - 1] + value;
		}
		
		// 쿼리를 하나씩 실행하며
		// 누적합[끝 인덱스] - 누적합[시작인덱스 - 1]를 통해 시작인덱스 값~끝 인덱스 값의 합을
		// 계산하고 출력한다.
		for (int queryIdx = 0; queryIdx < queryCount; queryIdx++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 시작 인덱스
			int end = Integer.parseInt(st.nextToken()); // 끝 인덱스
			sb.append(elementSum[end] - elementSum[start - 1]).append("\n");
		}
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		elementCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());
		
		elementSum = new int[elementCount + 1]; // 첫 번째 요소가 참고할 요소가 필요하기에 크기를 하나 더 크게 생성
	}

}
