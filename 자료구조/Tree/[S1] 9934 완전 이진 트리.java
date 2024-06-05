import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int[] nums;
    static ArrayList<ArrayList<Integer>> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        int len = (int) Math.pow(2, K) - 1;
        nums = new int[len];
        arr = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            arr.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        search(0, len, 0);

        StringBuilder sb = new StringBuilder();
        for (ArrayList<Integer> ar : arr) {
            for (Integer n : ar) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void search(int start, int end, int depth) {
        if (depth == K) {
            return;
        }

        int mid = (start + end) / 2;

        arr.get(depth).add(nums[mid]);

        search(start, mid - 1, depth + 1);
        search(mid + 1, end, depth + 1);
    }
}