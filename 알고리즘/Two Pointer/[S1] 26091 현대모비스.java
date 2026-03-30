import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_26091_현대모비스 {

	static BufferedReader br;
	static StringTokenizer st;

	static int personCount, powerLimit;
	static int[] power;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getTeamCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		personCount = Integer.parseInt(st.nextToken());
		powerLimit = Integer.parseInt(st.nextToken());

		power = new int[personCount];
		st = new StringTokenizer(br.readLine());
		for (int person = 0; person < personCount; person++) {
			power[person] = Integer.parseInt(st.nextToken());
		}
	}

	static int getTeamCount() {
		Arrays.sort(power);
		
		int count = 0;

		int l = 0;
		int r = personCount - 1;
		while (l < r) {
			if (power[l] + power[r] >= powerLimit) {
				count++;
				l++;
				r--;
			}
			else {
				l++;
			}
		}

		return count;
	}
}
