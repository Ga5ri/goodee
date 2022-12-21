package question;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
// 다음 코드를 실행시 예상되는 출력결과는?
public class Q06 {

	public static void main(String[] args) {
		Set<Singer> set = new HashSet<>();
		// set -> 중복값 삽입 불가, 특정한 순서 없음 -> 배열, List처럼 get(index) 사용해서 값을 가져올수 없음
		// HashSet 사용해서 저장 -> Hash알고리즘 사용하여 검색-> 중복값 제거
		Singer singer;
		
		singer = new Singer();
		
		singer.setName("admin");
		singer.setAge(30);
		set.add(singer);
		// Singer singer2 = new Singer(); -> singer2 변수 사용시 guest, admin 출력
		singer.setName("guest");
		singer.setAge(32);
		set.add(singer);
		/*
			값은 다르나 singer 변수가 같음으로 admin 에 guest 덮어씌워짐 
			출력 결과: guest
		 */
		// 이트레이트 패턴 : 순서가 없는 자료구조 일시적으로 순서가 있는것처럼
		Iterator<Singer> it = set.iterator(); // set을 Iterator 안에 담기
		while(it.hasNext()) { // Iterator 다음값이 있다면 true
			System.out.println(it.next().getName()); // iterator에서 값 꺼내서 출력
		}
	}

}

class Singer {
	private String name;
	private int age;
	public String getName()	{
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge()	{
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}