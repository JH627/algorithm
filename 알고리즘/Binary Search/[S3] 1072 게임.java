import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int rate = getRate(X, Y);

        int ans = -1;
        int l = 0;
        int r = 1000000000;
        while (l <= r) {
            int m = (l + r) / 2;

            if (getRate(X + m, Y + m) != rate) {
                ans = m;
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }

        System.out.print(ans);
    }

    static int getRate(int x, int y) {
        return (int) ((long) y * 100 / x);
    }
}
