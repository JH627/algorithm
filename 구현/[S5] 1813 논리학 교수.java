import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class BOJ_1813_논리학교수 {

    static BufferedReader br;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        String[] arr = br.readLine().split(" ");
        for (String s : arr) {
            list.add(Integer.parseInt(s));
        }

        int answer = -1;
        for (int k = 0; k <= N; k++) {
            int count = 0;
            for (int val : list) {
                if (val == k) {
                    count++;
                }
            }
            if (count == k) {
                answer = k;
            }
        }

        System.out.print(answer);
    }
}
