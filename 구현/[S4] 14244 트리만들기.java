import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14244_트리만들기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int nodeCount, leafNodeCount;

	public static void main(String[] args) throws IOException {
		init();
		findTree();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		leafNodeCount = Integer.parseInt(st.nextToken());
	}

	static void findTree() {
		sb = new StringBuilder();

		for (int nodeIndex = 0; nodeIndex < nodeCount - leafNodeCount; nodeIndex++) {
			sb.append(nodeIndex).append(" ").append(nodeIndex + 1).append("\n");
		}
		
		for (int nodeIndex = nodeCount - leafNodeCount + 1; nodeIndex < nodeCount; nodeIndex++) {
			sb.append((nodeCount - leafNodeCount)).append(" ").append(nodeIndex).append("\n");
		}

		System.out.print(sb);
	}

}
