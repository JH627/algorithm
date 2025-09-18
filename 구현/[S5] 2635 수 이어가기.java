import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2635_수이어가기 {

	static BufferedReader br;
	static StringBuilder sb;

	static int firstNum;
	static ArrayList<Integer> answerList;

	public static void main(String[] args) throws IOException {
		init();
		findMaxSize();
		printAnswer();
	}

	static void findMaxSize() {
		for (int num = 1; num <= firstNum; num++) {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(firstNum);
			temp.add(num);

			int first = firstNum;
			int second = num;

			while (true) {
				int next = first - second;
				if (next < 0) {
					break;
				}
				temp.add(next);
				first = second;
				second = next;
			}

			if (answerList.size() < temp.size()) {
				answerList = temp;
			}
		}
	}

	static void printAnswer() {
		sb = new StringBuilder();
		sb.append(answerList.size()).append('\n');
		for (Integer answer : answerList) {
			sb.append(answer).append(' ');
		}
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		firstNum = Integer.parseInt(br.readLine());
		answerList = new ArrayList<>();
	}
}
