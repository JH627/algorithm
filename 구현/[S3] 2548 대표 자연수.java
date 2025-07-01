import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2548_대표자연수 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(elements);
		System.out.print(elements[(elementCount - 1) / 2]);
	}
}
