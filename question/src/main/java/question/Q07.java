package question;
//다음 코드를 실행시 예상되는 출력결과는?
public class Q07 {
	private static int result = 0;
	private static boolean flag = false;
	
	public static void main(String[] args) {
		Q07.setResult(10);
		Q07.display();
	}
	
	public static void setResult(int x) {
		Q07.flag = Q07.method1(x) || Q07.method2(); // &&의 경우?
	}
	
	public static boolean method1(int x) {
		System.out.println("method1()실행");
		boolean temp = false;
		if(x>0) {
			Q07.result = x;
			temp = true;
		}
		return temp;
	}
	
	public static boolean method2() {
		System.out.println("method2()실행");
		Q07.result = Q07.result * 10;
		return true;
	}
	
	public static void display() {
		System.out.print(Q07.result+" ");
		System.out.println(Q07.flag);
	}
}
