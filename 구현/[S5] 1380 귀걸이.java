import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int num = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            String[] name = new String[n + 1];
            boolean[] confiscated = new boolean[n + 1];
            for (int i = 1; i < n + 1; i++) {
                name[i] = br.readLine();
            }

            for (int i = 0; i < 2 * n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int number = Integer.parseInt(st.nextToken());
                confiscated[number] = !confiscated[number];
            }

            for (int i = 1; i < n + 1; i++) {
                if (confiscated[i]) {
                    sb.append(num++).append(" ").append(name[i]).append("\n");
                    break;
                }
            }
        }

        System.out.print(sb);
    }
}
