
public class CarExample {

	// 메인 쓰레드의 엔트리 포인트 역할
	public static void main(String[] args) {
		System.out.println("메인 스레드에 의해 프로그램 시작됨");

		Car car = new Car("방그리");
		// car.run(); => Thread를 상속받아도 직접 호출하면 main 쓰레드.
		// 이에 독립 쓰레드를 생성하기 위해 start 메서드 이용
		car.start();
		
		Car car2 = new Car("홍길동");
		car2.start();
		
		Car car3 = new Car("리그방");
		car3.start();
		
		System.out.println("메인 스레드에 의해 프로그램 종료됨");
	}

}
