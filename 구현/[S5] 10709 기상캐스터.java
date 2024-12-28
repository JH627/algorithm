import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < H; h++) {
            String s = br.readLine();
            boolean found = false;
            int cnt = 0;
            for (int w = 0; w < W; w++) {
                if (s.charAt(w) == 'c') {
                    found = true;
                    cnt = 0;
                }
                if (!found) {
                    sb.append(-1).append(" ");
                    continue;
                }
                sb.append(cnt++).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
