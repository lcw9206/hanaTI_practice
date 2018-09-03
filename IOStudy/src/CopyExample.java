import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyExample {

	public static long copy(String srcPath, String destPath) throws IOException {
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(srcPath);
			out = new FileOutputStream(destPath);

			byte[] buffer = new byte[4 * 1024];
			int count = 0;
			long totalCount = 0;
			while ((count = in.read(buffer)) != -1) {
				// 바로 밑과 같이 선언하게 되면, 마지막 데이터가 1~3바이트일 경우 오버된 값을 복사하게 된다.
				// 따라서 count를 이용해 offset을 확실히 선언해줘야한다.
//			out.write(buffer);
				out.write(buffer, 0, count);
				totalCount += count;
			}
			return totalCount;

		} finally {
			// finally를 이용해 여기에 close를 적어놓을 경우, 위에서 in 선언 이후 out에서 예외가 발생해도
			// 무조건 여기를 거쳐 닫고 난 후, 예외처리가 던져지는 곳으로 이동한다. 
			// 만일 finally를 안쓰게 되면 평생 닫히지 않게된다.
			if(out != null) out.close();
			if(in != null) in.close();
		}
	}

	public static void main(String[] args) throws IOException {
		String src = "/Applications/KOSTA187/설치프로그램/staruml-5.0-with-cm.exe";
		String dest = "/Applications/KOSTA187/설치프로그램/staruml.exe";

		long copySize = copy(src, dest);
		System.out.println(copySize + "바이트 파일 복사 완료...");

	}

}
