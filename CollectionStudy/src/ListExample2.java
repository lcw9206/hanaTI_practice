import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * 
 * 순서를 통해 데이터를 관리하기 위한 규약(인터페이스)이다.
 * Set과 달리 요소가 순차적으로 관리되며, 중복을 허용하는 특징을 가진다.
 * ArrayList, Vector, LinkedList가 List 인터페이스를 구현한 대표적 클래스이다
 * @author 이철우
 */
public class ListExample2 {

	public static void main(String[] args) {
//		List list = null;
		// Vector를 사용할 때는 무조건 Element가 메서드에 붙는다.
		Vector list = null;
		// Vector(용량, 증가치)
		// Vector는 용량이 모자라면 2배씩 늘어난다. 10 20 40... 이런 비효율을 줄이고자 증가치를 언
		list = new Vector(10, 5);

		// 모든 값을 넣을 수 있다.
		list.addElement("황의조");
		list.addElement("손흥민");
		list.addElement("바나나");
		list.addElement(100);
		// autoboxing이 일어남
		list.addElement(new Integer(100));
		list.addElement(Calendar.getInstance());
		list.addElement("황의조");

		System.out.println("담겨진 개수 : " + list.size());
		System.out.println("비어있는지 여부 : " + list.isEmpty());
		
		System.out.println(list.elementAt(0));
		System.out.println(list.removeElement("바나나"));
		
		// Enumeration을 이용해 vector를 동기화처리
		Enumeration e = list.elements();
		while (e.hasMoreElements()) {
			Object object = (Object) e.nextElement();
			System.out.println(object);
		}
		
	}
}
