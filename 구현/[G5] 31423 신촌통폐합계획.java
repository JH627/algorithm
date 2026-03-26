import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_31423_신촌통폐합계획 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int wordCount;
	static String[] words;
	static int[] head, tail, next;
	static boolean[] removed;

	public static void main(String[] args) throws Exception {
		init();
		findStatus();
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		wordCount = Integer.parseInt(br.readLine());

		words = new String[wordCount + 1];
		head = new int[wordCount + 1];
		tail = new int[wordCount + 1];
		next = new int[wordCount + 1];
		removed = new boolean[wordCount + 1];

		for (int word = 1; word <= wordCount; word++) {
			words[word] = br.readLine();
			head[word] = word;
			tail[word] = word;
		}

		for (int query = 0; query < wordCount - 1; query++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());

			next[tail[i]] = head[j];
			tail[i] = tail[j];
			removed[j] = true;
		}
	}

	static void findStatus() {
		int start = -1;

		for (int word = 1; word <= wordCount; word++) {
			if (!removed[word]) {
				start = head[word];
				break;
			}
		}

		int cur = start;
		while (cur != 0) {
			sb.append(words[cur]);
			cur = next[cur];
		}
	}
}
