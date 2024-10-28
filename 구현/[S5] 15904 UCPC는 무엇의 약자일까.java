import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final char[] ucpc = {'U', 'C', 'P', 'C'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int len = s.length();
        int idx = 0;
        int word = 0;
        while (idx < len && word < 4) {
            if (s.charAt(idx) == ucpc[word]) {
                word++;
            }
            idx++;
        }

        System.out.print((word == 4) ? "I love UCPC" : "I hate UCPC");
    }
}
