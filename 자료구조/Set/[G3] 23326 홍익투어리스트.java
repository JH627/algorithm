import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_23326_홍익투어리스트 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int elementCount, queryCount;
	static TreeSet<Integer> popular, unpopular;

	public static void main(String[] args) throws IOException {
		init();

		useQuery();

		System.out.print(sb);
	}

	static void useQuery() throws IOException {
		int cursor = 0;
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int operation = Integer.parseInt(st.nextToken());

			switch (operation) {
				case 1:
					int targetRegion = Integer.parseInt(st.nextToken()) - 1;
					if (popular.contains(targetRegion)) {
						popular.remove(targetRegion);
						unpopular.add(targetRegion);
					}
					else {
						popular.add(targetRegion);
						unpopular.remove(targetRegion);
					}
					break;
				case 2:
					int moveRange = Integer.parseInt(st.nextToken());
					cursor = (cursor + moveRange) % elementCount;
					break;
				case 3:
					if (popular.isEmpty()) {
						sb.append("-1").append('\n');
						break;
					}
					int distance = 0;
					Integer ceil = popular.ceiling(cursor);
					// 한바퀴 돌아서 1로 가야하는 경우
					if (ceil == null) {
						distance = (elementCount - cursor) + popular.first();
					}
					// 아닌 경우
					else {
						distance = ceil - cursor;
					}
					sb.append(distance).append('\n');
					break;
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());

		popular = new TreeSet<>();
		unpopular = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			int popularity = Integer.parseInt(st.nextToken());
			if (popularity == 0) {
				unpopular.add(elementIndex);
			}
			else {
				popular.add(elementIndex);
			}
		}
	}
}
