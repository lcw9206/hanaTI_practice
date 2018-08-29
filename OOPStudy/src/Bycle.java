public class Bycle /*extends Object*/{
	// 모든 클래스들은 Object 클래스를 상속받는다.
	int id;
	String brand;
	public Bycle() {
		//super();
		this(0, null);
	}
	public Bycle(int id, String brand) {
		this.id = id;
		this.brand = brand;
	}
	
	public void running() {
		System.out.println("자전거가 달립니다..");
	}
}
