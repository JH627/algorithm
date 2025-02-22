import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 *
 * 1. 재료는 최소 하나이상을 포함하기에 각 재료를 하나씩 가지고 경우의 수 탐색 시작
 * 2. 이후에는 재료를 포함하거나 포함하지 않거나 두 상태로 분기하여 탐색
 * 2-1. 중복체크는 필요없음 -> 재료를 하나가지고 시작함, 앞 재료는 안가지고 시작
 * 		같은 경우가 나올 수 없음
 */
public class BOJ_2961_도영이가만든맛있는음식 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int elementCount;
	static int[] sour, bitter; // 각 재료의 신맛, 쓴맛을 저장할 배열
	static int minYummy = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		init();
		
		// 탐색 시작
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			makeYummy(elementIndex + 1, sour[elementIndex], bitter[elementIndex]);
		}

		System.out.print(minYummy);
	}


	static void makeYummy(int elementIndex, int sumSour, int sumBitter) {
		// 만약 끝까지 탐색한 경우 최소값 갱신
		if (elementIndex == elementCount) {
			minYummy = Math.min(minYummy, Math.abs(sumSour - sumBitter));
			return;
		}

		// 재료를 포함하지 않는 경우
		makeYummy(elementIndex + 1, sumSour, sumBitter);
		// 재료를 포함하는 경우
		makeYummy(elementIndex + 1, sumSour * sour[elementIndex], sumBitter + bitter[elementIndex]);
	}

	static void init() throws IOException {
		elementCount = Integer.parseInt(br.readLine());
		
		sour = new int[elementCount];
		bitter = new int[elementCount];

		// 신맛, 쓴맛을 입력받음
		for (int n = 0; n < elementCount; n++) {
			st = new StringTokenizer(br.readLine());
			sour[n] = Integer.parseInt(st.nextToken());
			bitter[n] = Integer.parseInt(st.nextToken());
		}
	}
}
