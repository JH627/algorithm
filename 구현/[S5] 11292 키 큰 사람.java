import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11292_키큰사람 {
    
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    
    public static void main(String[] args) throws Exception {
        init();
        find();
        System.out.print(sb);
    }
    
    static void find() throws Exception {
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            double max = 0;
            ArrayList<String> answer = new ArrayList<>();
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                double b = Double.parseDouble(st.nextToken());
                
                if (b < max) {
                    continue;
                }
                if (b > max) {
                    answer = new ArrayList<>();
                }
                
                answer.add(a);
                max = b;
            }

            for (String s : answer) {
                sb.append(s).append(' ');
            }
            sb.append('\n');
        }
    }
    
    static void init() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
    }

}
