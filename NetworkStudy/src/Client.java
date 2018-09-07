import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 클라이언트의 데이터 수신 및 처리
 * 클라이언트가 서버쪽 메모리지만, 클라이언트의 데이터를 수신 및 처리하므로 클래스 이름을 클라이언트로 함.
 * 
 * @author 이철우
 *
 */
public class Client extends Thread {

	private boolean running;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	// 예외처리는 메인문으로 넘긴다.
	Client(Socket socket) throws IOException {
		this.socket = socket;
		// 체이닝
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		running = true;
	}

	// 데이터가 언제 날라올지 모르므로 계속 대기하고 있어야한다.
	public void receive() {

		while (running) {
			// 예외처리 시, try-catch와 throws를 잘 고민해서 선택하자.
			// 여기서는 클라이언트 측 통신이 끊길수도 있으니 여기서 처리해주는 것이 맞다.
			String clientMesseage = null;

			try {
				clientMesseage = in.readLine();
				System.out.println("클라이언트 메세지 : " + clientMesseage);
				if (clientMesseage.equalsIgnoreCase("quit")) {
					break;
				}
				out.println(clientMesseage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 밑의 구문을 finally에 넣게되면 소켓이 정상종료 되었음에도 불구하고 소켓을 종료하려 한다.
			// 하지만 if문을 쓰게되면 socket의 유무에 따라 다르게 적용할 수 있다.
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		receive();
	}

}
