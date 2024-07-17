import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int M;
    static int bit = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "add":
                    bit |= (1 << Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    bit &= ~(1 << Integer.parseInt(st.nextToken()));
                    break;
                case "check":
                    sb.append(((bit & (1 << Integer.parseInt(st.nextToken()))) == 0) ? "0\n" : "1\n");
                    break;
                case "toggle":
                    bit ^= (1 << Integer.parseInt(st.nextToken()));
                    break;
                case "all":
                    bit |= ~(0);
                    break;
                case "empty":
                    bit &= 0;
                    break;
            }
        }

        System.out.println(sb);
    }
}
