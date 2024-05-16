import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        Map<String, Integer> m = new HashMap<>();
        int max = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            m.put(s, m.getOrDefault(s, 0) + 1);
            max = Math.max(max, m.get(s));
        }

        ArrayList<String> arr = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            if (entry.getValue() == max) {
                arr.add(entry.getKey());
            }
        }

        Collections.sort(arr);

        System.out.println(arr.get(0));
    }
}