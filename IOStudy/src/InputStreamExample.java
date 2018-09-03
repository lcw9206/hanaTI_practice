import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamExample {
	static final String path = "/Users/mcbookpro/Downloads/수강확인증_이철우_180824.pdf";

	public static void main(String[] args) {
		InputStream in = null;

		// 리소스때문에 IO에는 가장 많은 예외처리가 적용되어 있다.
		try {
			in = new FileInputStream(path);
			// 읽기 전, 바이트가 몇 개 있는지 확인
			System.out.println(in.available());

			// byte[](버퍼) 단위로 입력
			// 데이터를 4Kbyte만큼만 읽어오기
			byte[] buffer = new byte[4 * 1024];
			// count에 실제 읽어들일 byte 수가 저장된다.
			/*
			 * int count = in.read(buffer); System.out.println(count); 
			 * for (byte b: buffer) { System.out.println(b); }
			 */

			// 모든 데이터 읽기
//			읽을 데이터가 없을 경우, -1이 된다.
			int count = 0;
			while ((count = in.read(buffer)) != -1) {
				System.out.println(count);
			}

			// 위에서 다 읽었기때문에 0이된다.
			System.out.println(in.available());
//		// 다중캐치
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
