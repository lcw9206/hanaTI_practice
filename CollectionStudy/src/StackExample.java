import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class StackExample {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.push("2");
		stack.push("4");
		stack.push("1");
		stack.push("6");
		stack.push("5");
		stack.pop();
		System.out.println(stack.peek());
		for (String string : stack) {
			System.out.println(string);
		}
		
	}

}
