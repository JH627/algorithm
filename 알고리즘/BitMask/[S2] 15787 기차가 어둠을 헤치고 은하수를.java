import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_15787_기차가어둠을헤치고은하수를 {

	static BufferedReader br;
	static StringTokenizer st;

	static int trainCount, queryCount;
	static int[] trainStatus;

	public static void main(String[] args) throws IOException {
		init();
		useQuery();
		System.out.print(getAnswer());
	}

	static void useQuery() throws IOException {
		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());

			int oper = Integer.parseInt(st.nextToken());
			int nowTrain = Integer.parseInt(st.nextToken()) - 1;
			int sequence;
			switch (oper) {
				case 1:
					sequence = Integer.parseInt(st.nextToken()) - 1;
					trainStatus[nowTrain] |= (1 << sequence);
					break;
				case 2:
					sequence = Integer.parseInt(st.nextToken()) - 1;
					trainStatus[nowTrain] &= ~(1 << sequence);
					break;
				case 3:
					trainStatus[nowTrain] = (trainStatus[nowTrain] << 1) & ((1 << 20) - 1);
					break;
				case 4:
					trainStatus[nowTrain] >>= 1;
					break;
			}
		}
	}

	static int getAnswer() {
		HashSet<Integer> set = new HashSet<>();
		for (int status : trainStatus) {
			set.add(status);
		}
		return set.size();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		trainCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());

		trainStatus = new int[trainCount];
	}
}
