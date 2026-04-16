import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1561_놀이공원 {

	static BufferedReader br;
	static StringTokenizer st;

	static int userCount, rideCount;
	static int[] rideTime;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findLastRideNumber());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		userCount = Integer.parseInt(st.nextToken());
		rideCount = Integer.parseInt(st.nextToken());

		rideTime = new int[rideCount];
		st = new StringTokenizer(br.readLine());
		for (int ride = 0; ride < rideCount; ride++) {
			rideTime[ride] = Integer.parseInt(st.nextToken());
		}
	}

	static int findLastRideNumber() {
		if (userCount <= rideCount) {
			return userCount;
		}

		long l = 0;
		long r = 60000000000L;

		while (l <= r) {
			long mid = l + (r - l) / 2;

			long rideUserCount = getRideUserCount(mid);
			if (rideUserCount >= userCount) {
				r = mid - 1;
			}
			else {
				l = mid + 1;
			}
		}

		return findLastUserIndex(l);
	}

	static long getRideUserCount(long time) {
		long rideUserCount = rideCount;
		for (int ride = 0; ride < rideCount; ride++) {
			rideUserCount += time / rideTime[ride];
		}
		return rideUserCount;
	}

	static int findLastUserIndex(long time) {
		long beforeCount = getRideUserCount(time - 1);

		for (int ride = 0; ride < rideCount; ride++) {
			if (time % rideTime[ride] == 0) {
				beforeCount++;
				if (beforeCount == userCount) {
					return ride + 1;
				}
			}
		}
		return -1;
	}
}
