import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1495_기타리스트 {

	static BufferedReader br;
	static StringTokenizer st;

	static int musicCount, startVolume, limitVolume;
	static int[] volumes;
	static boolean[][] dp;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxVolume());
	}

	static int findMaxVolume() {
		int max = -1;
		for (int volume = limitVolume; volume >= 0; volume--) {
			if (dp[musicCount][volume]) {
				max = volume;
				break;
			}
		}
		return max;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		musicCount = Integer.parseInt(st.nextToken());
		startVolume = Integer.parseInt(st.nextToken());
		limitVolume = Integer.parseInt(st.nextToken());

		volumes = new int[musicCount + 1];
		dp = new boolean[musicCount + 1][limitVolume + 1];
		dp[0][startVolume] = true;
		st = new StringTokenizer(br.readLine());
		for (int music = 1; music < musicCount + 1; music++) {
			volumes[music] = Integer.parseInt(st.nextToken());
			for (int volume = 0; volume < limitVolume + 1; volume++) {
				if (dp[music - 1][volume]) {
					int plus = volume + volumes[music];
					int minus = volume - volumes[music];

					if (plus <= limitVolume) {
						dp[music][plus] = true;
					}
					if (minus >= 0) {
						dp[music][minus] = true;
					}
				}
			}
		}
	}
}
