
/**
 * 모든 도형의 공통적인 속성과 기능을 정의한 추상클래스
 * 
 * @author 이철우
 *
 */
public abstract class Shape {

	protected double x, y;

	// 추상 메서드 선언
	// 서브클래스가 반드시 구현(재정의)해야 할 수직적
	public abstract void draw();

	public abstract double getLength();

	public abstract double getArea();

	// 이런 경우 그냥 써도 된다는
	public void xxx() {

	}
}
