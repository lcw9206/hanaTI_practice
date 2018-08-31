import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;

public class ChatFrame extends Frame {
	Label serverL;
	TextField serverTF, inputTF;
	Button connectB, sendB;
	TextArea messageTA;
	List userL;

	public ChatFrame() {
		super("채팅");
	}

//	복합 관계
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

//	화면 배치담당 메서드 => 모듈화
	public void setContents() {
//		활성화, 비활성화 기능으로 컴포넌트, 버튼 등 다양한 곳에 사용 
//		connectB.setEnabled(false);
//		connectB.setBackground(Color.BLUE);
//		connectB.setForeground(Color.WHITE);

//		connectB.setFont(new Font("궁서", Font.BOLD, 20));
		
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
		
		setLocation(100,100);

//		setColorAll(Color.BLUE);
	}

	public void setCenter() {
		//		Runtime.getRuntime().exec(command); 팩토리 메서드
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x, y);
		
	}
	
	private void setColorAll(Color bg) {
		Component[] components = getComponents();
//		밑의 component에는 Panel도 들어가 있으므로 한 번 더 들어가야한다.
		for (Component component : components) {
			if (component instanceof Panel) {
				Component[] cs = ((Panel) component).getComponents();
				for (Component component2 : cs) {
					component2.setBackground(bg);
				}
			}
			component.setBackground(bg);
		}
	}
	
	
	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	// 이벤트 연결
	public void eventRegist() { 
		addWindowListener(new ExitHandler(this));
	}
	
	public static void main(String[] args) {
		ChatFrame frame = new ChatFrame("코톡");
		frame.setContents();
		frame.setSize(400, 500);
		frame.setCenter();
		// 이벤트 등록
		frame.eventRegist();
		frame.setVisible(true);
	}
}
