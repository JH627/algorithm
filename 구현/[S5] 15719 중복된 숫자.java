import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] num = new boolean[N];
        for (int n = 0; n < N; n++) {
            int a = Integer.parseInt(st.nextToken());
            if (num[a]) {
                System.out.print(a);
                return;
            }
            num[a] = true;
        }
    }
}
