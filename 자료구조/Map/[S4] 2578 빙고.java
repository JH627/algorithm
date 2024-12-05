import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] x, y;
    static int up, down;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, int[]> map = new HashMap<>();
        x = new int[5];
        y = new int[5];

        StringTokenizer st;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map.put(Integer.parseInt(st.nextToken()), new int[]{i, j});
            }
        }

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 5; j++) {
                int[] position = map.get(Integer.parseInt(st.nextToken()));
                checkBingo(position[0], position[1]);
                if (cnt >= 3) {
                    System.out.print(i * 5 + j);
                    return;
                }
            }
        }
    }

    static void checkBingo(int px, int py) {
        if (++y[py] == 5) {
            cnt++;
        }
        if (++x[px] == 5) {
            cnt++;
        }
        if (px == py && ++down == 5) {
            cnt++;
        }
        if ((px + py) == 4 && ++up == 5) {
            cnt++;
        }
    }
}
