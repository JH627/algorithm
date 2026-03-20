import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_28277_뭉쳐야산다 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int groupCount, queryCount;
	static ArrayList<TreeSet<Integer>> groups;

	public static void main(String[] args) throws IOException {
		init();
		useQuery();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		groupCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());

		groups = new ArrayList<>(groupCount);
		for (int group = 0; group < groupCount; group++) {
			groups.add(new TreeSet<>());
		}

		for (int group = 0; group < groupCount; group++) {
			st = new StringTokenizer(br.readLine());
			int groupSize = Integer.parseInt(st.nextToken());
			for (int memeber = 0; memeber < groupSize; memeber++) {
				int number = Integer.parseInt(st.nextToken());
				groups.get(group).add(number);
			}
		}
	}

	static void useQuery() throws IOException {
		sb = new StringBuilder();

		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());

			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken()) - 1;
			switch (command) {
				case 1:
					int b = Integer.parseInt(st.nextToken()) - 1;
					mix(a, b);
					break;
				case 2:
					sb.append(groups.get(a).size()).append("\n");
					break;
			}
		}

		System.out.print(sb);
	}

	static void mix(int a, int b) {
		int aGroupSize = groups.get(a).size();
		int bGroupSize = groups.get(b).size();

		if (aGroupSize >= bGroupSize) {
			groups.get(a).addAll(groups.get(b));
			groups.get(b).clear();
		}
		else {
			groups.get(b).addAll(groups.get(a));
			groups.get(a).clear();
			Collections.swap(groups, a, b);
		}
	}
}
