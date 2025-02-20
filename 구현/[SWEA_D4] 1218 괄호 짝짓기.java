import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 스택 활용 문제
 * 
 * 1. 스택을 하나 선언하고 검사할 문자들 중 맨 처음에 해당하는 문자를 스택에 push한다.
 * 2. 두 번째 문자부터 하나씩 스택에 넣으며 검사한다.
 * 2-1. 만약 열린 괄호 ('{', '[', '(', '<')가 나온 경우 스택에 push한다
 * 2-2. 만약 닫힌 괄호 ('}', ']', ')', '>')가 나온 경우
 * 2-2-1. 스택이 비어있는 경우 유효하지 않은 문자열이므로 즉시 종료
 * 2-2-2. 스택이 비어있진 않지만 가장 상단에 있는 요소와 현재 넣으려는 괄호의 종류가 안맞는 경우 => 유효하지 않은 상태 반환
 * 2-2-3. 스택이 비어있진 않지만 가장 상단에 있는 요소와 현재 넣으려는 괄호의 종류가 맞는 경우 => 가장 상단에 있는 요소 pop
 * 2-3. 모든 문자를 확인 한 후 스택에 요소가 하나 이상 있는 경우
 * 		=> 정상적으로 짝이 맞추어지지 않음 => 유효하지 않은 상태 반환
 * 2-3-1. 요소가 하나도 없는 경우
 * 		=> 문자열에 있는 모든 괄호의 짝이 맞추어짐 => 유효한 상태 반환
 *
 */
public class SWEA_1218_괄호짝짓기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static final int TC = 10;
	static final Character[] openCharacter = {'{', '[', '(', '<'}; // 열림 담당 괄호
    static char[] parenthesisList; // 검사할 문자열 배열
    static int parenthesisLength; // 검사할 문자열의 길이
	
	public static void main(String[] args) throws Exception {	
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			sb.append("#").append(testCase).append(" ").append(checkIsRightString()).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 올바른 문자열인지 검사하는 함수
	static int checkIsRightString() {
		Stack<Character> parentheses = new Stack<>();
		
		// 맨 앞 요소가 닫힌 괄호인 경우
		// 모든 경우에서 유효한 문자열이 나올 수 없으므로 조기 종료
		if (getPatternNumber(parenthesisList[0]) != -1) {
			return 0;
		}
		// 스택 선언 후 맨 앞 문자하나 push
		parentheses.add(parenthesisList[0]);
		// 문자를 하나씩 검사한다.
		for (int index = 1; index < parenthesisLength; index++) {
			char singleParenthesis = parenthesisList[index];
			
			// 문자의 패턴(열린 괄호라면 어느 괄호인지, 또는 닫힌괄호인지) 확인
			int pattern = getPatternNumber(singleParenthesis);
					
			// 닫힌 괄호인 경우
			if (pattern != -1) {
				// 만약 스택이 비어있거나 맨위 괄호가 현재 닫힘 괄호와 안맞는 경우
				if (parentheses.isEmpty() || parentheses.pop() != openCharacter[pattern]) {
					// 유효하지 않음 반환
					return 0;
				}
			}
			// 열린 괄호인 경우
			else {
				// 스택에 추가
				parentheses.add(singleParenthesis);
			}
		}
		
		// 문자열 검사가 모두 끝났는데 스택에 요소가 남아있는 경우 유효하지 않음 반환
		return parentheses.isEmpty() ? 1 : 0;
	}

	// 패턴 확인
	static int getPatternNumber(char singleParenthesis) {
		// 기본 값은 열린 괄호
		int pattern = -1;
		if (singleParenthesis == '}') {
			pattern = 0;
		}
		else if (singleParenthesis == ']') {
			pattern = 1;
		}
		else if (singleParenthesis == ')') {
			pattern = 2;
		}
		else if (singleParenthesis == '>') {
			pattern = 3;
		}
		return pattern;
	}
	
    static void init() throws Exception {
        parenthesisLength = Integer.parseInt(br.readLine());
        parenthesisList = br.readLine().toCharArray();
    }

}
