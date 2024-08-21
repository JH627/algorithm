import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static HashSet<String> set;
    static Character c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        set = new HashSet<>();

        N = Integer.parseInt(st.nextToken());
        c = st.nextToken().charAt(0);

        if (c == 'Y') { C = 1; }
        else if (c == 'F') { C = 2; }
        else if (c == 'O') { C = 3; }

        int ans = 0;
        while (N-- > 0) {
            String s = br.readLine();
            if (!set.contains(s)) {
                ans++;
            }
            set.add(s);
        }

        System.out.println(ans / C);
    }
}
