import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> lis = new ArrayList<>();
        lis.add(0);
        for (int n = 0; n < N; n++) {
            int now = arr[n];
            if (lis.get(lis.size() - 1) < now) {
                lis.add(now);
            }
            else {
                int s = 0;
                int e = lis.size() - 1;
                while (s < e) {
                    int mid = (s + e) / 2;
                    if (lis.get(mid) < now) {
                        s = mid + 1;
                    }
                    else {
                        e = mid;
                    }
                }
                lis.set(e, now);
            }
        }

        System.out.print(lis.size() - 1);
    }
}
