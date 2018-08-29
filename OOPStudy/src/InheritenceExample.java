
public class InheritenceExample {

	public static void main(String[] args) {
		Bycle bycle;
		bycle = new Bycle(10, "삼천리");
		System.out.println(bycle.brand);
		bycle.running();
		
		// 상속받은 아이를 생성하게 되면, Heap영역에 부모와 자식이 나란히붙어 생성된다.
		// 인스턴스 변수는 자식을 가리키지만, 실제로는 아무 것도 없다.
		// 하지만 아무 것도 없을 경우, 자식이 아닌 부모의 메모리까지 가서 값을 가져온다.
		// 따라서 상속은 메모리 상속이라고 보면 된다.
		MountainBycle mountainBycle = new MountainBycle(10, "삼천포", "카본", true);
		// 재사용 
		System.out.println(mountainBycle.brand);
		System.out.println(mountainBycle.id);
		mountainBycle.running();
		
		// 확장
		System.out.println(mountainBycle.frame);
		System.out.println(mountainBycle.suspension);
		
	}

}
