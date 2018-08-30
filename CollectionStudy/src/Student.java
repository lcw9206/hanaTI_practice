import java.util.HashMap;
import java.util.Map;

/**
 * 동적 객체 정의
 * 
 * @author 이철우
 *
 */
public class Student {
	// Key = 인스턴스변수라 생각
	Map<String, Object> prop;

	// 객체 생성 시, 자동으로 HashMap을 생성.
	// 동적 배열이므로 생성만하면 된다.
	public Student() {
		prop = new HashMap<String, Object>();
	}

	public Map<String, Object> getProp() {
		return prop;
	}

	public void setProp(Map<String, Object> prop) {
		this.prop = prop;
	}

	public void setProperty(String key, Object value) {
		prop.put(key, value);
	}

	public Object getProperty(String key) {
		return prop.get(key);
	}
	
	
	public static void main(String[] args) {
		Student student = new Student();
		student.setProperty("ssn", "88887");
		student.setProperty("name", "김기정");
		
		Student student2 = new Student();
		student.setProperty("ssn", "82887");
		student.setProperty("name", "이철우");
		student.setProperty("address", "LA");
		
	}
}
