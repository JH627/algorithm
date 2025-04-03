import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1. 요소를 하나씩 확인한다.
 * 1-1. 만약 현재 확인 중인 요소의 길이가 k개를 넘지 않는 경우
 * 1-2. 현재 초밥의 종류 등장 횟수를 1 늘림
 * 1-2-1. 만약 1 늘리기 전에 0이었다면 얻을 수 있는 초밥의 종류를 1 늘림
 * 
 * 2-2. 요소의 길이가 k개를 넘는 경우
 * 2-2-1. 가장 오래전에 넣은 초밥 등장 횟수를 1 감소
 * 2-2-2. 가장 오래전에 넣은 초밥의 종류 등장 횟수가 0이 된다면
 * 2-2-2-1. 얻을 수 있는 초밥의 개수 1 감소
 * 
 * 3. 길이가 k개로 맞춰진 경우
 * 4-1. 만약 쿠폰으로 먹을 수 있는 초밥을 먹은 경우
 * 4-1-1. 현재 얻을 수 있는 초밥의 개수와 최대값 비교
 * 4-2. 만약 쿠폰으로 먹을 수 있는 초밥을 먹지 않은 경우
 * 4-2-1. 현재 얻을 수 있는 초밥의 개수 + 1 과 최대값 비교
 * 
 */
public class BOJ_15961_회전초밥 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int elementCount, kindCount, consecutiveCount, couponNumber;
	static int[] elementKind;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(findMaxKind());
	}
	
	static int findMaxKind() {
		int maxKind = 0;
		int[] count = new int[kindCount + 1];
		
		int kind = 0;
		int l = 0;
		for (int r = 0; r < consecutiveCount; r++) {
			if (count[elementKind[r]]++ == 0) {
				kind++;						
			}	
		}
		
		
		for (int r = consecutiveCount; r < elementCount + consecutiveCount; r++) {
			if (--count[elementKind[l]] == 0) {
				kind--;
			}
				
			l++;
				
			if (count[elementKind[r]]++ == 0) {
				kind++;
			}
			
		
			int extraKind = count[couponNumber] == 0 ? 1 : 0;
			maxKind = Math.max(maxKind, kind + extraKind);
		}
		
		return maxKind;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		elementCount = Integer.parseInt(st.nextToken());
		kindCount = Integer.parseInt(st.nextToken());
		consecutiveCount = Integer.parseInt(st.nextToken());
		couponNumber = Integer.parseInt(st.nextToken());
		
		elementKind = new int[elementCount + consecutiveCount];
		for (int elementIndex = 0; elementIndex < consecutiveCount; elementIndex++) {
			elementKind[elementIndex] = Integer.parseInt(br.readLine());
		}
		
		for (int elementIndex = elementCount; elementIndex < elementCount - consecutiveCount; elementIndex++) {
			elementKind[elementIndex] = elementKind[elementIndex - elementCount];
		}
		
	}
	
}
