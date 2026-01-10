import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_11003_최솟값찾기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Num {
		int number, index;

		public Num(int number, int index) {
			this.number = number;
			this.index = index;
		}
	}

	static int elementCount, lenLimit;
	static Deque<Num> queue;

	public static void main(String[] args) throws IOException {
		init();
		findMinNumber();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		lenLimit = Integer.parseInt(st.nextToken());

		queue = new ArrayDeque<>();
	}

	static void findMinNumber() throws IOException {
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < elementCount; index++) {
			int number = Integer.parseInt(st.nextToken());

			while (!queue.isEmpty() && queue.peekLast().number >= number) {
				queue.removeLast();
			}

			queue.addLast(new Num(number, index));

			while (queue.peekFirst().index < index - lenLimit + 1) {
				queue.removeFirst();
			}

			sb.append(queue.peekFirst().number).append(" ");
		}

		System.out.print(sb);
	}
}
