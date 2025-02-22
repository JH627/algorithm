import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 비트 연산 연습
 *
 * add: OR 연산 ( | ) 을 통하여 n번째 비트를 켠다
 * 예시: 01001 | 00010 = 01011 (두 번째 비트를 켜는 경우)
 *
 * remove: AND 연산 ( & ) 과 NOT 연산 ( ~ ) 을 사용하여 n번째 비트를 끈다
 * 예시: 01011 & ~00010 = 01001 (두 번째 비트를 끄는 경우)
 *
 * check: AND 연산 ( & ) 을 사용하여 n번째 비트가 켜져 있는지 확인한다
 * 예시: (01011 & 00010) != 0 -> 1 (두 번째 비트가 존재함)
 *
 * toggle: XOR 연산 ( ^ ) 을 사용하여 n번째 비트를 반전시킨다
 * 예시: 01011 ^ 00010 = 01001 (두 번째 비트를 끄거나 켜는 경우)
 *
 * all: 20번째 비트까지 1로 만들기 위해서는 (1 << 20) - 1 하면 된다.
 *
 * empty: 모든 비트를 0으로 설정 => 값이 0
 */
public class BOJ_11723_집합 {

	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static final int ELEMENT_COUNT = 20; // 최대 집합의 크기
	static int queryCount; // 연산의 수
	static int currentGroupStatus; // 현재 집합의 상태, n이 포함된 포함된경우 n번째 비트가 1이 된다;

	public static void main(String[] args) throws IOException {
		init();

		updateQueries();

		System.out.print(sb);
	}

	static void updateQueries() throws IOException {
		for (int queryIdx = 0; queryIdx < queryCount; queryIdx++) {
			st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
				// S에 x를 추가
				case "add":
					currentGroupStatus |= (1 << Integer.parseInt(st.nextToken()) - 1);
					break;
				// S에서 x를 제거
				case "remove":
					currentGroupStatus &= ~(1 << Integer.parseInt(st.nextToken()) - 1);
					break;
				// S에 x가 있으면 1, 없으면 0 출력
				case "check":
					sb.append(((currentGroupStatus & (1 << Integer.parseInt(st.nextToken()) - 1)) == 0) ? "0\n" : "1\n");
					break;
				// S에 x가 있으면 x제거, 없으면 추가
				case "toggle":
					currentGroupStatus ^= (1 << Integer.parseInt(st.nextToken()) - 1);
					break;
				// 원소를 모두 들어있는 상태로 변경
				case "all":
					currentGroupStatus = (1 << ELEMENT_COUNT) - 1;
					break;
				// S를 공집합으로 변경
				case "empty":
					currentGroupStatus = 0;
					break;
			}
		}
	}

	static void init() throws IOException {
		queryCount = Integer.parseInt(br.readLine());

		currentGroupStatus = 0; // 초기값
	}
}
