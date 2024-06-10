import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        HashSet<String> set = new HashSet<>();

        int len = arr[0].length();
        int ans = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < N; j++) {
                set.add(arr[j].substring(len - i));
            }
            if (set.size() == N) {
                ans = i;
                break;
            }
            set.clear();
        }

        System.out.println(ans);
    }
}