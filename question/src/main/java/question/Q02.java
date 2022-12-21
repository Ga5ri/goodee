package question;

public class Q02 {
	public static int[] arNum = {7, 9, 1, 4, 5};
	
	public static void main(String[] args) {
		int out = 0;
		int in = 0;
		
		Q02 t = new Q02();
		
		for(out=arNum.length-1; out>0; out--) {
			for (in =0; in<out; in++) {
				if(arNum[in] > arNum[in+1]) { // 왼쪽 값이 오른쪽 값보다 크면 swap
					t.swap(in, in + 1);
					// 79145 -> 71945 -> 71495 -> 71459 ... -> 14759 -> 14579
				}
			}
		}
		t.display();
	}
	
	// 자리 변경(swap) 메서드
	public void swap(int one, int two) {
		int temp = arNum[one]; 
		arNum[one] = arNum[two]; 
		arNum[two] = temp; 
	}
	
	// 배열 출력 메서드
	public void display() {
		for(int index=0; index<arNum.length; index++) {
			System.out.print(arNum[index]);
		}
	}
}
