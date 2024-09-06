import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int bound = (int) Math.round(n * 0.15);

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(arr);

        int size = n - (bound * 2);
        int ans = 0;
        for (int i = bound; i < size + bound; i++) {
            ans += arr.get(i);
        }

        System.out.print((int) Math.round((double) ans / size));
    }
}
