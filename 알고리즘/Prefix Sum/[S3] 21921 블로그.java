import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int X, N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N + X];

        int MAX = 0;
        int count = 0;
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = N; i < N + X; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arr[i] += arr[i - 1];
            int sum = arr[i] - arr[i - N];
            if (sum == MAX) {
                count++;
            }
            if (sum > MAX) {
                MAX = sum;
                count = 1;
            }
        }

        if (MAX == 0) {
            sb.append("SAD");
        } else {
            sb.append(MAX).append("\n").append(count);
        }

        System.out.println(sb);
    }
}
