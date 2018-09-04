package character;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {

	public static void main(String[] args) throws IOException {
		String path = "src/BufferedInputStreamExample.java";
		FileReader in = new FileReader(path);
		BufferedReader br = new BufferedReader(in);
		String txt = null;
		int count = 0;
		// 클래스이기에 값이 없으면 null 반환
		while((txt = br.readLine()) != null) {
			System.out.println(txt);
		}
		
		// char형을 byte형인 FileInputStream로 읽어 비교해보기
		// 
		FileInputStream fis = new FileInputStream(path);
		// 배열이 byte 형이기에 문자가 반씩짤려 들어오는 것과 같다.
		// 밑과 같이 배열을 강제변환(디코딩)해서 출력하면 문자가 짤려 이상하게 나온다.
		byte[] buffer2 = new byte[1024];
		while((count = fis.read(buffer2)) != -1) {
			for (byte c : buffer2) {
				System.out.print((char)c);
			}
		}
	}

}
