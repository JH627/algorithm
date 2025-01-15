import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static final int[] line = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();

		int len = a.length();
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			arr.add(line[a.charAt(i) - 'A']);
			arr.add(line[b.charAt(i) - 'A']);
		}

		while (true) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int i = 0; i < arr.size() - 1; i++) {
				temp.add((arr.get(i) + arr.get(i + 1)) % 10);
			}

			arr = temp;
			if (arr.size() == 2) {
				System.out.printf("%d%d", arr.get(0), arr.get(1));
				break;
			}
		}
	}
}
