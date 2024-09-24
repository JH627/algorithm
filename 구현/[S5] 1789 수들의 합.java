import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static Long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(br.readLine());

        int num = 1;
        while (true) {
            if (N < num) {
                break;
            }
            N -= num++;
        }
        System.out.print(num - 1);
    }
}
