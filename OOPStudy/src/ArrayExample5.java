
/**
 * 레퍼런스타입 배열 선언, 생성, 초기화 다차원 배열
 * 
 * @author 이철우
 *
 */
public class ArrayExample5 {

	public static void main(String[] args) {
		String[][] array = new String[3][100];
		array[0][0] = "AAA";
		array[2][0] = "ZZZ";

		// 위와 아래 3개의 선언은 모두 같은 것이므로 기억할 것. 이런게 시험에 나온다.
//		String[][] array2 = new String[][] {{"a","b","c"}, {"d","e","f"}, {"g","h","i"}};
		String[][] array2 = { { "a", "b", "c" }, { "d", "e", "f" }, { "g", "h", "i" } };

		// 다차원 배열에서는 반드시 정방 행렬일 필요가 없다. 따라서 밑과 같이 동적으로 크기를 잡아 생성할 수 있다.
		int[][] arr = new int[3][];
		arr[0] = new int[4];
		arr[1] = new int[5];
	}
}
