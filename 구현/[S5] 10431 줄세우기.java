import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        P = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[] arr= new int[20];
        for (int p = 0; p < P; p++) {
            st = new StringTokenizer(br.readLine());
            sb.append(st.nextToken()).append(" ");
            for (int i = 0; i < 20; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int clock = 0;
            for (int i = 0; i < 20; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] > arr[i]) {
                        clock++;
                    }
                }
            }

            sb.append(clock).append("\n");
        }

        System.out.println(sb);
    }
}