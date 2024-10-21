import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean possible = true;
        for (int m = 0; m < M; m++) {
            int height = Integer.parseInt(br.readLine());
            int[] arr = new int[height];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < height; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < height && possible; i++) {
                if (arr[i] > min) {
                    possible = false;
                }
                else {
                    min = arr[i];
                }
            }
        }

        System.out.print((possible) ? "Yes" : "No");
    }
}
