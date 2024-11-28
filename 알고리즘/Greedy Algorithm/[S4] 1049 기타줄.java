import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] pack = new int[M];
        int[] unit = new int[M];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            pack[m] = Integer.parseInt(st.nextToken());
            unit[m] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pack);
        Arrays.sort(unit);

        int min = Math.min(((N / 6) + 1) * pack[0], N * unit[0]);
        min = Math.min(min, ((N / 6)) * pack[0] + (N % 6) * unit[0]);

        System.out.print(min);
    }
}
