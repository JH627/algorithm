import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25379_피하자 {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    static int numCount;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        numCount = Integer.parseInt(br.readLine());
        
        long leftCount = 0; long rightCount = 0;
        long totalSum = 0;
        int index = 0;
        st = new StringTokenizer(br.readLine());
        for (int numIndex = 0; numIndex < numCount; numIndex++) {
            int num = Integer.parseInt(st.nextToken());
            
            if (num % 2 == 0) {
                totalSum += index++;
                leftCount += numIndex;
                rightCount += numCount - 1 - numIndex;
            }
        }
        
        System.out.print(Math.min(leftCount, rightCount) - totalSum);
    }
}
