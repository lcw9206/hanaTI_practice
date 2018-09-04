package memo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDao {
	private String path = "/Users/mcbookpro/Java/workspace/IOStudy/";

	/**
	 * PrintWritter를 이용한 저장 메서드로 인코딩에 신경쓸 필요가 없다.
	 *
	 * @param title
	 * @param contents
	 * @throws IOException 
	 */
	public void save(String title, String contents) throws IOException {
		File file = new File(path + title);
		if(file.exists()) {
			throw new IOException("동일한 이름의 파일이 존재합니다.");
		}
		// 파일이 존재할 때 객체 생성
		PrintWriter pWriter = null;
		
		try {
			pWriter = new PrintWriter(path + title + ".txt");
			pWriter.println(contents);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pWriter != null) pWriter.close();
		}
	}

	/**
	 * BufferedReader를 이용한 열기 메서드로 디코딩에 신경 쓸 필요가 없다.
	 * 
	 * @param title
	 * @return
	 * @throws IOException 
	 */
	public String open(String title) throws IOException {
		File file = new File(path + title);
		if(!file.exists()) { 
			throw new IOException("존재하지 않는 파일입니다.");
		}
		String sBuffer = null;
		BufferedReader bReader = null;
		char[] buffer = new char[(int) file.length()];
		try {
			bReader = new BufferedReader(new FileReader(path + title));
			sBuffer = String.valueOf(bReader.read(buffer));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bReader != null) bReader.close();
		}
		return sBuffer;
	}
}
