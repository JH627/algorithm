import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        String s = br.readLine();
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        Stack<Double> stack = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isAlphabetic(c)) {
                double num = arr[c - 'A'];
                stack.push(num);
            }
            else {
                double a = stack.pop();
                double b = stack.pop();
                double ret = 0;

                switch (c) {
                    case '+':
                        ret = b + a;
                        break;
                    case '-':
                        ret = b - a;
                        break;
                    case '*':
                        ret = b * a;
                        break;
                    case '/':
                        ret = b / a;
                }
                stack.push(ret);
            }
        }
        System.out.printf("%.2f", stack.pop());
    }
}
