import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new HashMap<>();
        String s;
        int cnt = 0;
        while ((s = br.readLine()) != null) {
            map.put(s, map.getOrDefault(s, 0) + 1);
            cnt++;
        }

        ArrayList<String> arr = new ArrayList<>(map.keySet());
        Collections.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str).append(" ").append(String.format("%.4f", (double) (map.get(str) * 100) / (double) cnt)).append("\n");
        }
        System.out.print(sb);
    }
}
