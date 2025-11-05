import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_21967_세워라반석위에 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxLength());
	}

	static int findMaxLength() {
		int maxLength = 0;

		Deque<Integer> maxDeque = new ArrayDeque<>();
		Deque<Integer> minDeque = new ArrayDeque<>();

		int left = 0;
		for (int right = 0; right < elementCount; right++) {
			int current = elements[right];

			while (!maxDeque.isEmpty() && elements[maxDeque.peekLast()] < current) {
				maxDeque.pollLast();
			}
			maxDeque.addLast(right);

			while (!minDeque.isEmpty() && elements[minDeque.peekLast()] > current) {
				minDeque.pollLast();
			}
			minDeque.addLast(right);

			while (elements[maxDeque.peekFirst()] - elements[minDeque.peekFirst()] > 2) {
				left++;

				if (maxDeque.peekFirst() < left) {
					maxDeque.pollFirst();
				}
				if (minDeque.peekFirst() < left) {
					minDeque.pollFirst();
				}
			}

			maxLength = Math.max(maxLength, right - left + 1);
		}

		return maxLength;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount];

		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}
	}
}
