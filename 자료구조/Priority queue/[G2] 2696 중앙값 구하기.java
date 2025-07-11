import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2696_중앙값구하기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			getMidValue();
		}
		System.out.print(sb);
	}
	
	static void getMidValue() throws Exception {
		PriorityQueue<Integer> left = new PriorityQueue<Integer>(Comparator.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<Integer>();
		
		int elementCount = Integer.parseInt(br.readLine());
		sb.append((elementCount / 2) + 1).append("\n");
		
		int rowSize = (elementCount % 10 == 0) ? elementCount / 10 : elementCount / 10 + 1;
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < Math.min(10, elementCount - (10 * row)); col++) {
				// 넣고
				int number = Integer.parseInt(st.nextToken());
				if (left.isEmpty() || number <= left.peek()) {
					left.add(number);
				}
				else {
					right.add(number);
				}	
				
				// 홀수번째면 중간값 출력
				if ((col % 2) == 0) {
					if (left.size() < right.size()) {
						while (left.size() != right.size() + 1) {
							left.add(right.poll());
						}
					}
					else {
						while (left.size() > right.size() + 1) {
							right.add(left.poll());
						}
					}
					sb.append(left.peek()).append(" ");
				}
			}
			if (row % 2 == 1 && row != rowSize - 1) {
				sb.append("\n");
			}
		}
		sb.append("\n");
	}
	
}
