import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Long> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            list.add(Long.parseLong(br.readLine()));
        }

        Collections.sort(list, Collections.reverseOrder());

        long sum = 0;
        for (int n = 1; n <= N; n++) {
            long tip = list.get(n - 1) - (n - 1);
            if (tip <= 0) {
                break;
            }
            sum += tip;
        }

        System.out.print(sum);
    }
}
