
public class Rectangle extends Shape {

	private double width, height;

	public Rectangle() {

	}

	public Rectangle(double x, double y, double width, double height) {
		// super(x, y);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public void draw() {
		System.out.println("길이 : " + width + "높이 : " + height + "인 사각형입니다.");
	}

	@Override
	public double getLength() {
		return 2 * (width + height);
	}

	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public String toString() {
		return "길이 : " + width + "높이 : " + height + "인 사각형입니다.";
	}
}
