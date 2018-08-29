
public class MountainBycle extends Bycle{

	// 추가 속성
	String frame;
	boolean suspension;
	
	public MountainBycle() {
		this(0, null, null, false);
	}
	
	// 상속받은 변수들을 함께 초기화한다.
	// 부모의 생성자를 불러오려면 super를 사용한다.
	// 만일 부모 생성자에 default 생성자가 없으면 문제가 발생할 수 있다.
	public MountainBycle(int id, String brand, String frame, boolean suspension) {
		/*this.id = id;
		this.brand = brand;*/
		super(id, brand);
		this.frame = frame;
		this.suspension = suspension;
	}
	
	public void running() {
		System.out.println("산도 달립니다..");
	}
}
