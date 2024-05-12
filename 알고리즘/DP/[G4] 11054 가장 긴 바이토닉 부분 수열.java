import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] upDp;
    static int[] downDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        upDp = new int[N];
        downDp = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        upDp[0] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    upDp[i] = Math.max(upDp[i], upDp[j]);
                }
            }
            upDp[i]++;
        }

        downDp[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j <= N - 1; j++) {
                if (arr[i] > arr[j]) {
                    downDp[i] = Math.max(downDp[i], downDp[j]);
                }
            }
            downDp[i]++;
        }

        for (int i = 0; i < N; i++) {
            upDp[i] += downDp[i] - 1;
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(upDp[i], max);
        }

        System.out.println(max);
    }
}