import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_23757_아이들과선물상자 {

	static BufferedReader br;
	static StringTokenizer st;

	static int giftCount, childCount;
	static PriorityQueue<Integer> gifts;
	static int[] want;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(check());
	}

	static int check() {
		for (int child = 0; child < childCount; child++) {
			if (!gifts.isEmpty() && gifts.peek() >= want[child]) {
				Integer polled = gifts.poll();
				gifts.add(polled - want[child]);
			}
			else {
				return 0;
			}
		}
		return 1;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		giftCount = Integer.parseInt(st.nextToken());
		childCount = Integer.parseInt(st.nextToken());

		gifts = new PriorityQueue<>(Comparator.reverseOrder());
		st = new StringTokenizer(br.readLine());
		for (int gift = 0; gift < giftCount; gift++) {
			gifts.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		want = new int[childCount];
		for (int child = 0; child < childCount; child++) {
			want[child] = Integer.parseInt(st.nextToken());
		}
	}
}
