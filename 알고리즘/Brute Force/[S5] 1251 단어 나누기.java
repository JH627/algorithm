import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		ArrayList<String> arr = new ArrayList<>();

		int len = s.length();
		StringBuilder sb;
		for (int i = 1; i < len; i++) {
			StringBuilder a = new StringBuilder(s.substring(0, i)).reverse();
			for (int j = i + 1; j < len; j++) {
				StringBuilder b = new StringBuilder(s.substring(i, j)).reverse();
				StringBuilder c = new StringBuilder(s.substring(j)).reverse();
				sb = new StringBuilder();
				sb.append(a).append(b).append(c);
				arr.add(sb.toString());
			}
		}

		Collections.sort(arr);
		System.out.print(arr.get(0));
	}
	
}
