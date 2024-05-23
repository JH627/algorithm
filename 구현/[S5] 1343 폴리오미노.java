import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        s = s.replace("XXXX", "AAAA");
        s = s.replace("XX", "BB");

        StringBuilder sb = new StringBuilder();
        sb.append((s.indexOf('X') == -1) ? s : "-1");
        System.out.println(sb);
    }
}