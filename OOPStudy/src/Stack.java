
/**
 * LIFO 구조의 스택 구현
 * @author 이철우
 *
 */
public class Stack {
	private static int[] array;
	private static int count = 0;
	
	// 생성자 추가
	Stack() {
		this(10);
	}
	Stack(int num) {
		array = new int[num];
	}
	
	/**
	 * 배열에 값을 추가하는 push 메서드
	 * @param value
	 */
	public void push(int value) {
		array[count] += value;
		count++;
	}
	
	/**
	 * 배열의 값을 제거하는 pop 메서드
	 * @return int
	 */
	public int pop() {
		int value = 0;
		if(count > 0) {
			value = array[count];
			array[count] = 0;
			count--;
			System.out.println("값이 삭제되었습니다.");
			return value;
		} else {
			System.out.println("Stack에 값이 존재하지 않습니다.");
			return 0;
		}
	}
	
	/**
	 * 배열의 길이를 측정하는 length 메서드
	 * @return int
	 */
	public int length() {
		return count;
	}
	
	public static void main(String[] args) {
		Stack stack = new Stack(10);
		stack.push(5);
		stack.push(6);
		stack.push(7);

		// push test를 위한 for문 출력
		for (int i = 0; i < stack.length(); i++) {
			System.out.print(Stack.array[i] + "\t");
		}
		System.out.println();
		
		stack.pop();
		stack.pop();
		
		// pop test를 위한 for문 출력
		for (int i = 0; i < stack.length(); i++) {
			System.out.print(Stack.array[i] + "\t");
		}
		System.out.println();
		
		System.out.println("스택의 길이는 : " + stack.length());
	}
}
