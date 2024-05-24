import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[10];

        String s = br.readLine();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            arr[s.charAt(i) - '0']++;
        }

        arr[6] += arr[9] + 1;
        arr[6] /= 2;
        arr[9] = 0;

        int max = 0;
        for (int i = 0; i < 10; i++) {
          max = Math.max(arr[i], max);
        }

        System.out.println(max);
    }
}