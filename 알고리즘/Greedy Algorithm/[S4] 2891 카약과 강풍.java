import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2891_카약과강풍 {

	static BufferedReader br;
	static StringTokenizer st;

	static int teamCount, disableTeamCount, chanceTeamCount;
	static boolean[] disable;
	static ArrayList<Integer> chanceTeamNumber;

	public static void main(String[] args) throws IOException {
		init();

		System.out.print(getTeamCount());
	}

	static int getTeamCount() {
		int count = disableTeamCount;
		for (Integer teamNumber : chanceTeamNumber) {
			if (teamNumber > 0 && disable[teamNumber - 1]) {
				disable[teamNumber - 1] = false;
				count--;
				continue;
			}
			if (teamNumber < teamCount - 1 && disable[teamNumber + 1]) {
				disable[teamNumber + 1] = false;
				count--;
			}
		}
		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		teamCount = Integer.parseInt(st.nextToken());
		disableTeamCount = Integer.parseInt(st.nextToken());
		chanceTeamCount = Integer.parseInt(st.nextToken());

		disable = new boolean[teamCount];

		st = new StringTokenizer(br.readLine());
		for (int team = 0; team < disableTeamCount; team++) {
			disable[Integer.parseInt(st.nextToken()) - 1] = true;
		}

		st = new StringTokenizer(br.readLine());
		chanceTeamNumber = new ArrayList<>();
		for (int team = 0; team < chanceTeamCount; team++) {
			int num = Integer.parseInt(st.nextToken()) - 1;
			if (disable[num]) {
				disable[num] = false;
				disableTeamCount--;
				continue;
			}
			chanceTeamNumber.add(num);
		}

		Collections.sort(chanceTeamNumber);
	}
}
