import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_21939_문제추천시스템Version1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Problem implements Comparable<Problem>{
		int num, difficult;
		
		public Problem(int num, int difficult) {
			this.num = num;
			this.difficult = difficult;
		}

		@Override
		public int compareTo(Problem o) {
			if (this.difficult == o.difficult) {
				return this.num - o.num;
			}
			return this.difficult - o.difficult;
		}
	}
	
	static int problemCount, queryCount;
	static int[] diff;
	static TreeSet<Problem> set;
	
	public static void main(String[] args) throws IOException {
		init();
		
		useQuery();
	
		System.out.print(sb);
	}
	
	static void useQuery() throws IOException {
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			
			String command = st.nextToken();
			
			switch (command) {
				case "recommend":
					int x = Integer.parseInt(st.nextToken());
					if (x == 1) {
						sb.append(set.last().num);
					}
					else {
						sb.append(set.first().num);
					}
					sb.append("\n");
					break;
				case "add":
					int p = Integer.parseInt(st.nextToken());
					int l = Integer.parseInt(st.nextToken());
					
					set.add(new Problem(p, l));
					diff[p] = l;
					break;
					
				case "solved":
					int pn = Integer.parseInt(st.nextToken());
					set.remove(new Problem(pn, diff[pn]));
					break;
			}
		}
	}
	
	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		problemCount = Integer.parseInt(br.readLine());
		
		set = new TreeSet<>();
		diff = new int[100001];
		
		for (int problemIndex = 0; problemIndex < problemCount; problemIndex++) {
			st = new StringTokenizer(br.readLine());
			int problemNumber = Integer.parseInt(st.nextToken());
			int difficult = Integer.parseInt(st.nextToken());
			set.add(new Problem(problemNumber, difficult));
			diff[problemNumber] = difficult;
		}
		
		queryCount = Integer.parseInt(br.readLine());
	}
	

}
