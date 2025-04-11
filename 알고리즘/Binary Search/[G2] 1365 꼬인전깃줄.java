import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 최장 수열
 * 
 * 시작 지점은 오름차순이므로 전깃줄이 꼬이지 않기 위해선
 * 이전 지점의 전기줄이 지금 선택하는 전봇대 보다 작아야한다.
 * => 최장 수열
 * 
 * 1. 주어진 배열에서의 최장수열을 구한다.
 * 2. 최장수열에 포함되지 않은 숫자는 넣는 경우 전선에 꼬이는 경우 이므로
 * 3. 전체 전선의 개수 - 최장수열의 길이 출력
 *
 */
public class BOJ_1365_꼬인전깃줄 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int elementCount;
	static int[] dest, LIS;
	
	public static void main(String[] args) throws Exception {
		init();

		System.out.print(elementCount - getLISLength());
	}
	
	static int getLISLength() {
		LIS = new int[elementCount];
		int length = 0;
		
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			// 키(numbers[elementIndex]가 존재할 경우 그 인덱스 반환
			// 존재하지 않으면, 키가 들어갈 수 있는 위치를 계산해서 -insertionPoint - 1 반환
			int pos = Arrays.binarySearch(LIS, 0, length, dest[elementIndex]);
			
			// 키가 정확하게 존재하지 않을 때 들어갈 수 있는 위치 계산
			int upperBound = Math.abs(pos) - 1;
			LIS[upperBound] = dest[elementIndex];
			
			if (upperBound == length) {
				length++;
			}		
		}
		
		return length;
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		elementCount = Integer.parseInt(br.readLine());
		
		dest = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < elementCount; index++) {
			dest[index] = Integer.parseInt(st.nextToken());
		}
	}
}
