import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamExample {
	static final String path = "example2.dat";
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		// BufferedOutputStream는 단독적으로 실행 불가능. 반드시 노드스트림이 있어야한다.
		BufferedOutputStream out = new BufferedOutputStream(fos);
		// write => 하드에 쓰는 것이 아닌 BufferedOutputStream의 메모리에 쓴다.
		// BufferedOutputStream의 default 값인 512 바이트가 채워져야 하드에 써진다.
		out.write(10);
		out.write(20);
		byte[] data = {5, 6, 7, 8, 9};
		out.write(data);
		// 512 바이트가 채워지기 전에 하드에 쓰려면 flush를 선언해준다.
		// 단, 이 예제와 같이 메인문이 바로 끝날때는 써줄 필요가 없다. 종료와 동시에 써진다.
		out.flush();
		System.out.println("파일써짐");
		
	}
}
