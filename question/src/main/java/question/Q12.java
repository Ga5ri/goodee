package question;

import java.util.*;

public class Q12 {

	public static void main(String[] args) {
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(1);
		
		String s1 = new String("one");
		String s2 = new String("one");
		
		StringBuffer sf1 = new StringBuffer("one");
		StringBuffer sf2 = new StringBuffer("one");
		
		Date d1 = new Date();
		Date d2 = new GregorianCalendar(2011, 7, 15).getTime();
		Date d3 = new GregorianCalendar(2011, 7, 15).getTime();
		Date d4 = new Date();
		
		// equals 메서드는 객체끼리의 내용 비교
		// Integer 객체값 1로 동일 => true
		System.out.println(i1.equals(i2));
		
		// String 문자열 one 동일 => true
		System.out.println(s1.equals(s2));
		
		// StringBuffer내 실제 문자열이 아닌 개체에 대한 참조값 비교 => false
		System.out.println(sf1.equals(sf2));
		
		// 시간대가 동일하게 생성 2011.07.15 00:00:00 => true
		System.out.println(d2.equals(d3));
		
		// 밀리초 단위로 선언되는 시간에 차이 => false
		System.out.println(d1.equals(d4));
	}

}
