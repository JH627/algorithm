import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 그리디 문제
 * 
 * 풀이
 * 
 * 1. 남은 설탕의 킬로그램이 5로 나누어 떨어지는 경우에 (기존 봉투 개수) + (남은 설탕의 킬로그램 / 5) 
 *    => 가능하면 크게크게 담는게 봉투를 가장 적게 쓸 수 있다
 * 2. 만약 5로 나누어 떨어지지 않는다면
 * 2-1. 3을 빼고 봉투의 개수를 1개 늘린다. (3kg짜리 설탕봉투를 하나 만드는 효과)
 * 2-2. 만약 3을 뺐는데 0보다 작아진 경우
 *      => 3과 5로 만들 수 있는 경우의 수가 없으므로 -1을 출력
 *
 */
public class BOJ_2839_설탕배달 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int sugar;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(getMinPaperbag());
	}
	
	static int getMinPaperbag() {
		int minPaperbag = 0;
		
		while (true) {
			// 5로 나누어 떨어지는 경우
			if (sugar % 5 == 0) {
				// (기존 봉투 개수) += (남은 설탕의 킬로그램 / 5) 
				minPaperbag += sugar / 5;
				return minPaperbag;
			}
			
			// 5로 나누어 떨어지지 않는 경우
			// 3kg 봉지를 하나 만든다
			sugar -= 3;
			minPaperbag++;

			// 현재 남은 설탕의 양이 0보다 작은 경우 -1 출력
			if (sugar < 0) {
				return -1;
			}
		}
	}
	
	static void init() throws Exception {
		sugar = Integer.parseInt(br.readLine());
	}
	
}
