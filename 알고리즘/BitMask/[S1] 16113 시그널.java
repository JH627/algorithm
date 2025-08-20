import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_16113_시그널 {

	static BufferedReader br;
	static StringBuilder sb;

	static final int[] nums = {31599, 18724, 29671, 29647, 23497, 31183, 31215, 29257, 31727, 31695};

	static HashMap<Integer, Integer> map;
	static int length;
	static String signal;

	public static void main(String[] args) throws IOException {
		init();
		findNumber();
		System.out.print(sb);
	}

	static void findNumber() {
		for (int index = 0; index < length / 5; index++) {
			if (signal.charAt(index) == '#'){
				if (index >= length / 5 - 2) {
					sb.append('1');
					continue;
				}
				int num = 0;
				for (int row = 0; row < 5; row++) {
					for (int col = 0; col < 3; col++) {
						if (signal.charAt(index + row * (length / 5) + col) == '#') {
							num = (num << 1) + 1;
						}
						else {
							num = num << 1;
						}
					}
				}
				if (!map.containsKey(num)) {
					sb.append('1');
				}
				else {
					sb.append(map.get(num));
					index += 2;
				}
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		length = Integer.parseInt(br.readLine());
		signal = br.readLine();

		map = new HashMap<>();
		for (int num = 0; num < 10; num++) {
			map.put(nums[num], num);
		}
	}

}
