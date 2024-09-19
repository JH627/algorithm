import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, C;
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new LinkedHashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>(map.keySet());

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(map.get(o2), map.get(o1));
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Integer n : list) {
            int num = map.get(n);
            for (int i = 0; i < num; i++) {
                sb.append(n).append(" ");
            }
        }

        System.out.print(sb);
    }
}
