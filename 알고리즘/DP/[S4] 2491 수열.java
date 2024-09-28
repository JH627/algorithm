import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, up, down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        up = new int[N];
        down = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        up[0] = 1;
        down[0] = 1;
        int max = 1;
        for (int i = 1; i < N; i++) {
            up[i] = (arr[i] >= arr[i - 1]) ? up[i - 1] + 1 : 1;
            down[i] = (arr[i] <= arr[i - 1]) ? down[i - 1] + 1 : 1;
            max = Math.max(max, Math.max(up[i], down[i]));
        }

        System.out.print(max);
    }
}
