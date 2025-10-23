import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ_7656_만능오라클 {

    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String line = br.readLine();

        Pattern p = Pattern.compile("What is([^?.]*)\\?");
        Matcher m = p.matcher(line);

        while (m.find()) {
            String middle = m.group(1);
            sb.append("Forty-two is").append(middle).append(".").append("\n");
        }

        System.out.print(sb);
    }
}
