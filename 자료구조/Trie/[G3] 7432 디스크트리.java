import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_7432_디스크트리 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Trie {
		String word;
		TreeMap<String, Trie> children;

		public Trie(String word) {
			this.word = word;
			children = new TreeMap<>();
		}
	}

	static int wordCount;
	static Trie root;

	public static void main(String[] args) throws IOException {
		init();
		printTrie();
		System.out.print(sb);
	}

	static void printTrie() {
		for (Trie next : root.children.values()) {
			find(next, 0);
		}
	}

	static void find(Trie now, int depth) {
		for (int d = 0; d < depth; d++) {
			sb.append(" ");
		}
		sb.append(now.word).append("\n");

		for (Trie next : now.children.values()) {
			find(next, depth + 1);
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		wordCount = Integer.parseInt(br.readLine());

		root = new Trie(null);
		for (int word = 0; word < wordCount; word++) {
			st = new StringTokenizer(br.readLine(), "\\");
			Trie cursor = root;
			while (st.hasMoreTokens()) {
				String now = st.nextToken();
				if (cursor.children.containsKey(now)) {
					cursor = cursor.children.get(now);
				}
				else {
					cursor.children.put(now, new Trie(now));
					cursor = cursor.children.get(now);
				}
			}
		}
	}
}
