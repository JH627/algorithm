import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            arr.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(arr);

        int min = arr.get(arr.size() - 1) - arr.get(0);
        int l = 0;
        int r = 0;
        while (r < N) {
            int lv = arr.get(l);
            int rv = arr.get(r);

            if (rv - lv >= M) {
                min = Math.min(min, rv - lv);
                if (l < r) {
                    l++;
                }
                else {
                    r++;
                }
            }
            else {
                r++;
            }
        }

        System.out.print(min);

    }
}
