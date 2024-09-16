import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int X;
    static final int[] arr = {64, 32, 16, 8, 4, 2, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());

        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            if (arr[i] <= X) {
                sum += arr[i];
                cnt++;
            }
            if (sum > X) {
                sum -= arr[i];
                cnt--;
            }
            if (sum == X) {
                break;
            }
        }
        System.out.print(cnt);
    }
}