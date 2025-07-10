import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2166_다각형의면적 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int pointCount;
	static long[] x, y;
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.printf("%.1f", getSize());
	}
	
	static double getSize() {
		double size = 0;
		for (int point = 0; point < pointCount; point++) {
			size += x[point] * y[(point + 1) % pointCount];
			size -= x[point] * y[(point + pointCount - 1) % pointCount];
		}
		size = Math.abs(size) * 0.5;
		return size;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		pointCount = Integer.parseInt(br.readLine());
		
		x = new long[pointCount];
		y = new long[pointCount];
		for (int point = 0; point < pointCount; point++) {
			st = new StringTokenizer(br.readLine());
			x[point] = Long.parseLong(st.nextToken());
			y[point] = Long.parseLong(st.nextToken());
		}
	}
}
