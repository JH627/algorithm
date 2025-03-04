import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 풀이
 *
 * 1. 가능한 연산:
 *   - N → N + 1
 *   - N → sqrt(N) (단, N이 완전제곱수일 때만 가능)
 *
 * 2. 숫자를 줄이는 방법은 제곱근 연산뿐
 *    => 최대한 빠르게 제곱근 연산을 해야한다.
 * 3. 가장 가까운 완전 제곱수로 가기
 *    => 현재 숫자가 완전제곱수라면, 제곱근을 취한다.
 * 	  => 완전제곱수가 아니라면, 다음 완전제곱수로 만들기 위해 완전제곱수 까지 N → N + 1 연산을 수행한 후 제곱근을 취한다.
 */
public class SWEA_6782_현주가좋아하는제곱근놀이 {

	static BufferedReader br;
	static long number; // 입력으로 주어지는 숫자

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();

			int count = getCount();

			sb.append("#").append(testCase).append(" ").append(count).append("\n");
		}

		System.out.print(sb);
	}

	static int getCount() {
		int count = 0;

		// 목표 숫자인 2가 될 때까지 반복
		while (number != 2) {
			// 현재 숫자의 제곱근을 구함
			long sqrt = (long) Math.sqrt(number);

			// 현재 숫자가 완전제곱수인 경우 => 제곱근을 취함
			if (sqrt * sqrt == number) {
				number = sqrt;
				count++;
			}
			// 완전제곱수가 아닌 경우 => 다음 완전제곱수로 만들기 위해 +1 연산을 수행한 후 제곱근을 취함
			else {
				count += (sqrt + 1) * (sqrt + 1) - number; // (다음 완전제곱수 - 현재 숫자) 만큼 +1 연산 수행
				count++; // 이후 제곱근 연산 1회 추가
				number = sqrt + 1;
			}
		}

		return count;
	}

	static void init() throws IOException {
		number = Long.parseLong(br.readLine());
	}
}
