import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);

        int cnt =0 ;
        int tape = 0;
        int now;
        for (int i = 0; i < N; i++) {
            now = arr.get(i);
            if (now > tape) {
                tape = now + L - 1;
                cnt++;
            }
        }

        System.out.print(cnt);
    }
}
