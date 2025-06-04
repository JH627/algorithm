import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1069_집으로 {

	static BufferedReader br;
	static StringTokenizer st;

	static int x, y, d, t;

	public static void main(String[] args) throws IOException {
		init();

		System.out.printf("%.9f", getMinTime());
	}

	static double getMinTime() {
		double dist = Math.sqrt(x * x + y * y);
		double minTime = dist;

		int minJumpCount = (int)(dist / d);
		double minJumpTime = minJumpCount * t + dist - minJumpCount * d;

		int maxJumpCount = minJumpCount + 1;
		double maxJumpTime = maxJumpCount * t + maxJumpCount * d - dist;

		minTime = Math.min(minTime, minJumpTime);
		minTime = Math.min(minTime, maxJumpTime);

		if (minJumpCount > 0) {
			minTime = Math.min(minTime, (maxJumpCount * t));
		}
		else if (dist < d) {
			minTime = Math.min(minTime, t * 2.0);
		}

		return minTime;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
	}
}
