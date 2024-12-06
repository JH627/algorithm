import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        int alen = a.length();
        int blen = b.length();

        int r = (alen * blen) / gcd(Math.max(alen, blen), Math.min(alen, blen));

        b = b.repeat(r / blen);

        for (int i = 0; i < r;) {
            if (a.equals(b.substring(i, i + alen))) {
                i += alen;
            }
            else {
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }
}
