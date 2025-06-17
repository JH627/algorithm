import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13334_철로 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Line implements Comparable<Line> {
		int start, end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Line o) {
			return this.end - o.end;
		}
	}

	static int lineCount, railLength;
	static Line[] lines;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxCount());
	}

	static int getMaxCount() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int max = 0;

		for (int line = 0; line < lineCount; line++) {
			int railStart = lines[line].end - railLength;
			pq.offer(lines[line].start);

			while (!pq.isEmpty() && pq.peek() < railStart) {
				pq.poll();
			}

			max = Math.max(max, pq.size());
		}
		return max;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		lineCount = Integer.parseInt(br.readLine());

		ArrayList<Line> tempLines = new ArrayList<>();
		for (int line = 0; line < lineCount; line++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			tempLines.add(new Line(Math.min(start, end), Math.max(start, end)));
		}
		Collections.sort(tempLines);

		railLength = Integer.parseInt(br.readLine());
		lines = new Line[lineCount];
		int index = 0;
		for (int line = 0; line < lineCount; line++) {
			if (tempLines.get(line).end - tempLines.get(line).start > railLength) {
				continue;
			}
			lines[index++] = new Line(tempLines.get(line).start, tempLines.get(line).end);
		}
		lineCount = index;
	}
}
