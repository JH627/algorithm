import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static PriorityQueue<Long> minHeap, maxHeap;
	static PriorityQueue<Long> lazyMinHeap, lazyMaxHeap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();

			int K = Integer.parseInt(br.readLine());
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());

				char op = st.nextToken().charAt(0);
				if (op == 'I') {
					long num = Long.parseLong(st.nextToken());
					minHeap.add(num);
					maxHeap.add(num);
				}
				else {
					int workHeap = Integer.parseInt(st.nextToken());
					removeElementFromHeap(workHeap != -1, false);
				}
			}

			if (lazyMaxHeap.size() == maxHeap.size()) {
				sb.append("EMPTY").append("\n");
			}
			else {
				sb.append(removeElementFromHeap(true, true)).append(" ");
				sb.append(removeElementFromHeap(false, true)).append("\n");
			}
		}

		System.out.print(sb);
	}

	static void init() {
		minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

		lazyMinHeap = new PriorityQueue<>();
		lazyMaxHeap = new PriorityQueue<>(Comparator.reverseOrder());
	}

	static long removeElementFromHeap(boolean isMaxHeap, boolean peek) {
		PriorityQueue<Long> heap = (isMaxHeap) ? maxHeap : minHeap;
		PriorityQueue<Long> lazyHeap = (isMaxHeap) ? lazyMaxHeap : lazyMinHeap;
		PriorityQueue<Long> oppositeLazyHeap = (isMaxHeap) ? lazyMinHeap : lazyMaxHeap;

		while (!heap.isEmpty() && !lazyHeap.isEmpty()) {
			if (heap.peek().equals(lazyHeap.peek())) {
				heap.poll();
				lazyHeap.poll();
			}
			else {
				break;
			}
		}

		if (heap.isEmpty()) {
			return 0;
		}

		if (peek) {
			return heap.peek();
		}

		long removed = heap.poll();
		oppositeLazyHeap.add(removed);

		return removed;
	}
}
