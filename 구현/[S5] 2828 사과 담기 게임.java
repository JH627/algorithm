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

        int l = 0;
        int r = M - 1;
        int dist = 0;

        int J = Integer.parseInt(br.readLine());
        for (int j = 0; j < J; j++) {
            int apple = Integer.parseInt(br.readLine()) - 1;
            if (apple < l) {
                int gap = l - apple;
                dist += gap;
                r -= gap;
                l -= gap;
            }
            else if (apple > r) {
                int gap = apple - r;
                dist += gap;
                r += gap;
                l += gap;
            }
        }
        System.out.print(dist);
    }
}
