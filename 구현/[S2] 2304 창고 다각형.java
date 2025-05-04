import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2304_창고다각형 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Block implements Comparable<Block> {
		int position, height;

		public Block(int position, int height) {
			this.position = position;
			this.height = height;
		}

		@Override
		public int compareTo(Block o) {
			return this.position - o.position;
		}
	}

	static int maxHeight;
	static int blockCount;
	static Block[] blocks;

	public static void main(String[] args) throws Exception {
		init();

		System.out.print(getSize());
	}

	static int getSize() {
		if (blockCount == 1) {
			return maxHeight;
		}

		int area = 0;

		int left, right;
		int tempMaxHeight = blocks[0].height;
		for (left = 1; left < blockCount; left++) {
			area += (blocks[left].position - blocks[left - 1].position) * tempMaxHeight;
			if (blocks[left].height > tempMaxHeight) {
				tempMaxHeight = blocks[left].height;
			}
			if (tempMaxHeight == maxHeight) {
				break;
			}
		}

		tempMaxHeight = blocks[blockCount - 1].height;
		for (right = blockCount - 2; right >= 0; right--) {
			area += (blocks[right + 1].position - blocks[right].position) * tempMaxHeight;
			if (blocks[right].height > tempMaxHeight) {
				tempMaxHeight = blocks[right].height;
			}
			if (tempMaxHeight == maxHeight) {
				break;
			}
		}

		area += (blocks[right].position - blocks[left].position + 1) * maxHeight;

		return area;
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		blockCount = Integer.parseInt(br.readLine());

		maxHeight = 0;
		blocks = new Block[blockCount];
		for (int blockIndex = 0; blockIndex < blockCount; blockIndex++) {
			st = new StringTokenizer(br.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			blocks[blockIndex] = new Block(pos, height);
			maxHeight = Math.max(maxHeight, height);
		}

		Arrays.sort(blocks);
	}

}
