package repository;

import service.팀장;

public class asf {
	public static void main(String[] args) {
		팀장 t = new 팀장();
		
		// 직접하지 않고 프레임웤 기능을 사용
		// t.길동 = new 홍길동(); // DI
		
		t.감독하다();
	}
}
