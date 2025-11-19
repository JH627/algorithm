import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2812_크게만들기 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numberLength, outCount;
    static char[] num;

    public static void main(String[] args) throws IOException {
        init();
        findNumber();
    }

    static void findNumber() {
        Deque<Character> deque = new LinkedList<>();

        int out = 0;
        for (Character n : num) {
            while (!deque.isEmpty() && out != outCount && deque.peekLast() < n) {
                deque.pollLast();
                out++;
            }
            deque.addLast(n);
        }

        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < numberLength - outCount; index++) {
            sb.append(deque.pollFirst());
        }
        System.out.print(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        numberLength = Integer.parseInt(st.nextToken());
        outCount = Integer.parseInt(st.nextToken());

        num = br.readLine().toCharArray();
    }

}
