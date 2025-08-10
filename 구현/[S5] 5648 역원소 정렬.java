import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_5648_역원소정렬 {

    static BufferedReader br;
    static StringBuilder sb;
    static ArrayList<Long> nums;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        nums = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int count = 0;
        while (st.hasMoreTokens() && count < n) {
            nums.add(reverseNumber(Long.parseLong(st.nextToken())));
            count++;
        }

        while (count < n) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens() && count < n) {
                nums.add(reverseNumber(Long.parseLong(st.nextToken())));
                count++;
            }
        }

        Collections.sort(nums);

        for (Long num : nums) {
            sb.append(num).append("\n");
        }
        System.out.print(sb);
    }

    static long reverseNumber(long num) {
        long reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + (num % 10);
            num /= 10;
        }
        return reversed;
    }
}
