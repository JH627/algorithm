import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 풀이
 * 
 * 1. 0부터 시작 0~9까지 뒤에 추가해보며 주어진 길이가 될 때까지 경우의 수를 탐색
 * 2-1. 새로 만들어진 수가 소수가 아닌 경우 추가 탐색 중지
 * 2-2. 새로 만들어진 수가 소수인 경우 정해진 길이가 될때까지 뒤에 숫자를 추가해보며 탐색
 *
 * 주의사항
 * 1. 에라토스테네스의 체를 사용하여 미리 소수를 찾아 놓는 방법도 있지만
 *    문제 메모리 제한은 4MB로 최대로 등장할 수 있는 N == 8 인 경우에 10,000,000개의 요소를 저장하기에는 부족할 수 있음
 */
public class BOJ_2023_신기한소수 {

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int answerLength = 0;

    public static void main(String[] args) throws IOException {
        init();

        findPrimes(0, 0);

        System.out.print(sb);
    }


    // length: 현재 숫자의 길이, currentNumber: 현재 숫자
    static void findPrimes(int length, int currentNumber) {
        // 정해진 숫자 길이가 된 경우
        if (length == answerLength) {
            sb.append(currentNumber).append('\n');
            return;
        }

        // 오름차순으로 숫자를 출력해야 하므로 0부터 탐색
        for (int num = 0; num < 10; num++) {
            // 새로운 수 생성
            int nextNumber = currentNumber * 10 + num;
            // 새로운 수가 소수인 경우에 추가로 탐색
            if (isPrime(nextNumber)) {
                findPrimes(length + 1, nextNumber);
            }
        }
    }

    // 소수인지 판별
    static boolean isPrime(int num) {
        // 2 이하는 소수가 아님
        if (num < 2) {
            return false;
        }
        
        // 제곱이 num을 넘어가기 전까지만 탐색
        for (int baseNumber = 2; baseNumber * baseNumber <= num; baseNumber++) {
            // 만약 나누어 떨어지는 경우 소수가 아님
            if (num % baseNumber == 0) {
                return false;
            }
        }
        return true;
    }

    static void init() throws IOException {
        answerLength = Integer.parseInt(br.readLine());
    }
}
