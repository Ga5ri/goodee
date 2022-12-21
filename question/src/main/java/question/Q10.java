package question;

public class Q10 {

	public static void main(String[] args) {
		int x[][] = { {3, 2, 3}, {5, 9, 8} };
		// 3 2 3
		// 5 9 8 
		int y[][] = { {4, 7}, {9, 3}, {8, 1} };
		// 4 7
		// 9 3
		// 8 1
		int z[][] = Matrix.multiply(x, y);
		
		Matrix.print(z);

	}

}

class Matrix {
	public static int[][] multiply(int[][] m1, int[][] m2) {
		int m1Rows = m1.length; // 2행
		int m1Cols = m1[0].length; // 3열
		int m2Rows = m2.length; // 3행
		int m2Cols = m2[0].length; // 2열
		
		if(m1Cols != m2Rows) { // 행렬 곱셈 공식: 2x'3' * '3'x2 곱하고자 하는 m1의 '열'과 m2의 '행' 숫자가 다르면 곱셈 X -> 런타임 에러 IllegalArgumentException()
			throw new IllegalArgumentException();
		}
		
		// 곱셈 결과 행렬의 길이 -> 2행 2열
		int[][] result = new int[m1Rows][m2Cols];
		
		// 행렬 m1 * m2 결과를 result에 저장
		
		for(int i=0; i<m1Rows; i++) { // 0~1
			for(int j=0; j<m2Cols; j++) { // 0~1
				for(int k=0; k<m1Cols; k++) { // 0~2
					result[i][j] += m1[i][k] * m2[k][j];
					/*
					 * |3, 2, 3|   |4, 7|	  (3*4=12)+(2*9=18)+(3*8=24), (3*7=21)+(2*3=6)+(3*1=3)   | 54, 30 |
					 * |5, 9, 8| * |9, 3| =   (5*4=20)+(9*9=81)+(8*8=64), (5*7=35)+(9*3=27)+(8*1=8)  |165, 70 |						                            |
					 *             |8, 1|  
					 */
				}
			}
		}
		return result;
	}
	
	// 행렬 결과 출력
	public static void print(int[][] a) {
		int rows = a.length;
		int cols = a[0].length;
		for(int i=0; i<rows; i++) {
			System.out.print("{");
			for(int j=0; j<cols; j++) {
				System.out.print(a[i][j] + ",");
			}
			System.out.println("}");
		}
	}
}