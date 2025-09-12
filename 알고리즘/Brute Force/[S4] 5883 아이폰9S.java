import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_5883_아이폰9S {

    static BufferedReader br;

    static int peopleCount;
    static int[] numbers;
    static HashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(getMaxLength());
    }

    static int getMaxLength() {
        int maxLength = 1;

        for (int number : set) {
            int prev = -1;
            int curLength = 0;
            for (int index = 0; index < peopleCount; index++) {
                if (numbers[index] == number) {
                    continue;
                }
                if (numbers[index] == prev) {
                    maxLength = Math.max(++curLength, maxLength);
                }
                else {
                    prev = numbers[index];
                    curLength = 1;
                }
            }
        }

        return maxLength;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        peopleCount = Integer.parseInt(br.readLine());
        numbers = new int[peopleCount];
        set = new HashSet<>();
        for (int people = 0; people < peopleCount; people++) {
            numbers[people] = Integer.parseInt(br.readLine());
            set.add(numbers[people]);
        }
    }
}
