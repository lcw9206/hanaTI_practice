/**
 * 1차원 배열 선언, 생성, 초기화
 * 
 * @author 이철우
 *
 */
public class ArrayExample {

	public static void main(String[] args) {
		int[] array;
		array = new int[10];
		array[0] = 10;
		array[9] = 100;
		/*
		 * System.out.println(array[0]); System.out.println(array[9]);
		 */
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i] + "\t");
		}

		// int[] array2 = new int[] {1, 2, 3, 4, 5}; 아래와 동일
		int[] array2 = { 1, 2, 3, 4, 5 };

		// 기본 for문
		for (int i = 0; i < array2.length; i++) {
			System.out.println(array2[i]);
		}

		// 확장 for문
		for (int a : array2) {
			System.out.println(a);
		}

		// 미션 1. 배열 복사
		int[] array3 = { 3, 1, 9, 2, 5 };
		int[] array4 = new int[7];
		for (int i = 0; i < array3.length; i++) {
			array4[i] = array3[i];
		}
		System.out.println("-----------array4------------");
		for (int i : array4) {
			System.out.println(i);
		}

		// 미션 2. 버블 정렬
		int[] lottos = { 34, 12, 3, 9, 25, 2 };
		int temp = 0, step = 0;
		// 정렬코드
		for (int i = 0; i < lottos.length - 1; i++) {
			for (int j = 0; j < lottos.length - step - 1; j++) {
				if (lottos[j] > lottos[j + 1]) {
					temp = lottos[j + 1];
					lottos[j + 1] = lottos[j];
					lottos[j] = temp;
				}
			}
			step++;
		}
		System.out.println("----------lottos----------");
		for (int i : lottos) {
			System.out.println(i);
		}
	}
}
