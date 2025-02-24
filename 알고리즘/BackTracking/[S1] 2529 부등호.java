import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2529_부등호 {

	static int elementCount;
	static boolean[] isUp, isSelected;
	static ArrayList<String> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());

		isUp = new boolean[elementCount];
		isSelected = new boolean[10];
		list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			if (st.nextToken().charAt(0) == '<') {
				isUp[elementIndex] = true;
			}
		}

		for (int num = 0; num < 10; num++) {
			isSelected[num] = true;
			getRightSequence(0, String.valueOf(num));
			isSelected[num] = false;
		}

		Collections.sort(list);
		System.out.println(list.get(list.size() - 1));
		System.out.print(list.get(0));
	}

	static void getRightSequence(int selectedIndex, String currentNum) {
		if (selectedIndex == elementCount) {
			list.add(currentNum);
			return;
		}

		int last = currentNum.charAt(selectedIndex) - '0';

		if (isUp[selectedIndex]) {
			for (int index = last + 1; index < 10; index++) {
				if (!isSelected[index]) {
					isSelected[index] = true;
					getRightSequence(selectedIndex + 1, currentNum + index);
					isSelected[index] = false;
				}
			}
		}
		else {
			for (int index = 0; index < last; index++) {
				if (!isSelected[index]) {
					isSelected[index] = true;
					getRightSequence(selectedIndex + 1, currentNum + index);
					isSelected[index] = false;
				}
			}
		}
	}

}
