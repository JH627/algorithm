import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_빗물 {

    static BufferedReader br;
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[] height;
    static int maxIndex;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findAreaSize());
    }

    static int findAreaSize() {
        int size = 0;
        int maxHeight = 0;
        for (int left = 0; left <= maxIndex; left++) {
            if (height[left] < maxHeight) {
                size += maxHeight - height[left];
            }
            else {
                maxHeight = height[left];
            }
        }

        maxHeight = 0;
        for (int right = colSize - 1; right >= maxIndex; right--) {
            if (height[right] < maxHeight) {
                size += maxHeight - height[right];
            }
            else {
                maxHeight = height[right];
            }
        }
        return size;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        maxIndex = 0;
        height = new int[colSize];
        st = new StringTokenizer(br.readLine());
        for (int col = 0; col < colSize; col++) {
            height[col] = Integer.parseInt(st.nextToken());
            if (height[maxIndex] < height[col]) {
                maxIndex = col;
            }
        }
    }
}
