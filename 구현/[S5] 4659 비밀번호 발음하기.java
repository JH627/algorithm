import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static ArrayList<Character> mo = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = br.readLine();

            if (s.equals("end")) {
                break;
            }

            boolean appear = mo.contains(s.charAt(0));
            boolean possible = true;

            int cnt = 1;
            for (int i = 1; i < s.length() && possible; i++) {
                char now = s.charAt(i);
                char prev = s.charAt(i - 1);
                if (mo.contains(now)) {
                    appear = true;
                }
                if (mo.contains(prev) == mo.contains(now)) {
                    cnt++;
                    if (cnt == 2) {
                        if (prev == now) {
                            if (now == 'o' || now == 'e') {
                                continue;
                            }
                            possible = false;
                        }
                    }
                    if (cnt == 3) {
                        possible = false;
                    }
                }
                else {
                    cnt = 1;
                }
            }

            sb.append("<").append(s).append(">").append(" is ").append((possible && appear) ? "" : "not ").append("acceptable.");
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
