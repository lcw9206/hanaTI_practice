import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericExample {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("박소연");
		list.add("김홍기");
		list.add("류세은");
		
		Iterator<String> itor = list.iterator();
		while (itor.hasNext()) {
			String name = itor.next();
			System.out.println(name);
		}
		
		for (String string : list) {
			System.out.println(string);
		}
	}
}
