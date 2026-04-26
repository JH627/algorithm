import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_32136_소신발언 {

    static BufferedReader br;
    static StringTokenizer st;

    static int elementCount;
    static int[] times;

    public static void main(String[] args) throws IOException {
        init();

        int left = 0;
        int right = elementCount - 1;
        while (right - left > 2) {
            int midLeft = left + (right - left) / 3;
            int midRight = right - (right - left) / 3;

            long midLeftTime = getMaxMeltingTime(midLeft);
            long midRightTime = getMaxMeltingTime(midRight);

            if (midLeftTime > midRightTime) {
                left = midLeft;
            }
            else {
                right = midRight;
            }
        }

        long answer = Long.MAX_VALUE;
        for (int index = left; index <= right; index++) {
            answer = Math.min(answer, getMaxMeltingTime(index));
        }

        System.out.print(answer);
    }

    static long getMaxMeltingTime(int heaterIndex) {
        long maxTime = 0;

        for (int index = 0; index < elementCount; index++) {
            long time = (long) Math.abs(heaterIndex - index) * times[index];
            maxTime = Math.max(maxTime, time);
        }

        return maxTime;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        elementCount = Integer.parseInt(br.readLine());

        times = new int[elementCount];

        st = new StringTokenizer(br.readLine());
        for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
            times[elementIndex] = Integer.parseInt(st.nextToken());
        }
    }
}
