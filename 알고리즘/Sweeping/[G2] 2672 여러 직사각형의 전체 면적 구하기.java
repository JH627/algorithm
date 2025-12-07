import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2672_여러직사각형의전체면적구하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Rectangle {
		double x1, y1, x2, y2;

		public Rectangle(double x1, double y1, double x2, double y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	static int rectangleCount;
	static Rectangle[] rectangles;
	static double[] x, y;

	public static void main(String[] args) throws IOException {
		init();
		double size = getAreaSize();
		if ((int)size == size) {
			System.out.print((int) size);
		}
		else {
			System.out.printf("%.2f", getAreaSize());
		}
	}

	static double getAreaSize() {
		Arrays.sort(x);
		Arrays.sort(y);

		double size = 0;
		for (int i = 0; i < 2 * rectangleCount - 1; i++) {
			for (int j = 0; j < 2 * rectangleCount - 1; j++) {
				for (int k = 0; k < rectangleCount; k++) {
					if (x[i] >= rectangles[k].x1 && x[i + 1] <= rectangles[k].x2 &&
						y[j] >= rectangles[k].y1 && y[j + 1] <= rectangles[k].y2) {
						size += (x[i + 1] - x[i]) * (y[j + 1] - y[j]);
						break;
					}
				}
			}
		}

		return size;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		rectangleCount = Integer.parseInt(br.readLine());
		rectangles = new Rectangle[rectangleCount];
		x = new double[2 * rectangleCount];
		y = new double[2 * rectangleCount];
		for (int rectangle = 0; rectangle < rectangleCount; rectangle++) {
			st = new StringTokenizer(br.readLine());

			double x1 = Double.parseDouble(st.nextToken());
			double y1 = Double.parseDouble(st.nextToken());
			double width = Double.parseDouble(st.nextToken());
			double height = Double.parseDouble(st.nextToken());

			rectangles[rectangle] = new Rectangle(x1, y1, x1 + width, y1 + height);
			x[2 * rectangle] = x1;
			y[2 * rectangle] = y1;
			x[2 * rectangle + 1] = x1 + width;
			y[2 * rectangle + 1] = y1 + height;
		}
	}
}
