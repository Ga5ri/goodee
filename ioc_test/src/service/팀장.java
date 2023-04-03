package service;

import repository.*;
import service.*;


public class 팀장 {
	// @Autowired DI -> IOC 기법 중 하나로 객체에 주입해주는 기법
	// AOP -> 필터링해주는것, 매서드 가로채기 비슷한거 필터 필터는 요청 가로채기
	
	학생 길동;
	
	public void 감독하다() {		
		길동.공부하다();
	}

	// 컴파일은 진행되지만 널포인트익셉션 에러 발생
}
