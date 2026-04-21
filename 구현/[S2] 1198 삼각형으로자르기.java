import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1198_삼각형으로자르기 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int pointCount;
	static Point[] points;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxSize());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		pointCount = Integer.parseInt(br.readLine());
		points = new Point[pointCount];
		for (int point = 0; point < pointCount; point++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			points[point] = new Point(x, y);
		}
	}

	static double getMaxSize() {
		long maxDoubleArea = 0;

		for (int i = 0; i < pointCount - 2; i++) {
			for (int j = i + 1; j < pointCount - 1; j++) {
				for (int k = j + 1; k < pointCount; k++) {
					long doubleArea = Math.abs(crossProduct(points[i], points[j], points[k]));
					maxDoubleArea = Math.max(maxDoubleArea, doubleArea);
				}
			}
		}

		return maxDoubleArea / 2.0;
	}

	static long crossProduct(Point a, Point b, Point c) {
		return (long)(b.x - a.x) * (c.y - a.y) - (long)(b.y - a.y) * (c.x - a.x);
	}
}
