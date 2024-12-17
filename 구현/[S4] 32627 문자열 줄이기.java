import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String s = br.readLine();
        char[] arr = s.toCharArray();

        Arrays.sort(arr);
        int[] remove = new int[26];
        for (int m = 0; m < M; m++) {
            remove[arr[m] - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < N; n++) {
            if (remove[s.charAt(n) - 'a'] > 0) {
                remove[s.charAt(n) - 'a']--;
            }
            else {
                sb.append(s.charAt(n));
            }
        }
        System.out.print(sb);
    }
}
