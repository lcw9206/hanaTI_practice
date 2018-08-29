/**
 * @author 이철우
 *
 */
public class ArrayUtil {
	/**
	 * 배열 복사
	 * 
	 * @param src       복사하고자 하는 원본 배열
	 * @param increment 증가치
	 * @return 복사된 배열
	 */
	public static int[] copy(int[] src, int increament) {
		int[] array = new int[src.length + increament];
		for (int i = 0; i < src.length; i++) {
			array[i] = src[i];
		}
		return array;
	}

	/**
	 * 배열 오름차순 정렬 (버블 정렬)
	 * 
	 * @param src 원본배열
	 */
	public static void sort(int[] src) {
		int step = 0, temp = 0;
		for (int i = 0; i < src.length - 1; i++) {
			for (int j = 0; j < src.length - step - 1; j++) {
				if (src[j] > src[j + 1]) {
					temp = src[j + 1];
					src[j + 1] = src[j];
					src[j] = temp;
				}
			}
			step++;
		}
	}

	public static void main(String[] args) {
		int[] array = { 3, 5, 1, 6, 7, 10 };
		int[] copy = ArrayUtil.copy(array, 4);

		for (int i : copy) {
			System.out.println(i);
		}

		ArrayUtil.sort(array);
		for (int i : array) {
			System.out.print(i + "\t");

		}
	}
}
