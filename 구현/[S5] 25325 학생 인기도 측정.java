import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class BOJ_25325_학생인기도측정 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int studentCount;
	static HashMap<String, Integer> map;

	public static void main(String[] args) throws IOException {
		init();
		findOrder();
		System.out.print(sb);
	}

	static void findOrder() {
		List<String> order = new ArrayList<>(map.keySet());

		order.sort((o1, o2) -> {
			if (Objects.equals(map.get(o1), map.get(o2))) {
				return o1.compareTo(o2);
			}
			return map.get(o2) - map.get(o1);
		});

		for (String s : order) {
			sb.append(s).append(" ").append(map.get(s)).append("\n");
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		studentCount = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int student = 0; student < studentCount; student++) {
			map.put(st.nextToken(), 0);
		}

		for (int student = 0; student < studentCount; student++) {
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				map.put(s, map.get(s) + 1);
			}
		}
	}
}
