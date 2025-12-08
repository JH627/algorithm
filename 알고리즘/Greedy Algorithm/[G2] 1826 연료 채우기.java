import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1826_연료채우기 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Node implements Comparable<Node> {
		int position, fuel;

		public Node(int position, int fuel) {
			this.position = position;
			this.fuel = fuel;
		}

		@Override
		public int compareTo(Node o) {
			return this.position - o.position;
		}
	}

	static int nodeCount;
	static int endPoint, remainFuel;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinCount());
	}

	static int findMinCount() {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

		int nodeIndex = 0;
		int count = 0;
		int fuel = remainFuel;

		while (fuel < endPoint) {
			while (nodeIndex < nodeCount && nodes[nodeIndex].position <= fuel) {
				pq.add(nodes[nodeIndex].fuel);
				nodeIndex++;
			}

			if (pq.isEmpty()) {
				return -1;
			}

			fuel += pq.poll();
			count++;
		}

		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		nodeCount = Integer.parseInt(br.readLine());
		nodes = new Node[nodeCount];

		for (int node = 0; node < nodeCount; node++) {
			st = new StringTokenizer(br.readLine());
			int position = Integer.parseInt(st.nextToken());
			int fuel = Integer.parseInt(st.nextToken());
			nodes[node] = new Node(position, fuel);
		}

		Arrays.sort(nodes);

		st = new StringTokenizer(br.readLine());
		endPoint = Integer.parseInt(st.nextToken());
		remainFuel = Integer.parseInt(st.nextToken());
	}
}
