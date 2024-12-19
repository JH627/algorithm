import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            for (int n = 0; n < N; n++) {
                arr[n] = Integer.parseInt(st.nextToken());
            }

            long sum = 0L;

            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    sum += gcd(arr[i], arr[j]);
                }
            }

            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }

    static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
