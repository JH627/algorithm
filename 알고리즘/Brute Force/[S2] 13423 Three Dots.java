import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_13423_ThreeDots {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int elementCount;
    static int[] nums;
    static HashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < TC; testCase++) {
            init();
            sb.append(getCount()).append("\n");
        }
        System.out.print(sb);
    }

    static long getCount() {
        long count = 0;
        for (int start = 0; start < elementCount - 2; start++) {
            int startNum = nums[start];
            for (int mid = start + 1; mid < elementCount - 1; mid++) {
                int midNum = nums[mid];
                int diff = midNum - startNum;
                if (set.contains(midNum + diff)) {
                    count++;
                }
            }
        }
        return count;
    }

    static void init() throws IOException {
        elementCount = Integer.parseInt(br.readLine());
        nums = new int[elementCount];
        set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int num = 0; num < elementCount; num++) {
            nums[num] = Integer.parseInt(st.nextToken());
            set.add(nums[num]);
        }
        Arrays.sort(nums);
    }
}
