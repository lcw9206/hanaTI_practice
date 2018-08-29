
/**
 * 무기에 대한 수평적 규격 역할의
 * 
 * @author 이철우
 *
 */

// 인터페이스는 상호 작용을 하기위해 만드는 것이므로 무조건 public이다.
// 따라서 컴파일 시, 자동으로 public이 붙게되고, 메서드에는 abstract도 함께 붙게된다.
// 하지만 명시적으로 public 정도는 붙여주는게 좋다.
public interface Weapon {
	// 인터페이스 안에 변수를 선언하면 상수(public static final)처리 된다.
	// 기본 규격을 만들 때 생성하며, 상수로 취급하기에 대문자로 선언하는게 관례다.
	int WEIGHT = 10;

	public void attack();

}
