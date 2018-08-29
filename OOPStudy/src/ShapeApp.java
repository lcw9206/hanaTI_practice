
public class ShapeApp {

	public static void main(String[] args) {
		// Shape shape = new Shape(12.5, 35.7);
		Shape shape = null;

		shape = new Circle(15.0, 15.0, 30);
		shape.draw();
		System.out.println("원의 둘레: " + shape.getLength());
		System.out.println("원의 면적: " + shape.getArea());

		shape = new Rectangle(15.0, 15.0, 30.0, 30.0);
		shape.draw();
		System.out.println("사각형의 둘레: " + shape.getLength());
		System.out.println("사각형의 면적: " + shape.getArea());
		System.out.println(shape);

		Shape ref = new Rectangle(); // ············· ①
		if (ref instanceof Circle) { // ············· ②
			Circle c = (Circle) ref;
			System.out.println("dd");
		} else if (ref instanceof Rectangle) {
			Rectangle r = (Rectangle) ref;
			System.out.println("rr");
		}
	}

}
