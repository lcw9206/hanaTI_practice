package character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

public class MemoryStreamExample {

	public static void main(String[] args) throws IOException {
		String message = "KOSTA 187기";
		
		StringReader sr = new StringReader(message);
		System.out.println((char)sr.read());
	}

}
