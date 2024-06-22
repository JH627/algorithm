import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        HashMap<Long, Integer> map = new HashMap<>();

        long max = Long.MIN_VALUE;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            long n = Long.parseLong(br.readLine());
            map.put(n, map.getOrDefault(n, 0) + 1);

            if (map.get(n) > max) {
                max = map.get(n);
                ans = n;
            }
            else if (map.get(n) == max) {
                ans = Math.min(ans, n);
            }
        }

        System.out.println(ans);
    }
}