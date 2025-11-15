import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_28445_알록달록앵무새 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Pair implements Comparable<Pair> {
		String body, tail;

		public Pair(String body, String tail) {
			this.body = body;
			this.tail = tail;
		}

		public int compareTo(Pair o) {
			int compare = body.compareTo(o.body);
			if (compare == 0) {
				return tail.compareTo(o.tail);
			}
			return compare;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Pair pair = (Pair)o;
			return Objects.equals(body, pair.body) && Objects.equals(tail, pair.tail);
		}

		@Override
		public int hashCode() {
			return Objects.hash(body, tail);
		}
	}

	static ArrayList<String> colors;

	public static void main(String[] args) throws IOException {
		init();
		findPair();
	}

	static void findPair() {
		sb = new StringBuilder();

		Set<Pair> set = new HashSet<>();
		for (String body : colors) {
			for (String tail : colors) {
				set.add(new Pair(body, tail));
			}
		}

		ArrayList<Pair> pairs = new ArrayList<>(set);
		Collections.sort(pairs);

		for (Pair pair : pairs) {
			sb.append(pair.body).append(" ").append(pair.tail).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		colors = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			colors.add(st.nextToken());
			colors.add(st.nextToken());
		}
	}

}
