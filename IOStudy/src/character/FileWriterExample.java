package character;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileWriterExample {

	public static void main(String[] args) throws IOException {
		String path = "src/BufferedInputStreamExample.java";
		FileReader in = new FileReader(path);
		System.out.println(in.read());
		// 형변환으로 디코딩하기
//		System.out.println((char)in.read());
		/*char[] buffer = new char[1024];
		int count = 0;
		
		while((count = in.read(buffer)) != -1) {
			for (char c : buffer) {
				System.out.print(c);
			}
		}*/
		
		
		BufferedReader br = new BufferedReader(in);
//		String txt = br.readLine();
//		System.out.println(txt);
		String txt = null;
		// 클래스이기에 값이 없으면 null 반환
		while((txt = br.readLine()) != null) {
			System.out.println(txt);
		}
		
	}

}
