import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static ArrayList<Integer> arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder();
        arr = new ArrayList<>();
        String str;
        while (true) {
            str = br.readLine();
            if (str == null || str.equals("")) {
                break;
            }
            arr.add(Integer.parseInt(str));
        }

        postOrder(0, arr.size() - 1);

        System.out.println(sb);
    }

    static void postOrder(int n, int end) {
        if (n > end) {
            return;
        }

        int mid = n + 1;
        while (mid <= end && arr.get(mid) < arr.get(n)) {
            mid++;
        }

        postOrder(n + 1, mid - 1);
        postOrder(mid, end);

        sb.append(arr.get(n)).append("\n");
    }
}