
public class InnerExample {

	public static void main(String[] args) {
		
		OuterClass.InnerClass in = new OuterClass().new InnerClass();
		in.foo();
		
		OuterClass.SInnerClass in2 = new OuterClass.SInnerClass();
		in2.bar();
	}

}
