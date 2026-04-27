import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1306_달려라홍준 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Element {
		int number, index;

		public Element(int number, int index) {
			this.number = number;
			this.index = index;
		}
	}

	static int elementCount, viewLimit;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		init();
		findMaxElement();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		viewLimit = Integer.parseInt(st.nextToken());

		elements = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}
	}

	static void findMaxElement() {
		sb = new StringBuilder();

		int windowSize = 2 * viewLimit - 1;
		Deque<Element> numbers = new LinkedList<>();
		for (int index = 0; index < elementCount; index++) {
			while (!numbers.isEmpty() && numbers.peekLast().number <= elements[index]) {
				numbers.pollLast();
			}

			numbers.addLast(new Element(elements[index], index));

			int left = index - windowSize + 1;
			while (!numbers.isEmpty() && numbers.peekFirst().index < left) {
				numbers.pollFirst();
			}

			if (index >= windowSize - 1) {
				sb.append(numbers.peekFirst().number).append(' ');
			}
		}

		System.out.print(sb);
	}
}
