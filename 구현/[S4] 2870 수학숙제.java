import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int N;
    static ArrayList<BigInteger> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();

        String s;
        for (int n = 0; n < N; n++) {
            s = br.readLine();
            String[] split = s.split("\\D");
            for (String string : split) {
                if (string.equals("")) {
                    continue;
                }
                arr.add(new BigInteger(string));
            }
        }

        Collections.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (BigInteger n : arr) {
            sb.append(n).append("\n");
        }
        System.out.print(sb);
    }
}
