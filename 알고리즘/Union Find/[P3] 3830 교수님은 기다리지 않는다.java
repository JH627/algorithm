import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3830_교수님은기다리지않는다 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final String UNKNOWN = "UNKNOWN";

	static class Info {
		int parentNumber;
		long diff;

		public Info(int parentNumber, long diff) {
			this.parentNumber = parentNumber;
			this.diff = diff;
		}
	}

	static int sampleCount, queryCount;
	static Info[] infos;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while (init()) {
			useQuery();
		}
		System.out.print(sb);
	}

	static void useQuery() throws IOException {
		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken().charAt(0)) {
				case '!':
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					long diff = Long.parseLong(st.nextToken());
					union(a, b, diff);
					break;
				case '?':
					int findA = Integer.parseInt(st.nextToken());
					int findB = Integer.parseInt(st.nextToken());
					sb.append(getDiff(findA, findB)).append("\n");
					break;
			}
		}
	}

	static void union(int a, int b, long diff) {
		Info rootA = find(a);
		Info rootB = find(b);

		if (rootA.parentNumber == rootB.parentNumber) {
			return;
		}

		infos[rootA.parentNumber].parentNumber = rootB.parentNumber;
		infos[rootA.parentNumber].diff = rootB.diff - rootA.diff + diff;
	}


	static Info find(int x) {
		if (infos[x].parentNumber == x) {
			return new Info(x, 0);
		}

		Info parentInfo = find(infos[x].parentNumber);
		return infos[x] = new Info(parentInfo.parentNumber, parentInfo.diff + infos[x].diff);
	}

	static String getDiff(int a, int b) {
		Info rootA = find(a);
		Info rootB = find(b);

		if (rootA.parentNumber != rootB.parentNumber) {
			return UNKNOWN;
		}

		return String.valueOf(rootA.diff - rootB.diff);
	}

	static boolean init() throws IOException {
		st = new StringTokenizer(br.readLine());
		sampleCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());

		infos = new Info[sampleCount + 1];
		for (int sample = 1; sample <= sampleCount; sample++) {
			infos[sample] = new Info(sample, 0);
		}

		return !(sampleCount == 0 && queryCount == 0);
	}
}
