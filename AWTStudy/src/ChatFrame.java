import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
 * @author 이철우
 *
 */
public class ChatFrame extends Frame {
	Label serverL;
	TextField serverTF, inputTF;
	Button connectB, sendB;
	TextArea messageTA;
	List userL;

	MenuBar menuBar;
	Menu menu;
	MenuItem newMI, exitMI;
	
	public ChatFrame() {
		super("채팅");
	}

//	복합 관계
	public ChatFrame(String title) {
		super(title);
		serverL = new Label("서버");
		serverTF = new TextField();
		inputTF = new TextField();
		// 이름없는 내부 클래스
		// 원하는 컴포넌트를 오버라이딩해서 커스터마이징 할 수 있다.
		connectB = new Button("연결") /*{
			@Override
			public void paint(Graphics g) {
				g.drawLine(10, 10, 50, 10);
			}
		}*/;
		sendB = new Button("전송");
		messageTA = new TextArea();
		userL = new List();
		userL.add("말미잘");
		userL.add("꼴뚜기");
		userL.add("머저리");
		
		menuBar = new MenuBar();
		menu = new Menu("File");
		newMI = new MenuItem("New");
		newMI.setShortcut(new MenuShortcut(KeyEvent.VK_N));
		exitMI = new MenuItem("Exit");
		exitMI.setShortcut(new MenuShortcut(KeyEvent.VK_X));
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

		setLocation(100, 100);

//		setColorAll(Color.BLUE);
		
		setMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(newMI);
		menu.addSeparator();
		menu.add(exitMI);
	}

	public void setCenter() {
		// Runtime.getRuntime().exec(command); 팩토리 메서드
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

	public void appendMessage() {
		String message = inputTF.getText();
		messageTA.append(message + "\n");
		inputTF.setText("");
	}

	/** 멤버 내부클래스를 이용한 이벤트 처리 *//*
								 * class Exiter extends WindowAdapter {
								 * 
								 * @Override public void windowClosing(WindowEvent e) { finish(); } }
								 */

	// 이름있는 지역 내부 클래스
	// 기존 멤버 내부클래스의 메모리 낭비 문제를 해결한 방법
	/*
	 * public void eventRegist() { class Exiter extends WindowAdapter {
	 * 
	 * @Override public void windowClosing(WindowEvent e) { finish(); } }
	 * addWindowListener(new Exiter()); }
	 */

	// 이름없는 지역 내부 클래스
	public void eventRegist() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});

		// inputTF 값 이벤트 처리
		inputTF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appendMessage();
			}
		});

		serverTF.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyChar());
				System.out.println(KeyEvent.VK_ENTER);

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		inputTF.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				System.out.println(inputTF.getText());

			}
		});

		userL.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String name = userL.getSelectedItem();
					JOptionPane.showMessageDialog(null, name + "님 선택", "알림", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		exitMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				finish();
				
			}
		});
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
