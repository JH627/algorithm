import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ_12873_기념품 {

	static BufferedReader br;

	static int manCount;
	static LinkedList<Integer> list;

	public static void main(String[] args) throws IOException {
		init();

		System.out.print(getLastMan());
	}

	static int getLastMan() {
		long cursor = 0;
		for (int step = 1; step < manCount; step++) {
			long toMove = (long) Math.pow(step, 3);
			long remainManCount = manCount - step + 1;

			cursor = (cursor + toMove - 1) % remainManCount;

			list.remove((int) cursor);
		}

		return list.get(0);
	}

	public static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		manCount = Integer.parseInt(br.readLine());

		list = new LinkedList<>();
		for (int count = 0; count < manCount; count++) {
			list.add(count + 1);
		}
	}
}
