import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_25496_장신구명장임스 {

    static BufferedReader br;
    static StringTokenizer st;

    static int remainPower, elementCount;
    static int[] elements;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(getMaxCount());
    }

    static int getMaxCount() {
        int count = 0;
        for (int element : elements) {
            if (remainPower <= 0) {
                break;
            }
            count++;
            remainPower -= element;
        }
        return count;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        remainPower = 200 - Integer.parseInt(st.nextToken());
        elementCount = Integer.parseInt(st.nextToken());

        elements = new int[elementCount];
        st = new StringTokenizer(br.readLine());
        for (int element = 0; element < elementCount; element++) {
            elements[element] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(elements);
    }
}
