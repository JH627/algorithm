import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int min = Integer.MAX_VALUE;
        int ans = 0;

        for (int n = 0; n < N; n++) {
            int now = Integer.parseInt(st.nextToken());

            ans = Math.max(ans, now - min);
            min = Math.min(now, min);
        }

        System.out.print(ans);
    }
}
