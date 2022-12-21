package question;
//캡슐화 - Player 클래스의 장점을 캡슐화 관점에서 설명하시오.
public class Q04 {
	public static void main(String[] args) {
		Player luffy = new Player();
		Player zoro = new Player();
		Player nami = new Player();
		
		System.out.println(luffy.getId());
		System.out.println(zoro.getId());
		System.out.println(nami.getId());
	}
}

class Player { // 캡슐화 - 객체의 속성과 행위를 묶고 구현 내용은 외부에 감춤
	private static int nextId = 1; // 1) Why private? static?
	// private - 현재 클래스 내부에서만 사용할 것이고 외부에서는 사용하지 못함
	// static - 객체 생성없이 클래스명으로 바로 접근 가능
	private int id; // 2) Why private?
	public Player() { // 3) Why public?
		this.id = Player.nextId++;
	}
	
	protected int getId() { // 4) Why protected?
		return id;
	}
	
	private void setId(int id) { // 5) Why private?
		this.id = id;
	}
}
