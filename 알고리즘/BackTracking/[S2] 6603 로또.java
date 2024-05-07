import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int k;
    static int[] nums;
    static boolean[] visited;
    static ArrayList<Integer> arr;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new ArrayList<>();

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }

            sb = new StringBuilder();
            nums = new int[k];
            visited = new boolean[k];
            arr.clear();

            for (int i = 0; i < k; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            bt(0, 0);
//            sb.append("\n");
            System.out.println(sb);
        }
    }

    static private void bt(int idx, int clock) {
        if (clock == 6) {
            for (Integer num : arr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr.add(nums[i]);
                bt(i, clock + 1);
                visited[i] = false;
                arr.remove(arr.size() - 1);
            }
        }
    }
}