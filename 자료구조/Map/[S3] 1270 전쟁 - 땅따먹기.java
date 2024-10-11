import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            HashMap<Long, Long> map = new HashMap<>();
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            long win = -1;
            for (int i = 0; i < n; i++) {
                long num = Long.parseLong(st.nextToken());
                map.put(num, map.getOrDefault(num, 0L) + 1);
                if (map.get(num) > n / 2) {
                    win = num;
                    break;
                }
            }
            sb.append((win == -1) ? "SYJKGW" : win).append("\n");
        }

        System.out.print(sb);
    }
}
