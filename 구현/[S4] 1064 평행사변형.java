import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1064_평행사변형 {

	static BufferedReader br;
	static StringTokenizer st;

	static int xa, ya, xb, yb, xc, yc;

	public static void main(String[] args) throws IOException {
		init();
		System.out.printf("%.9f", getDiff());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		xa = Integer.parseInt(st.nextToken());
		ya = Integer.parseInt(st.nextToken());
		xb = Integer.parseInt(st.nextToken());
		yb = Integer.parseInt(st.nextToken());
		xc = Integer.parseInt(st.nextToken());
		yc = Integer.parseInt(st.nextToken());
	}

	static double getDiff() {
		// 점 세개가 직선에 있는지 확인
		if ((xb - xa) * (yc - ya) == (yb - ya) * (xc - xa)) {
			return -1;
		}
		
		// 삼각형 점이 3개이니 두점씩 선택해서 총 3가지 종류의 평행사변형 둘레 구하기
		// AB + AC
		double abac = 2 * (getDistance(xa, ya, xb, yb) + getDistance(xa, ya, xc, yc));
		// AB + BC
		double abbc = 2 * (getDistance(xa, ya, xb, yb) + getDistance(xb, yb, xc, yc));
		// AC + BC
		double acbc = 2 * (getDistance(xa, ya, xc, yc) + getDistance(xb, yb, xc, yc));

		double min = Math.min(abac, Math.min(abbc, acbc));
		double max = Math.max(abac, Math.max(abbc, acbc));

		return max - min;
	}

	static double getDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

}
