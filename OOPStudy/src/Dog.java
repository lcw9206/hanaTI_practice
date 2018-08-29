
public class Dog extends Animal {
	
	private boolean loyalty;
	
	public Dog() {
		this(null, 0, false);
	}
	
	public Dog(String name, int age, boolean loyalty) {
		this.name = name;
		this.age = age;
		this.loyalty = loyalty;
	}

	@Override
	public void sleep() {
		System.out.println("강아지가 주무십니다.");

	}

	@Override
	public void eat() {
		System.out.println("강아지가 먹는다.");

	}

	public static void main(String[] args) {
		// 아래와 같이 선언하면 프로그램이 유연하지 못하다.
		// Dog dog = new Dog();
		
		// 타입만 선언
		Animal animal = null;
		// 업 캐스팅 
		animal = new Dog("루니", 2, false);
		animal.eat();
		animal.printInfo();
	}
}
