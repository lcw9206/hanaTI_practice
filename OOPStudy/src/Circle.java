
public class Circle extends Shape {

	private double radian;

	public Circle() {
		// super();
		// 밑의 this는 안넣어도 된다.
		this(0.0, 0.0, 0.0);
	}

	public Circle(double x, double y, double radian) {
		// 추상 클래스에서 protected로 변수를 선언했으므로 다른 클래스에서 마치 자기것인것 처럼 쓸 수 있다.
		// super(x, y);
		this.x = x;
		this.y = y;
		this.radian = radian;
	}

	public double getRadian() {
		return radian;
	}

	public void setRadian(double radian) {
		this.radian = radian;
	}

	@Override
	public void draw() {
		// 아래와 같이 쓸 경우, private 속성으로 인해 상속이 안된다.
		System.out.println(x + "," + y + "," + "radian 원입니다.");
//		System.out.println(getX() + "," + getY() + "," + "radian 원입니다.");
	}

	@Override
	public double getLength() {
		return 2 * Math.PI * radian;
	}

	@Override
	public double getArea() {
		return Math.PI * Math.pow(radian, 2);
	}

	@Override
	public String toString() {
		return "Circle [radian=" + radian + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
