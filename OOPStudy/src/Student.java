
/**
 * 제너릭 클래스
 * @author 이철우
 *
 */
public class Student<T> {
	private String name;
	private T ssn;
	
	public Student() {
		super();
	}

	public Student(String name, T ssn) {
		super();
		this.name = name;
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getSsn() {
		return ssn;
	}

	public void setSsn(T ssn) {
		this.ssn = ssn;
	}
	
	public static void main(String[] args) {
		Student<Integer> student = new Student<Integer>("김기정", 123456);
		student.setSsn(35566);
		
	}
}
