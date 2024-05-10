import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        s = br.readLine();
        int len = s.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += s.charAt(i) - '0';
        }

        boolean possible = false;
        if (sum % 3 == 0) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            if (arr[0] == '0') {
                possible = true;
                for (int i = len - 1; i >= 0; i--) {
                    sb.append(arr[i]);
                }
            }
        }
        if (!possible) {
            sb.append(-1);
        }

        System.out.println(sb);
    }
}