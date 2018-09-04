import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamExample {
	static final String path = "example3.dat";

	public static void main(String[] args) throws IOException {
		boolean flag = false;
		char c = '이';
		int age = 27;
		double weight = 73.2;
		String message = "입출력 프로그램입니다..";
		
		// DataOutputStream는 내부적으로 버퍼가 존재하지 않는다.
		DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
		// 따라서 대량의 데이터를 쓸 일이 있으면 아래와 같이 버퍼를 생성해 사용하면 된다.
//		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(FileOutputStream(path)));
		
		out.writeBoolean(flag);
		out.writeChar(c);
		out.writeInt(age);
		out.writeDouble(weight);
		// UTF를 통해 String을 알아서 형변환해준다.
		out.writeUTF(message);
		out.close();
		System.out.println("썼음");
	}
	
}
