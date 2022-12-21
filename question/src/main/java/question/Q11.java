package question;

import java.util.*;


public class Q11 {

	public static void main(String[] args) {
		String[] arr = {"1", "2", "9", "7", "4", "6", "1", "7", "0"};
		// set -> 중복값 삽입 불가, 특정한 순서 없음 -> 배열, List처럼 get(index) 사용해서 값을 가져올수 없음
		// HashSet 사용해서 저장 -> Hash알고리즘 사용하여 검색-> 중복값 제거
		Set set = new HashSet();
		for(String s : arr) { 
			set.add(s); // 중복값 제외하고 1297460
		}
		
		// Collection 사용시 -> LinkedList
		// set -> list
		List list = new LinkedList(set);
		// sort() = 오름차순, reverse() = 내림차순
		Collections.sort(list); // 0124679 오름차순 정렬
		
		Iterator it = list.iterator(); // 이트레이트 패턴 : 순서가 없는 자료구조 일시적으로 순서가 있는것처럼
		
		while(it.hasNext()) { // Iterator 다음값이 있다면 true
			System.out.print(it.next());
		}
	}

}
