import java.util.*;
import java.io.*;

public class BOJ_20044_ProjectTeams {
    
    static BufferedReader br;
	static StringTokenizer st;
    
    static int teamCount;
    static int[] member;
    
    public static void main(String[] args) throws Exception {
        init();
        System.out.print(findMin());
    }
    
    static int findMin() {
        int min = 2000000;
        for(int team = 0; team < teamCount; team++) {
            int teamScore = member[team] + member[teamCount * 2 - 1 - i];
            if (teamScore < min) {
                min = teamScore;
            }
        }
        
        return min;
    }
    
    static void init() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        teamCount = Integer.parseInt(br.readLine());
        member = new int[teamCount * 2];
        st = new StringTokenizer(br.readLine());
        for(int team = 0; team < teamCount * 2; team++) {
            member[team] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(member);
    }
}
