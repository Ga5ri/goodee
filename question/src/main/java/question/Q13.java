package question;

import java.util.*;


public class Q13 {

	public static void main(String[] args) {
		// Stack 자료구조: 데이터를 쌓는 형태, 나중에 쌓인 데이터가 먼저 나온다. 
		// push->데이터를 추가하는 동작, pop->데이터를 빼는 동작
		// 012 순으로 쌓이고 210 으로 빠진다
		Stack st= new Stack();
		
		// Queue 자료구조: 데이터를 줄세우는 형태, 데이터가 삽입된 순서대로 나온다.
		// add,offer-> 값 추가(add의 경우 삽입 성공 true, 여유공간 부족으로 실패하면 에러)
		// poll-> 첫 값을 반환->제거->비어있으면 null
		// 012 -> 012
		Queue q = new LinkedList();
		
		st.push("0");
		st.push("1");
		st.push("2");
		
		q.offer("0");
		q.offer("1");
		q.offer("2");
		
		while(!st.empty()) { // st 값이 비어있지 않으면
			System.out.print(st.pop()); // 210
		}
		System.out.print("/");
		while(!q.isEmpty()) { // q가 비어있지 않으면
			System.out.print(q.poll()); // 012
		}
		// 출력 결과 210/012 출력
	}

}
