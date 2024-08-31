import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static int N;
    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new HashMap<>();

        for (int n = 0; n < N; n++) {
            String s = br.readLine();
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (int n = 0; n < N - 1; n++) {
            String s = br.readLine();
            map.put(s, map.getOrDefault(s, 0) - 1);
        }

        for (String s : map.keySet()) {
            if (map.get(s) != 0) {
                System.out.print(s);
                return;
            }
        }
    }
}
