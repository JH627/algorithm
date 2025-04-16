import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 동전을 만드는 경우의 수
 * 
 * 현재 동전을 쓰지 않는 경우 + 현재 동전을 쓰는 경우
 * 
 * 1. 현재 동전을 쓰지 않는 경우 => 같은 금액 이전 경우의 수
 * 2. 현재 동전을 쓰는 경우 => (현재 확인하고자 하는 금액 - 동전의 가치)금액 이전 경우의 수
 * 
 */
public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int coinCount, moneyCount;
	static int[] coinValue;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			
			int caseCount = findCase();
			
			sb.append(caseCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int findCase() {
		int[] count = new int[moneyCount + 1];
		
		count[0] = 1;
		for (int coinIndex = 0; coinIndex < coinCount; coinIndex++) {
			for (int money = coinValue[coinIndex]; money <= moneyCount; money++) {
				count[money] += count[money - coinValue[coinIndex]];
			}
		}

		return count[moneyCount];
	}

	static void init() throws Exception {
		coinCount = Integer.parseInt(br.readLine());
		
		coinValue = new int[coinCount];
		st = new StringTokenizer(br.readLine());
		for (int coinIndex = 0; coinIndex < coinCount; coinIndex++) {
			coinValue[coinIndex] = Integer.parseInt(st.nextToken());
		}
		
		moneyCount = Integer.parseInt(br.readLine());
	}
}
