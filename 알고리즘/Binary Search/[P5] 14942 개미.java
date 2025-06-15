import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14942_개미 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Edge {
		int end, cost;

		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
	}

	static int roomCount, antCount;
	static int[] antPower, nearRoom;
	static ArrayList<ArrayList<Edge>> edges;
	static ArrayList<Edge> distanceList;

	public static void main(String[] args) throws IOException {
		init();
		findNearestRoom(1);
		printAnswer();
	}

	static void findNearestRoom(int roomNumber) {
		nearRoom[roomNumber] = getNearestRoom(roomNumber);

		for (Edge edge : edges.get(roomNumber)) {
			if (nearRoom[edge.end] != 0) {
				continue;
			}
			distanceList.add(new Edge(edge.end, distanceList.get(distanceList.size() - 1).cost + edge.cost));
			findNearestRoom(edge.end);
			distanceList.remove(distanceList.size() - 1);
		}
	}

	static int getNearestRoom(int roomNumber) {
		int power = distanceList.get(distanceList.size() - 1).cost - antPower[roomNumber];

		if (power <= 0) {
			return 1;
		}

		int l = 0, r = distanceList.size();
		while (l < r) {
			int m = (l + r) / 2;
			if (distanceList.get(m).cost < power) {
				l = m + 1;
			}
			else {
				r = m;
			}
		}

		return distanceList.get(l).end;
	}

	static void printAnswer() {
		sb = new StringBuilder();
		for (int room = 1; room < roomCount + 1; room++) {
			sb.append(nearRoom[room]).append("\n");
		}
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		roomCount = antCount = Integer.parseInt(br.readLine());
		antPower = new int[antCount + 1];
		for (int ant = 1; ant < antCount + 1; ant++) {
			antPower[ant] = Integer.parseInt(br.readLine());
		}

		edges = new ArrayList<>();
		for (int room = 0; room < roomCount + 1; room++) {
			edges.add(new ArrayList<>());
		}

		for (int edge = 0; edge < roomCount - 1; edge++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.get(start).add(new Edge(end, cost));
			edges.get(end).add(new Edge(start, cost));
		}

		nearRoom = new int[roomCount + 1];

		distanceList = new ArrayList<>();
		distanceList.add(new Edge(1, 0));
	}
}
