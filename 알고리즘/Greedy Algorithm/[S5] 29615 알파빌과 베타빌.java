import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_29615_알파빌과베타빌 {

	static BufferedReader br;
	static StringTokenizer st;

	static int userCount, friendCount;
	static int[] line;
	static boolean[] isFriend;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinSwapCount());
	}

	static int findMinSwapCount() {
		int count = 0;

		for (int user = 0; user < friendCount; user++) {
			if (!isFriend[line[user]]) {
				count++;
			}
		}

		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		userCount = Integer.parseInt(st.nextToken());
		friendCount = Integer.parseInt(st.nextToken());

		line = new int[userCount];
		isFriend = new boolean[userCount];

		st = new StringTokenizer(br.readLine());
		for (int user = 0; user < userCount; user++) {
			line[user] = Integer.parseInt(st.nextToken()) - 1;
		}

		st = new StringTokenizer(br.readLine());
		for (int friend = 0; friend < friendCount; friend++) {
			isFriend[Integer.parseInt(st.nextToken()) - 1] = true;
		}
	}
}
