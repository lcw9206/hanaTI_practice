import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionExample {

	public static void main(String[] args) {
		// String 클래스를 내가 만든 클래스라고 가정하자.
		String str = "리플렉션이다.";
		
		// str 객체와 관련된 정보가 cls에 들어가있다. 
		Class cls = str.getClass();
		System.out.println(cls.getModifiers());
		System.out.println(Modifier.PUBLIC);
		System.out.println(cls.getName());
		System.out.println(cls.getSuperclass().getName());
		Field[] fields = cls.getFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		
		Method[] method = cls.getMethods();
		for (Method method2 : method) {
			System.out.println(method2.getName());
		}

	}

}
