package memo;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDao {
	private String path = "/Users/mcbookpro/Java/workspace/IOStudy/";
	PrintWriter pWriter;
	BufferedReader bReader;
	FileDialog fDialog;

	/**
	 * PrintWritter를 이용한 저장 메서드로 인코딩에 신경쓸 필요가 없다.
	 *
	 * @param title
	 * @param contents
	 */
	public void save(String title, String contents) {
		try {
			pWriter = new PrintWriter(path + title + ".txt");
			pWriter.println(contents);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pWriter != null)
				pWriter.close();
		}
	}

	/**
	 * BufferedReader를 이용한 열기 메서드로 디코딩에 신경쓸 필요가 없다.
	 * 
	 * @param title
	 * @return
	 */
	public char[] open(String title) {
		File file = new File(path + title);
		char[] buffer = new char[(int) file.length()];
		try {
			FileReader in = new FileReader(path + title);
			bReader = new BufferedReader(in);
			bReader.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
}
