import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16200_해커톤 {

	static BufferedReader br;
	static StringTokenizer st;

	static int studentCount;
	static int[] limit;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinTeamCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		studentCount = Integer.parseInt(br.readLine());
		limit = new int[studentCount];
		st = new StringTokenizer(br.readLine());
		for (int student = 0; student < studentCount; student++) {
			limit[student] = Integer.parseInt(st.nextToken());
		}
	}

	static int getMinTeamCount() {
		Arrays.sort(limit);

		int teamCount = 0;
		for (int student = 0; student < studentCount; student++) {
			student += limit[student] - 1;
			teamCount++;
		}

		return teamCount;
	}
}
