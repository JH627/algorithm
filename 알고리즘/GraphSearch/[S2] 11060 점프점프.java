import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11060_점프점프 {

	static int elementSize;
	static int[] elements, distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		elementSize = Integer.parseInt(br.readLine());

		elements = new int[elementSize];
		distance = new int[elementSize];
		Arrays.fill(distance, Integer.MAX_VALUE);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementSize; elementIndex++) {
			elements[elementIndex] = Integer.parseInt(st.nextToken());
		}

		if (elementSize == 1) {
			System.out.print(0);
			return;
		}

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{0, 0});
		distance[0] = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int nowElementIndex = now[0];
			int nowDistance = now[1];

			if (nowDistance > distance[nowElementIndex]) {
				continue;
			}

			if (elements[nowElementIndex] == 0) {
				continue;
			}

			for (int delta = 1; delta <= elements[nowElementIndex] ; delta++) {
				if (nowElementIndex + delta >= elementSize) {
					break;
				}
				if (distance[nowElementIndex + delta] > nowDistance + 1) {
					distance[nowElementIndex + delta] = nowDistance + 1;
					if (nowElementIndex + delta == elementSize - 1) {
						System.out.print(distance[nowElementIndex + delta]);
						return;
					}
					q.add(new int[]{nowElementIndex + delta, nowDistance + 1});
				}
			}
		}

		System.out.print(-1);
	}
}
