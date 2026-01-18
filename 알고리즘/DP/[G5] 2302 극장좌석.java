import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2302_극장좌석 {

	static BufferedReader br;

	static int seatCount, fixedSeatCount;
	static int[] count;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(getSeatCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		seatCount = Integer.parseInt(br.readLine());
		fixedSeatCount = Integer.parseInt(br.readLine());

		count = new int[41];
		count[0] = 1;
		count[1] = 1;
		count[2] = 2;

		for (int num = 3; num < 41; num++) {
			count[num] = count[num - 1] + count[num - 2];
		}
	}

	static int getSeatCount() throws IOException {
		int countSum = 1;

		int prevSeat = 0;
		for (int seat = 0; seat < fixedSeatCount; seat++) {
			int seatNumber = Integer.parseInt(br.readLine());
			countSum *= count[seatNumber - prevSeat - 1];
			prevSeat = seatNumber;
		}
		countSum *= count[seatCount - prevSeat];

		return countSum;
	}
}
