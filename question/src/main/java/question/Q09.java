package question;

public class Q09 {

	public static void main(String[] args) {
		// final 변수는 한번 값을 할당 시 수정할 수 없다(초기화 한 번만 가능)
		// 일~토요일 변수 선언 : 1~7
		final int SUN = 1;
		final int MON = 2;
		final int TUE = 3;
		final int WED = 4;
		final int THU = 5;
		final int FRI = 6;
		final int SAT = 7;
		
		// 시작, 끝, 시작주의 첫 요일 선언
		int start = 1;
		int end = 31;
		int startWeek = THU;
		System.out.println(" SU MO TU WE TH FR SA"); // 요일 출력
		for(int i=1; i<startWeek; i++) { // SUN 부터 THU 전까지 공백 출력 1~4
			System.out.print("   "); 
		}
		for(int i=start, n=startWeek; i<=end; i++, n++) { // i = 1부터 31까지, n은 5부터
			// 삼항 연산자 => (조건식) a : b -> 괄호안 조건이 참일경우 a 실행 거짓일경우 b 실행
			System.out.print((i<10)?" 0" + i : " " + i); // i가 10 미만일경우 공백+0 붙여서 출력 10부터는 공백만 붙여서 출력
			if(n % 7 == 0) // 7로 나눈 나머지가 0일경우 줄바꿈(println)
				System.out.println();
		}
	}

}
