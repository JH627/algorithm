import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10775_공항 {
	
	static BufferedReader br;
	
	static int gateCount, planeCount;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.print(getMaxPlaneCount());
	}
	
	static int getMaxPlaneCount() throws Exception {
		int count = 0;
		for (int planeIndex = 1; planeIndex <= planeCount; planeIndex++) {
			int maxGateNumber = Integer.parseInt(br.readLine());
			int findParent = find(maxGateNumber);
			if (findParent == 0) {
				break;
			}
			count++;
			parent[findParent] = findParent - 1;
		}
		return count;
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		gateCount = Integer.parseInt(br.readLine());
		planeCount = Integer.parseInt(br.readLine());
		
		parent = new int[gateCount + 1];
		for (int index = 1; index < gateCount + 1; index++) {
			parent[index] = index;
		}
	}
}
