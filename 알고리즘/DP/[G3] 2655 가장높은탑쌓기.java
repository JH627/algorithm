import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 밑면의 넓이가 내림차순이 되도록 벽돌을 정렬
 * 
 * 2. 정렬 후 최대 높이 계산
 * 	 2-1. 앞에서부터 벽돌을 하나씩 고르고
 * 		2-1-1. 그보다 앞에 있는 벽돌을 하나씩 확인
 * 			2-1-1-1. 만약 해당 벽돌의 무게가 (2-1)에서 고른 벽돌보다 무거운 경우
 * 				2-1-1-1-1. 해당 벽돌 위에 (2-1)에서 골랐던 벽돌을 올려보고 최대 높이를 갱신한다.
 * 
 * 3. 타워에 속한 블럭들을 확인
 * 	3-1. 내림차순으로 벽돌을 정렬했으므로 끝에서부터 확인
 * 		3-1-1. 만약 타워 높이와 해당 벽돌을 사용했을 때 최대 높이가 같은 경우
 * 			3-1-1-1. 해당 벽돌의 id를 리스트에 추가하고
 * 			3-1-1-2. 다음 벽돌의 최대 높이는 현재 최대높이에서 방금 리스트에 추가한 벽돌의 높이를 뺀 높이여야하므로
 * 			3-1-1-3. 찾아야하는 타워의 높이를 변경
 *
 */
public class BOJ_2655_가장높은탑쌓기 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Block implements Comparable<Block> {
		int id;
		int area, height, weight;

		public Block(int id, int area, int height, int weight) {
			this.id = id;
			this.area = area;
			this.height = height;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Block o) {
			return o.area - this.area;
		}
	}
	
	static int blockCount;
	static Block[] blocks;
	static int[] maxHeight;
	
	public static void main(String[] args) throws Exception {
		init();
		
		int max = makeTower();
		
		findBlocksInTower(max);
	}
	
	
	static int makeTower() {
		int max = maxHeight[0];
		
		for (int now = 0; now < blockCount; now++) {
			for (int prev = 0; prev < now; prev++) {
				// 2-1-1-1. 만약 해당 벽돌의 무게가 (2-1)에서 고른 벽돌보다 무거운 경우
				if (blocks[now].weight < blocks[prev].weight) {
					// 2-1-1-1-1. 해당 벽돌 위에 (2-1)에서 골랐던 벽돌을 올려보고 최대 높이를 갱신한다.
					maxHeight[now] = Math.max(maxHeight[now], maxHeight[prev] + blocks[now].height);
				}
			}
			// 타워 최대 높이 갱신
			max = Math.max(max, maxHeight[now]);
		}
		
		return max;
	}
	
	static void findBlocksInTower(int max) {
		ArrayList<Block> list = new ArrayList<>();
		
		int curMax = max;
		for (int blockIndex = blockCount - 1; blockIndex >= 0; blockIndex--) {
			// 3-1-1. 만약 타워 높이와 해당 벽돌을 사용했을 때 최대 높이가 같은 경우
			if (maxHeight[blockIndex] == curMax) {
				// 3-1-1-1. 해당 벽돌의 id를 리스트에 추가하고
				list.add(blocks[blockIndex]);
				 // 3-1-1-2. 다음 벽돌의 최대 높이는 현재 최대높이에서 방금 리스트에 추가한 벽돌의 높이를 뺀 높이여야하므로
				 // 3-1-1-3. 찾아야하는 타워의 높이를 변경
				curMax -= blocks[blockIndex].height;
			}
		}
		
		sb.append(list.size()).append("\n");
		
		for (Block block : list) {
			sb.append(block.id).append("\n");
		}
		
		System.out.print(sb);
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		blockCount = Integer.parseInt(br.readLine());
		blocks = new Block[blockCount];
		for (int blockIndex = 0; blockIndex < blockCount; blockIndex++) {
			st = new StringTokenizer(br.readLine());
			int area = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			blocks[blockIndex] = new Block(blockIndex + 1, area, height, weight);
		}

		Arrays.sort(blocks);
		
		maxHeight = new int[blockCount];
		for (int blockIndex = 0; blockIndex < blockCount; blockIndex++) {
			maxHeight[blockIndex] = blocks[blockIndex].height;
		}
	}
}
