import java.util.Random;

public class Car extends Thread {
	// 쓰레드 클래스에는 name 변수가 이미 존재한다.
	public Car(String name) {
		// super(name);
		setName(name);
	}

	// 쓰레드의 엔트리 포인트 역할
	public void run() {
		Random random = new Random();
		System.out.println(getName() + "자동차 가즈아!");
		for (int i = 0; i <= 100; i++) {
		
		// 각각의 쓰레드이므로 예외처리에서 throws 불가능	
		try {
			Thread.sleep(random.nextInt(200));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println(getName() + " 자동차 " + i + "미터 전진");
		}
		System.out.println(getName() + "자동차 Finish");
	}
}
