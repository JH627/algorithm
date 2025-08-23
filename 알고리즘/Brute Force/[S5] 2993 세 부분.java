import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2993_세부분 {
	
	static BufferedReader br;	
	
	static String s;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));

		s = br.readLine();
		
		String min = "z";
		for (int index = 1; index < s.length() - 1; index++) {
			for (int nextIndex = index + 1; nextIndex < s.length(); nextIndex++) {
				String newStr = getReversedWord(index, nextIndex);
				if (min.compareTo(newStr) > 0) {
					min = newStr;
				}
			}
		}
		
		System.out.print(min);
	}
	
	static String getReversedWord(int index, int nextIndex) {
		StringBuilder front = new StringBuilder(s.substring(0, index));
		StringBuilder mid = new StringBuilder(s.substring(index, nextIndex));
		StringBuilder back = new StringBuilder(s.substring(nextIndex));
		
		return front.reverse().toString() + mid.reverse().toString() + back.reverse().toString();
	}
}
