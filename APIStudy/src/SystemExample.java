
public class SystemExample {

	public static void main(String[] args) {
		System.out.println("프로그램 Start");
		
		// System.exit(0);
		System.gc();
		
		long start = System.currentTimeMillis();
		
		for(int i=0; i<10000000; i++) {
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end- start);
		System.out.println(System.getenv("Path"));
		System.out.println("프로그램 Finish");

	}

}
