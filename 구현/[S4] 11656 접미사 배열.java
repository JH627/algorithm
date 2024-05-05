import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int len = s.length();

        String[] arr = new String[len];

        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.substring(i , len);
        }

        Arrays.sort(arr);

        for(String str : arr) {
            bw.write(str);
            bw.write('\n');
        }
        bw.flush();
    }
}