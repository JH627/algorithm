import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ_12845_모두의마블 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		init();
        System.out.print(getMaxMoney());
	}
    
    static int getMaxMoney() {
        if (elementCount == 0) {
            return 0;
        }
        
        Arrays.sort(elements);
        
        int sum = 0;
        for (int index = 0; index < elementCount - 1; index++) {
            sum += elements[index];
        }
        
        sum += elements[elementCount - 1] * (elementCount - 1);
        return sum;
    }

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
        
        elementCount = Integer.parseInt(br.readLine());
        
        elements = new int[elementCount];
        st = new StringTokenizer(br.readLine());
        for (int element = 0; element < elementCount; element++) {
            elements[element] = Integer.parseInt(st.nextToken());
        }
	}
}
