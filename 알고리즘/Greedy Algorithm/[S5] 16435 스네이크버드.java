import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 입력받은 과일의 높이를 오름차순으로 정렬
 * 2. 과일 배열을 하나씩 탐색하며
 * 2-1. 만약 과일의 높이가 현재 내 길이와 같거나 작다면 나의 길이 + 1
 * 2-2. 만약 과일의 높이가 내 길이보다 크다면 탐색 종료
 *
 */
public class BOJ_16435_스네이크버드 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int elementCount, currentLength; // elementCount: 과일의 개수, currentLength: 스네이크버드의 초기 길이 정수
	static ArrayList<Integer> prey; // 과일의 높이
	 
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(getMaxSnakeLength());
	}
	
	static int getMaxSnakeLength() {
		int nowLength = currentLength; // 초기 길이 상태로 초기화
		
		for (int preyPower : prey) {
			// 만약 과일의 높이가 현재 내 길이와 같거나 작다면 나의 길이 + 1
			if (preyPower <= nowLength) {
				nowLength++;
			}
			// 만약 과일의 높이가 내 길이보다 크다면 탐색 종료
			else {
				break;
			}			
		}
		
		return nowLength;
	}

	static void init() throws Exception {
		prey = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		currentLength = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			prey.add(Integer.parseInt(st.nextToken()));
		}
		
		// 입력받은 과일의 높이를 오름차순으로 정렬
		Collections.sort(prey);
	}
	
}
