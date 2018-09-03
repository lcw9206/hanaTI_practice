import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamExample {
	static final String path = "example.dat";

	public static void main(String[] args) throws IOException {
		OutputStream out = new FileOutputStream(path);
		//OutputStream out = new FileOutputStream(path, true);
		// 지정 파일이 존재하면 값을 set한다. 하지만 위와 같이 true 옵션을 주게되면 set이 아닌 append한다. 
		// 저장한 값을 에디터로 열면 아스키코드에 해당하는 문자가 나온다. (디코딩되어)
		// 하지만 이는 잘못된 것이다. 스트림은 순수히 통로역할만 하기에 디코딩되지 않은 65가 저장되어있는 것이다.
//		out.write(65);
//		out.close();

		byte[] buffer = new byte[128];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = (byte) i;
		}
		
		out.write(buffer);
		// 위 아래 동일한 동작
//		out.write(buffer, 0, buffer.length);
		out.close();
		System.out.println("파일에 128바이트 썼음");
	}
}
