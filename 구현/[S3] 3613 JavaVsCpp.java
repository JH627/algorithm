import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_3613_JavavsCpp {

	static BufferedReader br; 
	
	static String s;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		
		boolean isJava = false;
		boolean isCpp = false;
		
		int len = s.length();
		for (int index = 0; index < len; index++) {
			if (Character.isUpperCase(s.charAt(index))) {
				if (index == 0) {
					System.out.println("Error!");
					return;
				}
				isJava = true;
			}
			if (s.charAt(index) == '_') {
				isCpp = true;
			}
		}
		
		if (s.charAt(0) == '_' || s.charAt(s.length() - 1) == '_' || s.contains("__")) {
			System.out.println("Error!");
			return;
		}
		
		if (isJava && isCpp) {
			System.out.println("Error!");
			return;
		}
		
		if (!isJava && !isCpp) {
			System.out.println(s);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		if (isJava) {
			for (int index = 0; index < len; index++) {
				if (Character.isUpperCase(s.charAt(index))) {
					sb.append("_").append((char) (s.charAt(index) + 32));
				}
				else {
					sb.append(s.charAt(index));
				}
			}
		}
		
		if (isCpp) {
			boolean prevUnderbar = false;
			for (int index = 0; index < len; index++) {
				if (s.charAt(index) == '_') {
					prevUnderbar = true;
				}
				else {
					if (prevUnderbar) {
						sb.append((char) (s.charAt(index) - 32));
						prevUnderbar = false;
					}
					else {
						sb.append(s.charAt(index));
					}
				}
			}
		}
		
		System.out.println(sb);
	}
}
