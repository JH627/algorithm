import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int SIZE = 101;
    static boolean[][] arr = new boolean[SIZE][SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int area = 0;
        int x1, x2, y1, y2;
        for (int k = 0; k < 4; k++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    if (arr[i][j]) {
                        continue;
                    }
                    arr[i][j] = true;
                    area++;
                }
            }
        }

        System.out.print(area);
    }
}
