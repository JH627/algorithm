import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class BOJ_1539_이진검색트리 {

	static BufferedReader br;

	static int elementCount;
	static TreeMap<Integer, Integer> bst;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		bst = new TreeMap<>();
		elementCount = Integer.parseInt(br.readLine());
		long sum = 0;
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			int num = Integer.parseInt(br.readLine());
			Map.Entry<Integer, Integer> left = bst.lowerEntry(num);
			Map.Entry<Integer, Integer> right = bst.higherEntry(num);

			int leftValue = (left == null) ? 0 : left.getValue();
			int rightValue = (right == null) ? 0 : right.getValue();

			int prevMaxHeight = Math.max(leftValue, rightValue);
			bst.put(num, prevMaxHeight + 1);
			sum += prevMaxHeight + 1;
		}

		System.out.print(sum);
	}
}
