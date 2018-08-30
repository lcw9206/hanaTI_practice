import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

public class ChatFrame extends Frame {
	Label serverL;
	TextField serverTF, inputTF;
	Button connectB, sendB;
	TextArea messageTA;
	List userL;
	
	public ChatFrame() {
		super("채팅");
	}

	// 복합 관계
	public ChatFrame(String title) {
		super(title);
		serverL = new Label("서버");
		serverTF = new TextField();
		inputTF = new TextField();
		connectB = new Button("연결");
		sendB = new Button("전송");
		messageTA = new TextArea();
		userL = new List();
	}

	// 화면 배치담당 메서드
	public void setContents() {
		Panel northP = new Panel();
		northP.setLayout(new BorderLayout());
		northP.add(serverL, BorderLayout.WEST);
		northP.add(serverTF, BorderLayout.CENTER);
		northP.add(connectB, BorderLayout.EAST);
		
		Panel southP = new Panel();
		southP.setLayout(new BorderLayout());
		southP.add(inputTF, BorderLayout.CENTER);
		southP.add(sendB, BorderLayout.EAST);
		
		add(northP, BorderLayout.NORTH);
		add(messageTA, BorderLayout.CENTER);
		add(userL, BorderLayout.EAST);
		add(southP, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		ChatFrame frame = new ChatFrame("코톡");
		frame.setContents();
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}
