package question;

public class Q03 {
	public static void main(String[] args) {
		Q03 q = new Q03();
		int a = 20; // a = 20
		int[] b = {100, 200, 300}; // b[0] = 100, b[1] = 200, b[2] = 300
		// 메서드 호출
		q.change(a,b);
		q.display(a,b);
		
	}
	void change(int a, int[] b) {
		a = a + 10; // 20 + 10 = 30
		b[1] = b[1] + 10; // 200 + 10 = 210
	}
	
	void display(int a, int b[]) {
		System.out.print(a + "/" + b[1]); // 출력결과 20/210
	}
	/*
	change 메서드에서 main으로 부터 넘겨받은 a, b 값을 변경했지만
	출력해보면 a의 값은 변경되지 않은 채 출력됨
	
	'a'의 경우 기본형 매개변수라서 int a 의 값이 변경된것이 아니라 
	a의 값이 30 으로 변경된것 즉, 원본엔 영향을 미치지 못함 -> 기본형 매개변수는 읽어 오는것만 가능하며 변경은 불가능
	
	'b'의 경우 참조형 매개변수라서(배열 변수 = 참조형) 값이 아니라 값이 저장된 주소를 change 메서드에게
	넘겨주었기 때문에 값을 읽어오고, 변경하는것이 가능 -> 참조형 매개변수는 읽어오고 변경까지 가능
	따라서 20 / 210 출력
	*/
}
