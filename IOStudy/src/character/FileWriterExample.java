package character;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {

	public static void main(String[] args) throws IOException {
		String path = "examplt5.txt";

		String message = "아 배고파...";
		FileWriter out = new FileWriter(path);

		/*
		 * char[] chars = new char[100]; message.getChars(0, message.length(), chars,
		 * 0); out.write(chars); out.close();
		 */
		// 위의 예제는 String을 char형의 배열로 변환해야하는 번거로움 발생

		// 밑의 예제는 형변환 없이 바로 사용
		// 인코딩 기능까지 됨.
		BufferedWriter bw = new BufferedWriter(out);
		bw.write(message);
		bw.close();

	}

}
