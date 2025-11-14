import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2428_표절 {

    static BufferedReader br;
    static StringTokenizer st;

    static int elementCount;
    static int[] elements;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(getCount());
    }

    static long getCount() {
        long count = 0;

        int r = 0;
        for (int l = 0; l < elementCount; l++) {
            while (r < elementCount && elements[l] * 10 >= elements[r] * 9) {
                r++;
            }

            count += (r - l - 1);
        }

        return count;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        elementCount = Integer.parseInt(br.readLine());
        elements = new int[elementCount];

        st = new StringTokenizer(br.readLine());
        for (int element = 0; element < elementCount; element++) {
            elements[element] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(elements);
    }
}
