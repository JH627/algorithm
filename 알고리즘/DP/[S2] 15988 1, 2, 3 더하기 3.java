import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MAX = 1000001;
    static final int CUT = 1000000009;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new long[MAX];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;

        for (int i = 3; i < MAX; i++) {
            arr[i] = (arr[i - 3] + arr[i - 2] + arr[i - 1]) % CUT;
        }

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            sb.append(arr[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.print(sb);
    }

}
