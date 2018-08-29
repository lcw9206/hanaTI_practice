
public class PolymophismExample {

	public static void main(String[] args) {
		// 클래스 형변환(Up casting)
		Shape shape = null;
//		shape = new Shape();
		
		shape = new Circle(10, 10, 20);
		System.out.println(shape);
		
		shape = new Rectangle(2, 2, 4, 4);
		System.out.println(shape);
		
		System.out.println(shape.x);
		// 재정의가 아닌 확장이므로 호출 불가능 
		// System.out.println(shape.getWidth());
		
		System.out.println(shape.getArea());
		
		// 추가된 속성이나 메소드를 접근하기 위해 다운 캐스팅 필요
		Rectangle rectangle = (Rectangle)shape;
		rectangle.getWidth();
		
		System.out.println(shape instanceof Object);
		System.out.println(shape instanceof Shape);
		System.out.println(shape instanceof Rectangle);		
	}

}
