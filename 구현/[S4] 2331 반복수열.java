import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int A, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        list.add(A);

        while (true) {
            int now = list.get(list.size() - 1);

            int next = 0;
            while (now != 0) {
                next += (int)Math.pow(now % 10, P);
                now /= 10;
            }

            if (list.contains(next)) {
                System.out.print(list.indexOf(next));
                return;
            }
            list.add(next);
        }
    }
}
