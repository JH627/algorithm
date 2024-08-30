import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static boolean[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new boolean[N + 1];

        int ans = 0;
        int cnt = 0;
        for (int i = 2; i < N + 1 && cnt != K; i++) {
            if (arr[i]) {
                continue;
            }
            for (int j = 1; i * j < N + 1; j++) {
                if (arr[i * j]) {
                    continue;
                }
                arr[i * j] = true;
                if (++cnt == K) {
                    ans = i * j;
                    break;
                }
            }
        }
        System.out.print(ans);
    }
}
