import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String s = br.readLine();

            int len = s.length();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = s.charAt(i) - '0';
            }

            int lastChangedIdx = len;
            for (int i = len - 2; i >= 0; i--) {
                if (arr[i] > arr[i + 1]) {
                    arr[i]--;
                    lastChangedIdx = i + 1;
                }
            }

            sb.append("Case #").append(t + 1).append(": ");
            for (int i = (arr[0] == 0) ? 1 : 0; i < lastChangedIdx; i++) {
                sb.append(arr[i]);
            }
            for (int i = lastChangedIdx; i < len; i++) {
                sb.append('9');
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
