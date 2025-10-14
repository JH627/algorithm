import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21276_계보복원가호석 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int personCount, infoCount;
	static String[] personNames;
	static Map<String, Integer> nameToIndex;

	static ArrayList<Integer>[] graph;
	static ArrayList<Integer>[] children;
	static int[] degree;
	static int[] parent;
	static ArrayList<Integer> roots;

	public static void main(String[] args) throws IOException {
		init();
		restoreGenealogy();
		printAnswer();
	}

	static void restoreGenealogy() {
		roots = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();

		for (int person = 0; person < personCount; person++) {
			if (degree[person] == 0) {
				roots.add(person);
				queue.add(person);
			}
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int next : graph[current]) {
				degree[next]--;
				if (degree[next] == 0) {
					parent[next] = current;
					children[current].add(next);
					queue.add(next);
				}
			}
		}
	}

	static void printAnswer() {
		sb = new StringBuilder();

		ArrayList<String> rootNames = new ArrayList<>();
		for (int index : roots) {
			rootNames.add(personNames[index]);
		}
		Collections.sort(rootNames);

		sb.append(rootNames.size()).append("\n");
		for (String rootName : rootNames) {
			sb.append(rootName).append(" ");
		}
		sb.append("\n");

		String[] namesSorted = personNames.clone();
		Arrays.sort(namesSorted);

		for (String name : namesSorted) {
			int u = nameToIndex.get(name);

			ArrayList<String> childNames = new ArrayList<>();
			for (int v : children[u]) {
				childNames.add(personNames[v]);
			}
			Collections.sort(childNames);

			sb.append(name).append(" ").append(childNames.size());
			for (String cn : childNames) {
				sb.append(" ").append(cn);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		personCount = Integer.parseInt(br.readLine());

		personNames = new String[personCount];
		nameToIndex = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		for (int person = 0; person < personCount; person++) {
			String name = st.nextToken();
			personNames[person] = name;
			nameToIndex.put(name, person);
		}

		infoCount = Integer.parseInt(br.readLine());

		graph = new ArrayList[personCount];
		children = new ArrayList[personCount];
		for (int person = 0; person < personCount; person++) {
			graph[person] = new ArrayList<>();
			children[person] = new ArrayList<>();
		}

		degree = new int[personCount];
		parent = new int[personCount];
		Arrays.fill(parent, -1);

		for (int info = 0; info < infoCount; info++) {
			st = new StringTokenizer(br.readLine());
			String descendantName = st.nextToken();
			String ancestorName = st.nextToken();
			int descendant = nameToIndex.get(descendantName);
			int ancestor = nameToIndex.get(ancestorName);

			graph[ancestor].add(descendant);
			degree[descendant]++;
		}
	}
}
