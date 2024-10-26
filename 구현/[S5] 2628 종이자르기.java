import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static ArrayList<Integer> x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        x = new ArrayList<>(Arrays.asList(0, n));
        y = new ArrayList<>(Arrays.asList(0, m));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                x.add(Integer.parseInt(st.nextToken()));
            }
            else {
                y.add(Integer.parseInt(st.nextToken()));
            }
        }

        Collections.sort(x);
        Collections.sort(y);

        System.out.print(findMax(x) * findMax(y));
    }

    static int findMax(ArrayList<Integer> arr) {
        int size = arr.size();
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < size; i++) {
            int len = arr.get(i) - arr.get(i - 1);
            max = Math.max(max, len);
        }

        return max;
    }
}
