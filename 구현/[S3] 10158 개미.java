import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int W, H, P, Q, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(br.readLine());

        int x = (P + T) % (2 * W);
        int y = (Q + T) % (2 * H);

        if (x > W) {
            x = 2 * W - x;
        }
        if (y > H) {
            y = 2 * H - y;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(x).append(" ").append(y);
        System.out.print(sb);
    }
}
