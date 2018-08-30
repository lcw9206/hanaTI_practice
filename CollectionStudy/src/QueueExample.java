import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class QueueExample {

	public static void main(String[] args) {
		LinkedList<String> queue = new LinkedList<>();
		queue.offer("AAAA");
		queue.offer("BBBB");
		queue.offer("CCCC");
		queue.poll();
		queue.poll();

		System.out.println(queue.poll());
		
	}

}
