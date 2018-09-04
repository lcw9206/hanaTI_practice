package character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterExample {

	public static void main(String[] args) throws IOException {
		String path = "example6.html";
		
		PrintWriter out = new PrintWriter(path);
		out.println("<html>");
		out.println("<body>");
		out.println("<b>HTML</b>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
