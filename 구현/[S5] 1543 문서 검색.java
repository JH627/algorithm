import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String s2 = br.readLine();

        String replaced = s.replace(s2, "");
        int ans = (s.length() - replaced.length()) / s2.length();
        System.out.println(ans);
    }
}