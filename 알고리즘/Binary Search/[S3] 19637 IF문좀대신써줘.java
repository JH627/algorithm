import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19637_IF문좀대신써줘 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Tag {
		String name;
		int power;

		public Tag(String name, int power) {
			this.name = name;
			this.power = power;
		}
	}

	static int tagCount, characterCount;
	static Tag[] tags;

	public static void main(String[] args) throws IOException {
		init();
		findTag();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		tagCount = Integer.parseInt(st.nextToken());
		characterCount = Integer.parseInt(st.nextToken());

		tags = new Tag[tagCount];
		for (int tag = 0; tag < tagCount; tag++) {
			st = new StringTokenizer(br.readLine());
			tags[tag] = new Tag(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
	}

	static void findTag() throws IOException {
		sb = new StringBuilder();

		for (int character = 0; character < characterCount; character++) {
			int power = Integer.parseInt(br.readLine());
			sb.append(getTag(power)).append("\n");
		}

		System.out.print(sb);
	}

	static String getTag(int power) {
		int l = 0;
		int r = tagCount - 1;

		while (l <= r) {
			int m = (l + r) / 2;
			if (tags[m].power < power) {
				l = m + 1;
			}
			else {
				r = m - 1;
			}
		}

		return tags[l].name;
	}
}
