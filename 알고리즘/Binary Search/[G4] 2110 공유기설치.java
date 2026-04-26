import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_공유기설치 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int[] position;
	static int homeCount, gasCount;
	
	public static void main(String[] args) throws Exception {
		init();
		
		int maxDistance = findMaxDistance();
		
		System.out.print(maxDistance);
	}
	
	static int findMaxDistance() {
		int l = 0;
		int r = position[homeCount - 1];
		
		
		while (l < r) {
			int mid = (l + r) / 2;
			// 만약 지금거리도 가능
			// 범위 좁히기
			if (checkIsPossible(mid)) {
				r = mid;
			}
			// 지금 거리로는 불가능
			// 최소 지금 거리보다는 커야함
			else {
				l = mid + 1;
			}
		}

		return r;
	}
	
	static boolean checkIsPossible(int distance) {
		int count = 1;
		int currentIndex = 0;
		
		for (int index = currentIndex; index < homeCount; index++) {
			// 이전에 설치한 가스가 최대로 닿는 곳을 확인
			while (position[index] - position[currentIndex] <= distance) {
				index++;
				// 최대로 닫는 곳이 배열 끝을 넘어 선다면 가능
				if (index == homeCount) {
					return true;
				}
			}
			
			// 만약 최대로 닫는 곳을 확인했는데 집이 남은 경우
			// 해당집에 가스통 설치
			currentIndex = index;
			// 설치 개수가 제한을 넘는다면 불가능한 거리
			if (++count >= gasCount) {
				return false;
			}
		}
		
		return true;
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		homeCount = Integer.parseInt(st.nextToken());
		gasCount = Integer.parseInt(st.nextToken());
		
		position = new int[homeCount];
		for (int homeIndex = 0; homeIndex < homeCount; homeIndex++) {
			position[homeIndex] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(position);
	}
}
