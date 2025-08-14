import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17390_이건꼭풀어야해 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int elementCount, queryCount;
    static int[] elements;

    public static void main(String[] args) throws IOException {
        init();
        useQuery();
        System.out.print(sb);
    }

    static void useQuery() throws IOException {
        for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            sb.append(elements[right] - elements[left - 1]).append("\n");
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        elementCount = Integer.parseInt(st.nextToken());
        queryCount = Integer.parseInt(st.nextToken());

        elements = new int[elementCount + 1];
        st = new StringTokenizer(br.readLine());
        for (int index = 0; index < elementCount; index++) {
            elements[index] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(elements);

        for (int index = 1; index <= elementCount; index++) {
            elements[index] +=  elements[index - 1];
        }
    }
}
