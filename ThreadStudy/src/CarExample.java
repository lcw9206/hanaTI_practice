
public class CarExample {

	public static void main(String[] args) {
		System.out.println("메인 스레드에 의해 프로그램 시작됨");

		Car car = new Car("방그리");
		car.run();
		
		System.out.println("메인 스레드에 의해 프로그램 종료됨");
	}

}
