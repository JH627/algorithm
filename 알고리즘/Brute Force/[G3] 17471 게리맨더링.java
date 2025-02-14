import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, allVisited;
	static int[] population;
	static ArrayList<ArrayList<Integer>> graph;
	static int populationSize;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		init();

		comb(0, 0, 0, 0);
		
		System.out.print(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void comb(int index, int nowPopulation, int visitedStatus, int size) {
		if (visitedStatus == allVisited) {
			return;
		}
		
		if (visitedStatus != 0) {
			int diff = Math.abs((populationSize - nowPopulation) - nowPopulation);
			if (min > diff) {
				int reverseVisitedStatus = ((1 << N) - 1) ^ visitedStatus;
				if (checkIsConnected(visitedStatus, N - size) && checkIsConnected(reverseVisitedStatus, size)) {
					min = diff;
				}
			}
		}
		
		if (populationSize / 2 <= nowPopulation) {
			return;
		}
		
		if (index == N) {
			return;
		}
		
		comb(index + 1, nowPopulation + population[index], visitedStatus | (1 << index), size + 1);
		comb(index + 1, nowPopulation, visitedStatus, size);	
	}
	
	static boolean checkIsConnected(int visitedStatus, int size) { 
		Queue<Integer> toVisit = new LinkedList<>();
		
		for (int n = 0; n < N; n++) {
			if ((visitedStatus & (1 << n)) == 0) {
				toVisit.add(n);
				visitedStatus |= (1 << n);
				break;
			}
		}
		
		int visitedNodes = 0;
		while (!toVisit.isEmpty()) {
			int now = toVisit.poll();
			
			visitedNodes++;
			for (int next : graph.get(now)) {
				if ((visitedStatus & (1 << next)) == 0) {
					toVisit.add(next);
					visitedStatus |= (1 << next);
				}
			}
		}
		
		return visitedNodes == size;
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		population = new int[N];
		allVisited = (1 << N) - 1;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			population[n] = Integer.parseInt(st.nextToken());
			populationSize += population[n];
		}

		graph = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			graph.add(new ArrayList<>());
		}

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int nearCityCount = Integer.parseInt(st.nextToken());
			for (int count = 0; count < nearCityCount; count++) {
				graph.get(n).add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
	}
}

