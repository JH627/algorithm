import java.io.*;
import java.util.*;

public class BOJ_11815_짝수홀수 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < T; i++) {
            long num = Long.parseLong(st.nextToken());
            long root = (long) Math.sqrt(num);
            sb.append(root * root == num ? 1 : 0).append(" ");
        }

        System.out.print(sb);
    }
}
