
/**
 * 추상 클래스 
 * @author 이철우
 *
 */
public abstract class Animal {
	protected String name;
	protected int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	// 모든 동물을 나타내는 방법은 같기에 public으로 할 수 있다.
	public void printInfo() {
		System.out.println("이름: " + name + ", 나이 : " + age);
	}
	
	// 모든 동물은 다르게 자므로
	public abstract void sleep();
	public abstract void eat();
	
}
