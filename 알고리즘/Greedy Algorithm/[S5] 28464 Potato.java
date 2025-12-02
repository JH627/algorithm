import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_28464_Potato {

    static BufferedReader br;
    static StringTokenizer st;

    static int elementCount;
    static int[] count;

    public static void main(String[] args) throws IOException {
        init();
        findCount();
    }

    static void findCount() {
        int aUserCount = 0;
        int bUserCount = 0;

        Arrays.sort(count);
        for (int element = elementCount - 1; element >= elementCount / 2; element--) {
            bUserCount += count[element];
        }
        for (int element = 0; element < elementCount / 2; element++) {
            aUserCount += count[element];
        }

        System.out.printf("%d %d", aUserCount, bUserCount);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        elementCount = Integer.parseInt(br.readLine());
        count = new int[elementCount];
        st = new StringTokenizer(br.readLine());
        for (int element = 0; element < elementCount; element++) {
            count[element] = Integer.parseInt(st.nextToken());
        }
    }
}
