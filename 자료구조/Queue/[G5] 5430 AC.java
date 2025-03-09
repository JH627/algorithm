import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_5430_AC {

	static BufferedReader br;
	static StringBuilder sb;

	static String query;
	static int elementCount;
	static Deque<Integer> numbers;
	static boolean reverse;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();

			boolean error = useQuery();

			if (error) {
				sb.append("error\n");
				continue;
			}

			printResult();
		}

		System.out.print(sb);
	}

	static boolean useQuery() {
		reverse = false;
		
		int queryLength = query.length();
		for (int index = 0; index < queryLength; index++) {
			char queryChar = query.charAt(index);
			if (queryChar == 'R') {
				reverse = !reverse;
			}
			else {
				if (numbers.isEmpty()) {
					return true;
				}
				if (reverse) {
					numbers.pollLast();
				}
				else {
					numbers.pollFirst();
				}
			}
		}

		return false;
	}

	static void printResult() {
		sb.append("[");

		int size = numbers.size();
		for (int index = 0; index < size - 1; index++) {
			sb.append((reverse) ? numbers.pollLast() : numbers.pollFirst()).append(",");
		}
		if (size > 0) {
			sb.append(numbers.pop());
		}

		sb.append("]\n");
	}

	static void init() throws IOException {
		query = br.readLine();
		elementCount = Integer.parseInt(br.readLine());

		numbers = new ArrayDeque<>();
		String element = br.readLine();
		StringTokenizer st = new StringTokenizer(element.substring(1, element.length() - 1), ",");
		while (st.hasMoreTokens()) {
			numbers.addLast(Integer.parseInt(st.nextToken()));
		}
	}
}
