import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1283_단축키지정 {

    static BufferedReader br;
    static StringBuilder sb;

    static int optionCount;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        init();
        findOptionKey();
        System.out.print(sb);
    }

    static void findOptionKey() throws IOException {
        for (int option = 0; option < optionCount; option++) {
            String[] line = br.readLine().split(" ");
            int lineLength = line.length;
            boolean find = false;

            for (int lineIndex = 0; lineIndex < lineLength; lineIndex++) {
                int alpha = findAlpha(line[lineIndex].charAt(0));
                if (used[alpha]) {
                    continue;
                }
                find = true;
                line[lineIndex] = line[lineIndex].replaceFirst(String.valueOf(line[lineIndex].charAt(0)), String.format("[%c]", line[lineIndex].charAt(0)));
                used[alpha] = true;
                break;
            }

            if (find) {
                for (String singleLine : line) {
                    sb.append(singleLine).append(" ");
                }
                sb.append("\n");
                continue;
            }

            for (int lineIndex = 0; lineIndex < lineLength; lineIndex++) {
                if (find) {
                    sb.append(line[lineIndex]).append(" ");
                    continue;
                }
                for (int index = 0; index < line[lineIndex].length(); index++) {
                    char c = line[lineIndex].charAt(index);
                    if (c == ' ' || used[findAlpha(c)]) {
                        continue;
                    }
                    used[findAlpha(c)] = true;
                    line[lineIndex] = line[lineIndex].replaceFirst(String.valueOf(c), String.format("[%c]", c));
                    find = true;
                    break;
                }
                sb.append(line[lineIndex]).append(" ");
            }
            sb.append("\n");
        }
    }

    static int findAlpha(char c) {
        int alpha = 0;
        if (Character.isLowerCase(c)) {
            alpha = c - 'a';
        }
        else {
            alpha = c - 'A';
        }
        return alpha;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        optionCount = Integer.parseInt(br.readLine());
        used = new boolean[26];

        sb = new StringBuilder();
    }
}
