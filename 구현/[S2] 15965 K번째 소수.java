import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int K;
    static final int MAX = 8000001;
    static boolean[] checked;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        checked = new boolean[MAX];

        for (int i = 2; i < MAX; i++) {
            if (!checked[i]) {
                arr.add(i);
                if (arr.size() == K) {
                    System.out.print(i);
                    return;
                }
            }
            for (int j = 2; i * j < MAX; j++) {
                checked[i * j] = true;
            }
        }
    }
}
