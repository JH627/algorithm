import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String s;
    static int len;
    static int[] pi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        len = Integer.parseInt(br.readLine());
        s = br.readLine();

        pi = new int[len];

        int max = failureFunction();

        System.out.println(max);
    }

    static int failureFunction() {
        int max = 1;

        int i = 1;
        int j = 0;
        while (i < len) {
            if (s.charAt(i) == s.charAt(j)) {
                pi[i++] = ++j;
            }
            else if (j > 0) {
                j = pi[j - 1];
            }
            else {
                i++;
            }
            max = Math.max(max, i - pi[i - 1]);
        }

        return max;
    }
}