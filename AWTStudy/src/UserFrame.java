import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class UserFrame extends Frame implements MouseListener, WindowListener {
	// String title; => 자동 상속되어 있음.
	Button eButton, wButton, sButton, nButton, cButton;

	public UserFrame() {
		super("이름없음");
	}

	// 복합 관계
	public UserFrame(String title) {
		super(title);
		this.eButton = new Button("East");
		this.wButton = new Button("West");
		this.sButton = new Button("South");
		this.nButton = new Button("North");
		this.cButton = new Button("Center");
	}

	// 화면 배치담당 메서드
	public void setContents() {
		// 레이아웃 매니저 교체
		setLayout(new FlowLayout());
		add(eButton, BorderLayout.EAST);
		add(wButton, BorderLayout.WEST);
		add(sButton, BorderLayout.SOUTH);
		add(nButton, BorderLayout.NORTH);
		add(cButton, BorderLayout.CENTER);

	}

	// 이벤트 처리 메서드
	public void eventRegist() {
		// 이벤트 소스
		// UserFrame이 이벤트 처리를 담당하므로 this를 써준다.
		eButton.addMouseListener(this);
		wButton.addMouseListener(this);
		sButton.addMouseListener(this);
		nButton.addMouseListener(this);
		cButton.addMouseListener(this);
		this.addWindowListener(this);
		
	}

	// 컨트롤 타워
	public static void main(String[] args) {
		UserFrame frame = new UserFrame("타이틀입니다.");
		frame.setContents();
		frame.eventRegist();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

	// MouseEvent 안에는 모든 정보들이 들어있다.
	// 콜백 메서드 => 누군가에 의해 불러지는 메서드 ex)
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e);
		System.out.println("마우스 클릭됨");
		// eButton
		// Object로 값을 받아 다운캐스팅을 진행.
		Object eventSource = e.getSource();
		Button button = (Button) eventSource;
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		button.setBackground(new Color(r, g, b));
		
		if(eventSource == eButton) {
			System.out.println("eButton");
		} else if (eventSource == sButton) {
			System.out.println("sButton");
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed() Called");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased() Called");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered() Called");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited() Called");
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("창 열림");
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		setVisible(false);
		// OS에 그래픽 리소스 반납
		dispose();
		System.exit(0);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
