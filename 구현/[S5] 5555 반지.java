import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class BOJ_5555_반지 {
 
    static int elementCount;
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        elementCount = Integer.parseInt(br.readLine());
        int count = 0;
        String toCheck;
        for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
            toCheck = br.readLine();
            toCheck += toCheck;
 
            if (toCheck.contains(origin)) {
                count++;
            }
        }
        
        System.out.print(count);
    }
}
