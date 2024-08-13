import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int T, N;
    static HashMap<String, ArrayList<String>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new HashMap<>();

            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();
                map.computeIfAbsent(type, k -> new ArrayList<>()).add(name);
            }

            int ans = 1;
            for (ArrayList<String> value : map.values()) {
                ans *= value.size() + 1;
            }
            sb.append(ans - 1).append("\n");
        }

        System.out.println(sb);
    }
}
