import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class FileExample {

	public static void main(String[] args) throws IOException {
		Calendar cal = null;
		
		String path = "/Users/mcbookpro/Downloads/some.dat";
		File file = null;
		
		file = new File(path);
		System.out.println("존재여부 : " + file.exists());
		
		path = "/Applications/KOSTA187/설치프로그램/ALZip106.exe";
		file = new File(path);
		System.out.println("파일용량: " + file.length());
		System.out.printf("변경날자:  %1$tF %tr%n", file.lastModified());
		
		path = "/Applications/KOSTA187";
		file = new File(path);
		System.out.println("Path가 디렉토리입니까? : " + file.isDirectory());
		String[] dirNames = file.list();
		
		// listfiles 메서드를 이용해 파일, 디렉토리를 분리해 출력하는 방법이 더 좋다.
		for (String string : dirNames) {
			System.out.println("서브 디렉토리 : " + string);
		}
		
		path = "example.dat";
		file = new File(path);
		System.out.println("파일의 절대 경로는 : " + file.getAbsolutePath());
		
		// 조작관련 기능들
		path = "xxx.dat";
		file = new File(path);
		System.out.println(file.createNewFile());
		
		// 임시파일
		// 만들어진 파일의 정보를 담은 File 객체를 반환
		File temp = File.createTempFile("some", ".dat");
		System.out.println(temp.getAbsolutePath());
		FileOutputStream out = new FileOutputStream(temp);
		out.write(10);
		
		
		// 실행파일 종료시 자동 삭제
		temp.deleteOnExit();
		
		File dir = new File("/Users/mcbookpro/");
//		dir.mkdirs();
		
		File[] drives = File.listRoots();
		for (File file2 : drives) {
			System.out.println(file2);
		}
		
	}

}
