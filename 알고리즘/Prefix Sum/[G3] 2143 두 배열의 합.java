import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int T, n, m;
    static long[] nArr, mArr;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        Map<Long, Long> nMap = new HashMap<>();
        Map<Long, Long> mMap = new HashMap<>();

        n = Integer.parseInt(br.readLine());
        nArr = new long[n];
        init(nMap, n, nArr);

        m = Integer.parseInt(br.readLine());
        mArr = new long[m];
        init(mMap, m, mArr);

        cal(nMap, n, nArr);
        cal(mMap, m, mArr);

        long cnt = 0;
        for (Long l : nMap.keySet()) {
            Long clock = mMap.getOrDefault(T - l, 0L);
            if (clock != 0) {
                cnt += clock * nMap.get(l);
            }
        }

        System.out.println(cnt);
    }

    private static void init(Map<Long, Long> map, int n, long[] arr) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            map.put(arr[i], map.getOrDefault(arr[i], 0L) + 1);
        }
    }

    static void cal(Map<Long, Long> map, int n, long[] arr) {
        for (int i = 0; i < n; i++) {
            long sum = arr[i];
            for (int j = i + 1; j < n; j++) {
                sum += arr[j];
                map.put(sum, map.getOrDefault(sum, 0L) + 1);
            }
        }
    }
}
