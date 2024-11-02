import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        cnt = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> arr = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            arr.add(cnt[i], i + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer i : arr) {
            sb.append(i).append(" ");
        }
        System.out.print(sb);
    }
}
