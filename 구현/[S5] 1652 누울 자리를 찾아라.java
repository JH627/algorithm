import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, rowCount, colCount;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        rowCount = 0;
        colCount = 0;
        check(true);
        check(false);

        System.out.printf("%d %d", rowCount, colCount);
    }

    static void check(boolean isRow) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (arr[isRow ? i : j][isRow ? j : i] == '.') {
                    cnt++;
                }
                else {
                    if (cnt > 1) {
                        count++;
                    }
                    cnt = 0;
                }
            }

            if (cnt > 1) {
                count++;
            }
        }

        if (isRow) {
            rowCount = count;
        }
        else {
            colCount = count;
        }
    }
}
