import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/* 2020 Naver 개발자 채용 코딩테스트 1번
 *  * 다음과 같이 간단한 기능을 하는 메일 보관함 프로그램을 작성하려 합니다.

명령어	동작
RECEIVE [메일 주소]	[메일 주소]를 임시 보관함에 저장합니다.
DELETE	임시 보관함에서 가장 최근에 저장한 [메일 주소] 하나를 삭제합니다.
SAVE	임시 보관함에 있는 모든 [메일 주소]를 영구 보관함에 저장하고, 임시 보관함을 비웁니다.
이때, SAVE 명령어는 [메일 주소]가 임시 보관함에 저장되어 있던 순서대로 영구 보관함에 저장합니다. 이때, 영구 보관함에 먼저 저장된 [메일 주소]가 있다면 그 뒤에 이어서 순서대로 저장하면 됩니다. 또, DELETE와 SAVE는 임시 보관함에 [메일 주소]가 없는 경우 아무런 동작도 하지 않습니다.

프로그램에서 사용된 명령어들이 문자열 형태로 담긴 배열 record가 매개변수로 주어질 때, 모든 명령어를 수행 후 영구 보관함에 저장된 메일 주소를 순서대로 배열에 담아 return 하도록 solution 함수를 완성해주세요.

제한사항
record의 길이는 1 이상 10,000 이하입니다.
record의 각 원소는 "RECEIVE [메일 주소]", "DELETE", "SAVE" 세 가지 형태중 하나입니다.
RECEIVE와 [메일 주소]는 공백(스페이스바) 하나로 구분되어 있습니다.
[메일 주소]는 "아이디@naver.com" 형식의 문자열이며, 아이디는 대, 소문자와 숫자로 구성되어 있습니다.
아이디의 길이는 1 이상 10 이하이며, 대, 소문자를 구분합니다.
[메일 주소]는 중복되어 주어지지 않습니다.
return 하는 배열의 길이가 1 이상인 경우만 입력으로 주어집니다.
 * */
public class Main {

	public static void main(String[] args) {
//		String[] record = {"RECEIVE abcd@naver.com", "RECEIVE zzkn@naver.com", "DELETE", "RECEIVE qwerty@naver.com", "SAVE", "RECEIVE QwerTY@naver.com"};
//		//["abcd@naver.com", "qwerty@naver.com"]

		String[] record = {"RECEIVE abcd@naver.com", "RECEIVE zzkn@naver.com", "DELETE", "RECEIVE qwerty@naver.com", "SAVE", "SAVE", "DELETE", "RECEIVE QwerTY@naver.com", "SAVE"};
//		["abcd@naver.com", "qwerty@naver.com", "QwerTY@naver.com"]


		
		System.out.println(Arrays.toString(new Solution().solution(record)));
		
	}

}

class Solution {
	public String[] solution(String[] record) {
		String[] answer = {};
		List<String> permanentStorage = new LinkedList<>();
		Stack<String> temporaryStorage = new Stack<>();
		
		
		// 1. 받은 메일 Parsing
		for (int i = 0; i < record.length; i++) {
			String[] commands = record[i].split(" ");
			
			String command = commands[0];
			if (command.equals("RECEIVE")) {
				String address = commands[1];
				temporaryStorage.push(address); // 임시 저장함에 저장
			
			} else if (command.equals("SAVE")) { // 영구 보관함 저장
				Collections.reverse(temporaryStorage);
//				System.out.println(temporaryStorage);
				while (!temporaryStorage.isEmpty()) {
					permanentStorage.add(temporaryStorage.pop());
				}
				
			} else if (command.equals("DELETE")) { // 임시 보관함의 가장 최근꺼 삭제, stack pop
				if (!temporaryStorage.isEmpty())
					temporaryStorage.pop();
			}
		}
//		System.out.println(permanentStorage);
		answer = new String[permanentStorage.size()];
		for (int i = 0; i < permanentStorage.size(); i++) {
			answer[i] = permanentStorage.get(i);
		}
		
		return answer;
		
	}
}