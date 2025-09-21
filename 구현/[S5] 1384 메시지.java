import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1384_메시지 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int peopleCount;
	static String[] names;
	static char[][] logs;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int group = 1;
		while (init()) {
			sb.append("Group ").append(group++).append('\n');
			findPeople();
			sb.append('\n');
		}

		System.out.print(sb);
	}

	static void findPeople() {
		boolean find = false;
		for (int paperOwner = 0; paperOwner < peopleCount; paperOwner++) {
			for (int step = 1; step <= peopleCount - 1; step++) {
				if (logs[paperOwner][step - 1] == 'N') {
					int author = (paperOwner - step + peopleCount) % peopleCount;
					sb.append(names[author]).append(" was nasty about ").append(names[paperOwner]).append('\n');
					find = true;
				}
			}
		}

		if (!find) {
			sb.append("Nobody was nasty").append('\n');
		}
	}

	static boolean init() throws IOException {
		peopleCount = Integer.parseInt(br.readLine());

		if (peopleCount == 0) {
			return false;
		}

		names = new String[peopleCount];
		logs = new char[peopleCount][peopleCount - 1];

		for (int people = 0; people < peopleCount; people++) {
			st = new StringTokenizer(br.readLine());
			names[people] = st.nextToken();
			for (int other = 0; other < peopleCount - 1; other++) {
				logs[people][other] = st.nextToken().charAt(0);
			}
		}

		return true;
	}
}
