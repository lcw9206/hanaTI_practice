import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

public class UserFrame extends Frame {
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

	public static void main(String[] args) {
		UserFrame frame = new UserFrame("타이틀입니다.");
		frame.setContents();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
}
