import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 모든 암호 조합을 살펴보기
 * 2. 만약 암호의 길이 제한에 도달 했다면 모음과 자음 개수가 주어진 조건에 부합하는지 확인
 * 3. 만약 조건에 맞는다면 출력
 *
 */
public class BOJ_1759_암호만들기 {

	static BufferedReader br;
	static StringBuilder sb;
	 
	static final char[] VOWELS = new char[]{'a', 'e', 'i', 'o', 'u'};
	
    static int selectCount, elementCount;
    static Character[] characters;
    static boolean[] isVowel;

    public static void main(String[] args) throws Exception {
    	init();

        makePassword(0, 0, 0, 0, "");

        System.out.print(sb);
    }

    static void makePassword(int elementIndex, int selectedIndex, int vowels, int consonants, String s) {
    	// 만약 문자를 다 골랐고
        if (selectedIndex == selectCount) {
        	// 조건에 맞다면 출력
            if (vowels >= 1 && consonants >= 2) {
                sb.append(s).append("\n");
            }
            return;
        }

        // 암호를 완성할 수 있는 최대 인덱스까지 문자를 하나씩 가지고 분기
        // 분기 시에는 모음인지 자음인지 카운팅하여 분기한다.
        for (int index = elementIndex; index <= elementCount - selectCount + selectedIndex; index++) {
        	makePassword(index + 1, selectedIndex + 1, 
        			(isVowel[index]) ? vowels + 1 : vowels, 
        			(isVowel[index]) ? consonants : consonants + 1, s + characters[index]);
        }
    }
    
    static void init() throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	sb = new StringBuilder();
    	
        StringTokenizer st = new StringTokenizer(br.readLine());
        selectCount = Integer.parseInt(st.nextToken());
        elementCount = Integer.parseInt(st.nextToken());
        
        characters = new Character[elementCount];
        
        st = new StringTokenizer(br.readLine());
        for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
        	characters[elementIndex] = st.nextToken().charAt(0);
        }

        // 사전식으로 출력하기 위하여 정렬
        Arrays.sort(characters);

        // 모음인지 확인
        isVowel = new boolean[elementCount];
        for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
        	for (int index = 0; index < 5; index++) {
        		if (characters[elementIndex] == VOWELS[index]) {
        			isVowel[elementIndex] = true;
        			break;
        		}
        	}
        }
    }
}
