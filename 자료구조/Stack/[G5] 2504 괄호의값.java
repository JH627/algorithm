import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504_괄호의값 {

    static BufferedReader br;

    static char[] line;

    public static void main(String[] args) throws Exception {
        init();
        System.out.print(findValue());
    }

    static int findValue() {
        int value = 0;
        boolean possible = true;
        Stack<Integer> stack = new Stack<>();

        for (char c : line) {
            int tempValue = 0;
            boolean found = false;
            switch (c) {
                case '(':
                    stack.add(-1);
                    break;
                case '[':
                    stack.add(-2);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        possible = false;
                        break;
                    }
                    while (!stack.isEmpty()) {
                        if (stack.peek() == -1) {
                            found = true;
                            stack.pop();
                            if (tempValue == 0) {
                                stack.push(2);
                            }
                            else {
                                stack.push(tempValue * 2);
                            }
                            break;
                        }
                        else if (stack.peek() >= 1) {
                            tempValue += Integer.parseInt(String.valueOf(stack.pop()));
                        }
                        else {
                            possible = false;
                            break;
                        }
                    }
                    if (!found) {
                        possible = false;
                        break;
                    }

                    break;
                case ']':
                    if (stack.isEmpty()) {
                        possible = false;
                        break;
                    }
                    while (!stack.isEmpty()) {
                        if (stack.peek() == -2) {
                            found = true;
                            stack.pop();
                            if (tempValue == 0) {
                                stack.push(3);
                            }
                            else {
                                stack.push(tempValue * 3);
                            }
                            break;
                        }
                        else if (stack.peek() >= 1) {
                            tempValue += Integer.parseInt(String.valueOf(stack.pop()));
                        }
                        else {
                            possible = false;
                            break;
                        }
                    }
                    if (!found) {
                        possible = false;
                        break;
                    }

                    break;
            }
        }

        while (possible && !stack.isEmpty()) {
            if (stack.peek() >= 1) {
                value += stack.pop();
            }
            else {
                possible = false;
                break;
            }
        }

        return possible ? value : 0;
    }

    static void init() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine().toCharArray();
    }
}
