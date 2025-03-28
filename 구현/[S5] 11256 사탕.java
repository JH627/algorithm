import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ_11256_사탕 {
    
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int T;
    static int candyCount, boxCount;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in)); 
        sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            init(); 

            for (int box = 0; box < boxCount; box++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr.add(a * b);
            }
            
            Collections.sort(arr, Collections.reverseOrder());
            
            int count = 0;
            for (int i = 0; i < boxCount; i++) {
                if (candyCount <= 0) {
                    break;
                }
                candyCount -= arr.get(i);
                count++;
            }
            
            sb.append(count).append("\n");
        }
        
        System.out.print(sb);
    }

    static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        
        candyCount = Integer.parseInt(st.nextToken());
        boxCount = Integer.parseInt(st.nextToken());
        
        arr = new ArrayList<>();
    }
}
