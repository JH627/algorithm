import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int[] dist;
    static int N;
    static final int MAX = 501;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[MAX];
        dist = new int[MAX];
        Arrays.fill(arr, MAX);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        dist[0] = 1;
        int maxDist = 1;
        for (int i = 1; i < MAX; i++) {
            if (arr[i] == MAX) {
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dist[i] = Math.max(dist[j], dist[i]);
                }
            }
            dist[i]++;
            maxDist = Math.max(maxDist, dist[i]);
        }

        System.out.println(N - maxDist);
    }
}