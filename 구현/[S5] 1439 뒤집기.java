import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int len = s.length();
        int clock = 0;
        char now = 'e';
        for (int i = 0; i < len;) {
              if (s.charAt(i) != now) {
                  clock++;
                  now = s.charAt(i);
              }
              while (i < len && s.charAt(i) == now) {
                  i++;
              }
        }

        System.out.println(clock / 2);
    }
}