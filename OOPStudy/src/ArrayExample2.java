/**
 * 다차원 배열 테스트
 * 
 * @author 이철우
 *
 */
public class ArrayExample2 {

	public static void main(String[] args) {
		char[][] array;
		array = new char[10][10];
		array[0][0] = 'A';
		array[9][9] = 'Z';

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}
		// 배열의 값을 초기화 할 때는 [] 사이에 값을 넣지 않는다. 시험문제
		// 밑이 기본
//		int[][] array2 = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

		// 밑은 간략화된 초기화
		int[][] array2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		int[][] gugudan = new int[8][9];
		for (int i = 0; i < gugudan.length; i++) {
			for (int j = 0; j < gugudan[i].length; j++) {
				gugudan[i][j] = (i + 2) * (j + 1);
				System.out.print((i + 2) + " * " + (j + 1) + " = " + gugudan[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
