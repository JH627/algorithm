import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N;
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        set = new HashSet<>();

        String s;
        for (int n = 0; n < N; n++) {
            s = br.readLine();
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            set.add(String.valueOf(arr));
        }

        System.out.print(set.size());
    }
}
