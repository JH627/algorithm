import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new HashMap<>();
        for (int n = 0; n < N; n++) {
            map.put(br.readLine(), 1);
        }

        int ans = N;
        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), ",");

            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                if (map.containsKey(s)) {
                    map.remove(s);
                    ans--;
                }
            }
            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}
