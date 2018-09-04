import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStreamExample {
	static final String path = "/Users/mcbookpro/Downloads/수강확인증_이철우_180824.pdf";
	
	public static void main(String[] args) throws IOException {
		// Node Stream
		InputStream fin = null;
		// 물을 땡겨온다.
		fin = new FileInputStream(path);
		
		// Filter Stream
		// default = 512바이트 배열을 갖고 있다. 
		BufferedInputStream in = null;
		// 땡겨온 물을 사용한다. 데이터 임의 접근이 가능하다.
		in = new BufferedInputStream(fin);  
		in.read();
		System.out.println(in.read());
		in.read();
		System.out.println(in.read());
		in.read();
		System.out.println(in.read());
		in.read();
		System.out.println(in.read());
		in.mark(2); // read로 데이터를 읽다가 reset 메서드를 이용해 mark한 곳으로 이동할 수 있다.
		in.skip(20); // n바이트만큼 건너뛴다.
		in.reset(); // mark한 곳으로 되돌아간다.
		System.out.println(in.read());
	}
}
