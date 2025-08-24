import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3711_학번 {

	static BufferedReader br;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		int[] checked = new int[1000000];
		int stamp = 0;
		for (int testCase = 0; testCase < TC; testCase++) {
			int studentCount = Integer.parseInt(br.readLine());
			int[] nums = new int[studentCount];
			for (int student = 0; student < studentCount; student++) {
				nums[student] = Integer.parseInt(br.readLine());
			}

			for (int mod = studentCount; ; mod++) {
				int tag = ++stamp;
				boolean possible = true;

				for (int student = 0; student < studentCount; student++) {
					int cur = nums[student] % mod;
					if (checked[cur] == tag) {
						possible = false;
						break;
					}
					checked[cur] = tag;
				}

				if (possible) {
					sb.append(mod).append('\n');
					break;
				}
			}
		}
		System.out.print(sb);
	}
}
